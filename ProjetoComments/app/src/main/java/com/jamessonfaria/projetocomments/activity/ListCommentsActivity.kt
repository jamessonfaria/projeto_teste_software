package com.jamessonfaria.projetocomments.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.iid.FirebaseInstanceId
import com.jamessonfaria.projetocomments.R
import com.jamessonfaria.projetocomments.adapter.AdapterComentarios
import com.jamessonfaria.projetocomments.model.Comentario
import com.jamessonfaria.projetocomments.retrofit.client.ComentarioWebClient
import com.jamessonfaria.projetocomments.retrofit.client.ComentarioWebClient.RespostaListener
import com.jamessonfaria.projetocomments.util.ItemClicked
import com.jamessonfaria.projetocomments.util.Util
import kotlinx.android.synthetic.main.activity_list_comments.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.yesButton


class ListCommentsActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private var listaComentarios: List<Comentario> = ArrayList<Comentario>()
    lateinit var mAdView: AdView
    lateinit var toolbar: Toolbar
    lateinit var searchView: SearchView
    lateinit var comentarioAdapter: AdapterComentarios

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_comments)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val refreshedToken = FirebaseInstanceId.getInstance().token

        MobileAds.initialize(this, "ca-app-pub-2262809297014272~8826799325")

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()
        mAdView.loadAd(adRequest)

        getComentarios()

        fab.setOnClickListener { view ->
            startActivity(Intent(this@ListCommentsActivity, CreateCommentsActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        var item: MenuItem = menu!!.findItem(R.id.action_search)
        searchView = MenuItemCompat.getActionView(item) as SearchView
        MenuItemCompat.setOnActionExpandListener(item, object : MenuItemCompat.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                toolbar.setBackgroundColor(resources.getColor(R.color.azulLight))

                (searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text) as EditText)
                        .setHintTextColor(Color.WHITE)
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                searchView.setQuery("", false)
                return true
            }

        })

        searchView.maxWidth = Int.MAX_VALUE
        searchName(searchView)
        return true
    }

    private fun searchName(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                comentarioAdapter.filter.filter(newText)
                return true
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_search) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
            return
        }

        super.onBackPressed()
    }

    override fun onRestart() {

        super.onRestart()
        getComentarios()
    }

    override fun onResume() {

        super.onResume()
        mFirebaseAnalytics!!.setCurrentScreen(this@ListCommentsActivity,
                "ListCommentsActivity", null)
    }

    fun getComentarios() {
        if (Util.isNetworkAvaliabe(this)) {

            var progress = indeterminateProgressDialog("Coletando Comentários...", null)

            val client = ComentarioWebClient(this@ListCommentsActivity)
            client.getComentarios(object : RespostaListener<List<Comentario>> {
                override fun sucesso(resposta: List<Comentario>) {

                    runOnUiThread {
                        progress.cancel()
                        listaComentarios = resposta

                        val rv: RecyclerView = findViewById(R.id.rvListaComentarios)
                        rv.layoutManager = LinearLayoutManager(this@ListCommentsActivity) as RecyclerView.LayoutManager?
                        rv.hasFixedSize()
                        comentarioAdapter = AdapterComentarios(this@ListCommentsActivity,
                                listaComentarios, { comentarioItem: Comentario -> ItemClicked().rvItemClicked(comentarioItem, this@ListCommentsActivity, this@ListCommentsActivity) })
                        rv.adapter = comentarioAdapter

                    }
                }

                override fun falha(mensagem: String) {
                    runOnUiThread {
                        progress.cancel()
                        alert(mensagem, null) {
                            yesButton { }
                        }.show()
                    }
                }

            })
        } else {
            alert("Problema na Conexão com a Internet.", null) {
                yesButton { finish() }
            }.show()
        }
    }

}

package com.jamessonfaria.projetocomments.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jamessonfaria.projetocomments.R
import com.jamessonfaria.projetocomments.model.User
import com.jamessonfaria.projetocomments.retrofit.client.ComentarioWebClient
import com.jamessonfaria.projetocomments.retrofit.client.ComentarioWebClient.*
import com.jamessonfaria.projetocomments.util.Util
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.yesButton

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View) {
        if (Util.isNetworkAvaliabe(this@LoginActivity)) {

            val user = User(edtEmail.text.toString(), edtSenha.text.toString())

            var progress = indeterminateProgressDialog("Carregando...", null)

            var client = ComentarioWebClient(this@LoginActivity)
            client.login(user, object : RespostaListener<User> {
                override fun sucesso(resposta: User) {
                    runOnUiThread {
                        progress.cancel()
                        startActivity(Intent(this@LoginActivity, ListCommentsActivity::class.java))
                        overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation)
                        finish()
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

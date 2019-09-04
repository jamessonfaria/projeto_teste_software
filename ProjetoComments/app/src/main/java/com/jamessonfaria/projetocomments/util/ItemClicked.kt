package com.jamessonfaria.projetocomments.util

import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jamessonfaria.projetocomments.R
import com.jamessonfaria.projetocomments.activity.DetailCommentActivity
import com.jamessonfaria.projetocomments.model.Comentario
import android.support.v7.app.AppCompatActivity

class ItemClicked {
    fun rvItemClicked(comentario : Comentario, context: Context, app: AppCompatActivity) {

        val gson: Gson = Gson()
        val type = object : TypeToken<Comentario>() {}.type
        val comentarioJson = gson.toJson(comentario, type)

        var intent: Intent = Intent(context, DetailCommentActivity::class.java)
        intent.putExtra("COMENTARIO", comentarioJson)
        app.startActivity(intent)
        app.overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation)
    }
}
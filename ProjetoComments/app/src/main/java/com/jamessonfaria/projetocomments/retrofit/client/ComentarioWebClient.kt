package com.jamessonfaria.projetocomments.retrofit.client

import android.content.Context
import com.jamessonfaria.projetocomments.model.Comentario
import com.jamessonfaria.projetocomments.model.User
import com.jamessonfaria.projetocomments.retrofit.ComentarioRetrofit
import com.jamessonfaria.projetocomments.retrofit.service.ComentarioService
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ComentarioWebClient {

    private val service: ComentarioService

    constructor(ctx: Context) {
        service = ComentarioRetrofit(ctx).comentarioService()
    }

    fun login(user: User, listener: RespostaListener<User>) {
        val json: String = "{\"user\":{" +
                "\"email\":\"" + user.email + "\"," +
                "\"password\":\"" + user.password + "\"}}"

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)

        val call = service.login(body)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                listener.falha(t.message.toString())
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful && response.body() != null) {
                    listener.sucesso(response.body()!!)
                } else {
                    listener.falha("Usuário ou Senha Inválidos.")
                }
            }

        })
    }

    fun delete(id: Int, listener: RespostaListener<String>) {
        val call = service.delete(id)
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                listener.falha(t.message.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    listener.sucesso(response.body().toString())
                } else {
                    listener.falha(response.message())
                }
            }
        })
    }

    fun getComentarios(listener: RespostaListener<List<Comentario>>) {
        val call = service.listarComentarios()
        call.enqueue(object : Callback<List<Comentario>> {
            override fun onFailure(call: Call<List<Comentario>>, t: Throwable) {
                if (!call.isCanceled) {
                    listener.falha(t.message.toString())
                }
            }

            override fun onResponse(call: Call<List<Comentario>>, response: Response<List<Comentario>>) {
                if (response.isSuccessful()) {
                    listener.sucesso(response.body()!!)
                } else {
                    listener.falha(response.message())
                }
            }
        })
    }

    fun criarComentario(comentario: Comentario, path: String, listener: RespostaListener<Comentario>) {

        val userBody = RequestBody.create(MediaType.parse("text/plain"), comentario.user)
        val contentBody = RequestBody.create(MediaType.parse("text/plain"), comentario.content)
        val latBody = RequestBody.create(MediaType.parse("text/plain"), comentario.lat)
        val lngBody = RequestBody.create(MediaType.parse("text/plain"), comentario.lng)

     /*   var pictureBody: RequestBody? = null
        if (!path.isEmpty()) {
            pictureBody = RequestBody.create(MediaType.parse("image/jpg"), File(path))
        }else{
            pictureBody = RequestBody.create(MediaType.parse("image/jpg"), "")
        }*/

        val call = service.criarComentario(userBody, contentBody, latBody, lngBody)
        call.enqueue(object : Callback<Comentario> {
            override fun onFailure(call: Call<Comentario>, t: Throwable) {
                if (!call.isCanceled) {
                    listener.falha(t.toString())
                }
            }

            override fun onResponse(call: Call<Comentario>, response: Response<Comentario>) {
                if (response.isSuccessful) {
                    listener.sucesso(response.body()!!)
                } else {
                    listener.falha(response.message())
                }
            }

        })
    }

    interface RespostaListener<T> {
        fun sucesso(resposta: T)
        fun falha(mensagem: String)
    }

}
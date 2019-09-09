package com.jamessonfaria.projetocomments.retrofit.client

import android.content.Context
import com.jamessonfaria.projetocomments.model.Comentario
import com.jamessonfaria.projetocomments.model.User
import com.jamessonfaria.projetocomments.retrofit.TestRetrofit
import com.jamessonfaria.projetocomments.retrofit.service.TestService
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response

class TestWebClient {

    private val service: TestService

    constructor(ctx: Context) {
        service = TestRetrofit(ctx).getTestService()
    }

    fun login(user: User) : Response<User> {
        val json: String = "{\"user\":{" +
                "\"email\":\"" + user.email + "\"," +
                "\"password\":\"" + user.password + "\"}}"

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)

        val call = service.login(body)
        val resposta = call.execute()
        return resposta
    }

    fun listaComentarios() : Response<List<Comentario>> {
        val call = service.listarComentarios()
        val resposta = call.execute()
        return resposta
    }

}

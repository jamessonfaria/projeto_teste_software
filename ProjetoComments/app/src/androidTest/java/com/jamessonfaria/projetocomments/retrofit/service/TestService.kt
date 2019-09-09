package com.jamessonfaria.projetocomments.retrofit.service

import com.jamessonfaria.projetocomments.model.Comentario
import com.jamessonfaria.projetocomments.model.User
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TestService {

    @POST("users/sign_in.json")
    fun login(@Body user: RequestBody): Call<User>

    @GET("comments.json")
    fun listarComentarios(): Call<List<Comentario>>

}

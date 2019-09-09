package com.jamessonfaria.projetocomments.retrofit.service

import com.jamessonfaria.projetocomments.model.Comentario
import com.jamessonfaria.projetocomments.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ComentarioService {

    @POST("users/sign_in.json")
    fun login(@Body user: RequestBody): Call<User>

    @GET("comments.json")
    fun listarComentarios(): Call<List<Comentario>>

    @DELETE("comments/{id}.json")
    fun delete(@Path("id") id: Int): Call<String>

    @Multipart
    @POST("comments.json")
    fun criarComentario(@Part("comment[user]") user: RequestBody,
                        @Part("comment[content]") content: RequestBody,
                        @Part("comment[lat]") lat: RequestBody,
                        @Part("comment[lng]") lng: RequestBody
                        //@Part("comment[picture]\"; filename=\"imagem.jpg\" ") picture: RequestBody
                        ) : Call<Comentario>
}
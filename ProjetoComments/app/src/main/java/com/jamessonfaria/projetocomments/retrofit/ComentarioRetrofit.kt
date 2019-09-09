package com.jamessonfaria.projetocomments.retrofit

import android.content.Context
import com.github.rodlibs.persistencecookie.PersistentCookieStore
import com.jamessonfaria.projetocomments.retrofit.service.ComentarioService
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit


open class ComentarioRetrofit {

    private val URL_BASE = "http://teste-aula-ios.herokuapp.com"
    protected var retrofit: Retrofit
    private var context: Context
    private var myCookie: PersistentCookieStore


    constructor(ctx: Context) {
        context = ctx
        myCookie = PersistentCookieStore(context)

        var client = configuraHttpClient()
        retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun configuraHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        if (myCookie == null) {
            myCookie = PersistentCookieStore(context)
        }

        val cookieHandler = CookieManager(myCookie, CookiePolicy.ACCEPT_ALL)

        var client = OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                .cookieJar(JavaNetCookieJar(cookieHandler))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        return client
    }

    fun comentarioService() = retrofit!!.create(ComentarioService::class.java)

}
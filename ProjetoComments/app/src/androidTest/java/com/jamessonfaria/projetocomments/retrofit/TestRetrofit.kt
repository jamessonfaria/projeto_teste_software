package com.jamessonfaria.projetocomments.retrofit

import android.content.Context
import com.jamessonfaria.projetocomments.retrofit.service.TestService

class TestRetrofit(ctx: Context) : ComentarioRetrofit(ctx) {

    fun getTestService() =  retrofit.create(TestService::class.java)

}

package com.jamessonfaria.projetocomments.util

import android.content.Context
import android.net.ConnectivityManager
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Util {

    fun isNetworkAvaliabe(context: Context): Boolean{
        val cm = getConnectivityManager(context)
        val info = cm.activeNetworkInfo

        return info != null && info.isConnected ; true ; false
    }

    private fun getConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun formatarData(data: String): String {

        val dataFormatada = LocalDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME)

        var formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        var dataFinal = dataFormatada.format(formato)

        return dataFinal
    }


}


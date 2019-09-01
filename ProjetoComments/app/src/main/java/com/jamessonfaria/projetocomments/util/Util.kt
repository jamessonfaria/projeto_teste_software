package com.jamessonfaria.projetocomments.util

import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Util {

    fun isNetworkAvaliabe(context: Context): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo

        return info != null && info.isConnected ; true ; false
    }

    fun formatarData(data: String): String {

        val dataFormatada = LocalDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME)

        var formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        var dataFinal = dataFormatada.format(formato)

        return dataFinal
    }


}


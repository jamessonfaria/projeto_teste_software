package com.jamessonfaria.projetocomments.util

import android.content.Context
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UtilTest {

    val context: Context = Mockito.mock(Context::class.java)
    val util = Mockito.mock(Util::class.java)

    @Test
    fun deve_ObterDataFormatada() {
        val data = "2019-08-18T20:26:19.228-03:00"
        val resultado = Util.formatarData(data)

        assertNotNull(resultado)
        assertEquals("18/08/2019 20:26:19", resultado)
    }

    @Test
    fun deve_ObterRedeDisponivel(){
        Mockito.`when`(util.isNetworkAvaliabe(context)).thenReturn(true)
        val retorno = util.isNetworkAvaliabe(context)

        assertNotNull(retorno)
        Assert.assertTrue(retorno)
    }
}
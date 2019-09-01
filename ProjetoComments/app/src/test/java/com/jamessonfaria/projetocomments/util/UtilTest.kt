package com.jamessonfaria.projetocomments.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Ignore
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class UtilTest {

    @Test
    fun deve_ObterDataFormatada() {
        val data = "2019-08-18T20:26:19.228-03:00"
        val resultado = Util.formatarData(data)

        assertNotNull(resultado)
        assertEquals("18/08/2019 20:26:19", resultado)
    }
}
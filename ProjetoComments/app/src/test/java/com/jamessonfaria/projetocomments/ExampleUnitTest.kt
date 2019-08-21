package com.jamessonfaria.projetocomments

import com.jamessonfaria.projetocomments.model.Comentario
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.BeforeClass
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun valida(){
        assertEquals(10, 5+5)
    }

    @Test
    fun testa(){
        val c: Comentario = Comentario(1,"", "", "", "", "", "", "")
        assertEquals(2, 2);
    }
}

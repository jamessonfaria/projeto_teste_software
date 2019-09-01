package com.jamessonfaria.projetocomments.adapter

import com.jamessonfaria.projetocomments.model.Comentario
import org.junit.Test

import org.junit.Assert.*

class AdapterComentariosTest {

    val listaComentarios: ArrayList<Comentario> = ArrayList<Comentario>()

    @Test
    fun deve_ObterListaComentarios() {
        listaComentarios.add(Comentario(1, "jamesson", "Teste", "","","","", ""))
        listaComentarios.add(Comentario(2, "fred", "Teste 2222", "","","","", ""))
        listaComentarios.add(Comentario(3, "amanda", "Teste 33333", "","","","", ""))
        assertEquals("jamesson", listaComentarios.get(0).user)
        assertEquals("fred", listaComentarios.get(1).user)
        assertEquals("amanda", listaComentarios.get(2).user)
        assertEquals(3, listaComentarios.size)
    }

}
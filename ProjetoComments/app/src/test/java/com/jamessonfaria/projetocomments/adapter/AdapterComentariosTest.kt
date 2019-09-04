package com.jamessonfaria.projetocomments.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import com.jamessonfaria.projetocomments.activity.ListCommentsActivity
import com.jamessonfaria.projetocomments.model.Comentario
import com.jamessonfaria.projetocomments.util.ItemClicked
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.matchers.InstanceOf
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AdapterComentariosTest {

    var adapterComentarios: AdapterComentarios = Mockito.mock(AdapterComentarios::class.java)
    val listaComentarios: ArrayList<Comentario> = ArrayList<Comentario>()
    val viewGroup: ViewGroup = Mockito.mock(ViewGroup::class.java)
    val view: RecyclerView.ViewHolder = Mockito.mock(RecyclerView.ViewHolder::class.java)
    val filter: Filter = Mockito.mock(Filter::class.java)

    @Before
    fun setup(){
        listaComentarios.add(Comentario(1, "jamesson", "Teste", "","","","", ""))
        listaComentarios.add(Comentario(2, "fred", "Teste 2222", "","","","", ""))
        listaComentarios.add(Comentario(3, "amanda", "Teste 33333", "","","","", ""))
    }

    @Test
    fun deve_ObterQtdRegistrosDaLista(){
        val itemCount = adapterComentarios?.itemCount
        assertEquals(0, itemCount)
    }

    @Test
    fun deve_RealizarBindViewHolder(){
        adapterComentarios.bindViewHolder(view, 0)
        Mockito.verify(adapterComentarios, Mockito.times(1)).bindViewHolder(view, 0)
    }

    @Test
    fun deve_ObterViewHolder(){
        Mockito.`when`(adapterComentarios?.createViewHolder(viewGroup, 0)).thenReturn(view)
        val recycleView = adapterComentarios?.createViewHolder(viewGroup, 0)
        assertEquals(recycleView?.itemViewType, 0)
    }

    @Test
    fun deve_ObterListaFiltrada(){
        val filterRes = adapterComentarios?.filter
        Mockito.verify(adapterComentarios, Mockito.times(1)).filter
    }

    @Test
    fun deve_ObterListaComentarios() {
        assertEquals("jamesson", listaComentarios.get(0).user)
        assertEquals("fred", listaComentarios.get(1).user)
        assertEquals("amanda", listaComentarios.get(2).user)
        assertEquals(3, listaComentarios.size)
    }

}
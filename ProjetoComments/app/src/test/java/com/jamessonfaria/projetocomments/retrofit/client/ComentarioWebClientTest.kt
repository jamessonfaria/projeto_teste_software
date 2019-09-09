package com.jamessonfaria.projetocomments.retrofit.client

import com.jamessonfaria.projetocomments.model.Comentario
import com.jamessonfaria.projetocomments.model.User
import com.jamessonfaria.projetocomments.retrofit.client.ComentarioWebClient.RespostaListener
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ComentarioWebClientTest {

    @Mock
    val cb = Mockito.mock(ComentarioWebClient.RespostaListener::class.java)
    @Mock
    val client = Mockito.mock(ComentarioWebClient::class.java)

    @Test
    fun deve_RealizarLogin() {
        var user = User("crystian@roadmaps.com.br", "12345678")
        client.login(user, cb as RespostaListener<User>)
        Mockito.verify(client, Mockito.times(1)).login(user, cb as RespostaListener<User>)
    }

    @Test
    fun deve_RemoverComentario(){
        client.delete(1, cb as RespostaListener<String>)
        client.delete(1, cb)
        Mockito.verify(client, Mockito.times(2)).delete(1, cb)
    }

    @Test
    fun deve_ReceberOsComentarios(){
        client.getComentarios(cb as RespostaListener<List<Comentario>>)
        client.getComentarios(cb)
        client.getComentarios(cb)
        Mockito.verify(client, Mockito.times(3)).getComentarios(cb)
    }
}
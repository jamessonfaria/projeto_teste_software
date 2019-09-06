package com.jamessonfaria.projetocomments.util

import com.jamessonfaria.projetocomments.model.Comentario
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class NetworkTest {

    val network: Network = Mockito.mock(Network::class.java)
    val cb: Network.HttpCallback = Mockito.mock(Network.HttpCallback::class.java)

    @Test
    fun deve_RealizarLogin(){
        network.login("crystian@roadmaps.com.br", "12345678", cb)
        Mockito.verify(network, Mockito.times(1)).login("crystian@roadmaps.com.br", "12345678", cb)
    }

    @Test
    fun deve_RemoverComentario(){
        network.delete(1, cb)
        network.delete(1, cb)
        Mockito.verify(network, Mockito.times(2)).delete(1, cb)
    }

    @Test
    fun deve_ReceberOsComentarios(){
        network.getComments(cb)
        network.getComments(cb)
        network.getComments(cb)
        Mockito.verify(network, Mockito.times(3)).getComments(cb)
    }

    @Test
    fun deve_PostarComentarioComFoto(){
        var imagem = "http://www.gravatar.com/avatar/ea89655cfc41de232d8be85245bb6a72"
        var comentario = Comentario(0,"Jamesson", "Teste",
                "", imagem,
                "","88.0", "-40.4")

        network.postCommentWithPicture(comentario, imagem, cb)
        Mockito.verify(network, Mockito.times(1)).postCommentWithPicture(comentario, imagem, cb)
    }


}
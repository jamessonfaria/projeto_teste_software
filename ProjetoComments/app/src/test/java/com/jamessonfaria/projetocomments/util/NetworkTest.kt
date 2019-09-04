package com.jamessonfaria.projetocomments.util

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

}
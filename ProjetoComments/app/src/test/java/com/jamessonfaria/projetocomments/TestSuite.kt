package com.jamessonfaria.projetocomments


import com.jamessonfaria.projetocomments.adapter.AdapterComentariosTest
import com.jamessonfaria.projetocomments.retrofit.client.ComentarioWebClientTest
import com.jamessonfaria.projetocomments.util.UtilTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        UtilTest::class,
        ComentarioWebClientTest::class,
        AdapterComentariosTest::class
)
class TestSuite
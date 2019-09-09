package com.jamessonfaria.projetocomments.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.jamessonfaria.projetocomments.R
import com.jamessonfaria.projetocomments.retrofit.client.TestWebClient
import org.hamcrest.Matchers.allOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListCommentsTelaTest {

    @Rule
    @JvmField
    var activity = ActivityTestRule(LoginActivity::class.java, true, true)

    @Before
    fun realizarLoginNaTela(){
        onView(allOf(withId(R.id.edtEmail), isDisplayed()))
                .perform(replaceText("crystian@roadmaps.com.br"),
                        closeSoftKeyboard())

        onView(allOf(withId(R.id.edtSenha), isDisplayed()))
                .perform(replaceText("12345678"),
                        closeSoftKeyboard())

        onView(allOf(withId(R.id.button), withText("Acessar"), isDisplayed()))
                .perform(click())

        onView(allOf(withText("Comments"), isDisplayed()))
                .check(matches(withText("Comments")))
    }

    @Test
    fun deve_ValidarCamposNaTelaListaComentarios() {



        onView(withText("Comments"))
                .check(matches(isDisplayed()))

        onView(withId(R.id.rvListaComentarios))
                .check(matches(isDisplayed()))

        onView(withId(R.id.action_search))
                .check(matches(isDisplayed()))

        onView(withId(R.id.fab))
                .check(matches(isDisplayed()))

    }

    @Test
    fun deve_RetornarPeloMenosUmComentario() {
        val client = TestWebClient(activity.activity)
        val listaComentarios = client.listaComentarios()
        Assert.assertTrue(listaComentarios.body()!!.size > 0)
    }

    @Test
    fun deve_RealizarPesquisaNaListaDeComentarios() {
        onView(allOf(withId(R.id.action_search), withContentDescription("Pesquisar"),
                        isDisplayed())).perform(click())

        onView(allOf(withId(R.id.search_src_text), isDisplayed()))
                .perform(replaceText("teste"),
                        closeSoftKeyboard())

        onView(allOf(withId(R.id.txtNome), withText("test 10"),
                isDisplayed())).check(matches(withText("test 10")))
    }

}
package com.jamessonfaria.projetocomments.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.jamessonfaria.projetocomments.R
import com.jamessonfaria.projetocomments.model.User
import com.jamessonfaria.projetocomments.retrofit.client.TestWebClient
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ListCommentsTelaTest {

    @Rule
    @JvmField
    var activity = ActivityTestRule(ListCommentsActivity::class.java, true, true)

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


}
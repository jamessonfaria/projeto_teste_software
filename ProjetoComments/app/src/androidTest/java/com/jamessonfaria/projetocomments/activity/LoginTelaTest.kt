package com.jamessonfaria.projetocomments.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.github.rodlibs.persistencecookie.PersistentCookieStore
import com.jamessonfaria.projetocomments.model.User
import com.jamessonfaria.projetocomments.retrofit.client.TestWebClient
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginTelaTest {

    @Rule
    @JvmField
    var activity = ActivityTestRule(LoginActivity::class.java, true, true)

    @Before
    fun remove_Cookies(){
        var myCookie = PersistentCookieStore(activity.activity)
        myCookie.removeAll()
    }

    @Test
    fun deve_ValidarCamposNaTelaLogin() {

        onView(withText("Acesse sua Conta"))
                .check(matches(isDisplayed()))

        onView(withHint("Email"))
                .check(matches(isDisplayed()))

        onView(withHint("Senha"))
                .check(matches(isDisplayed()))

        onView(withText("Acessar"))
                .check(matches(isDisplayed()))

    }

    @Test
    fun deve_RealizarLoginNaTelaLogin() {
        val client = TestWebClient(activity.activity)
        val resLogin = client.login(User("crystian@roadmaps.com.br", "12345678"))

        if (!resLogin.isSuccessful) {
            Assert.fail("Problema ao realizar o login")
        } else {
            Assert.assertEquals(resLogin.code(), 201)
        }
    }

    @Test
    fun deve_NaoRealizarLoginNaTelaLogin() {
        val client = TestWebClient(activity.activity)
        val resLogin = client.login(User("jame", "123"))
        Assert.assertNotEquals(resLogin.code(), 201)
    }

}


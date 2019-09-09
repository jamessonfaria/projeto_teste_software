package com.jamessonfaria.projetocomments.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.github.rodlibs.persistencecookie.PersistentCookieStore
import com.jamessonfaria.projetocomments.R
import org.hamcrest.Matchers.allOf
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
    fun nao_Deve_RealizarLoginNaTelaLogin() {

        onView(allOf(withId(R.id.edtEmail), isDisplayed()))
                .perform(replaceText("jamesson@roadmaps.com.br"),
                        closeSoftKeyboard())

        onView(allOf(withId(R.id.edtSenha), isDisplayed()))
                .perform(replaceText("12345678910"),
                        closeSoftKeyboard())

        onView(allOf(withId(R.id.button), withText("Acessar"), isDisplayed()))
                .perform(click())

        onView(
                allOf(withId(android.R.id.message), withText("Usuário ou Senha Inválidos."),
                        isDisplayed()))
                .check(matches(withText("Usuário ou Senha Inválidos.")))
    }

}


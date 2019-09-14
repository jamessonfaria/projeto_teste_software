package com.jamessonfaria.projetocomments.activity

import android.R.attr.x
import android.R.attr.y
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.uiautomator.UiDevice
import android.view.View
import android.view.ViewGroup
import com.jamessonfaria.projetocomments.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import android.support.test.uiautomator.UiSelector
import android.support.test.uiautomator.UiObject



class CreateCommentsTelaTest {

    @Rule
    @JvmField
    var activity = ActivityTestRule(LoginActivity::class.java, true, true)

    @Rule
    @JvmField
    var mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.ACCESS_COARSE_LOCATION")

    @Before
    fun realizarLoginNaTela() {
        onView(allOf(withId(R.id.edtEmail), isDisplayed()))
                .perform(replaceText("crystian@roadmaps.com.br"),
                        closeSoftKeyboard())

        onView(allOf(withId(R.id.edtSenha), isDisplayed()))
                .perform(replaceText("12345678"),
                        closeSoftKeyboard())

        onView(allOf(withId(R.id.button), withText("Acessar"), isDisplayed()))
                .perform(click())

        onView(allOf(withText("Comments"), isDisplayed()))
                .check(ViewAssertions.matches(withText("Comments")))
    }

    @Test
    fun deve_CriarComentario() {
        onView(allOf(withId(R.id.fab), isDisplayed()))
                .perform(click())

        Thread.sleep(5000)

        onView(allOf(withId(R.id.txtNome), isDisplayed()))
                .perform(replaceText("teste automatizado"),
                        closeSoftKeyboard())

        onView(allOf(withId(R.id.txtDescricao), isDisplayed()))
                .perform(replaceText("teste automatizado"), closeSoftKeyboard())

        onView(allOf(withId(R.id.fabSalvar), isDisplayed()))
                .perform(click())

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.click(x, y)

        onView(allOf(withId(android.R.id.message), withText("Comentário criado com sucesso."),
                isDisplayed()))
                .check(matches(withText("Comentário criado com sucesso.")))

    }

}
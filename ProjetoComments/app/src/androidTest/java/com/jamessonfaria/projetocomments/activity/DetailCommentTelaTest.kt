package com.jamessonfaria.projetocomments.activity

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.uiautomator.UiDevice
import android.support.v7.widget.RecyclerView
import com.jamessonfaria.projetocomments.R
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailCommentTelaTest {
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
                .perform(ViewActions.replaceText("crystian@roadmaps.com.br"),
                        ViewActions.closeSoftKeyboard())

        onView(allOf(withId(R.id.edtSenha), isDisplayed()))
                .perform(ViewActions.replaceText("12345678"),
                        ViewActions.closeSoftKeyboard())

        onView(allOf(withId(R.id.button), withText("Acessar"), isDisplayed()))
                .perform(ViewActions.click())

        onView(allOf(withText("Comments"), isDisplayed()))
                .check(matches(withText("Comments")))
    }

    @Test
    fun deve_RemoverComentario() {
        onView(allOf(withId(R.id.rvListaComentarios)))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        onView(allOf(withId(R.id.btnDeletar), withText("DELETAR"), isDisplayed()))
                .perform(ViewActions.click())

        /* onView(allOf(withId(android.R.id.button1), withText("OK")))
                 .perform(ViewActions.scrollTo(), ViewActions.click())*/

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.click(android.R.attr.x, android.R.attr.y)

        onView(allOf(withId(android.R.id.message), withText("Comentário removido com sucesso."),
                isDisplayed())).check(matches(withText("Comentário removido com sucesso.")))
    }
}
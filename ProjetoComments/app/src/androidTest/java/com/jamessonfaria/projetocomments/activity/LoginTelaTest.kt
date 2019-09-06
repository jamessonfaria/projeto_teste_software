package com.jamessonfaria.projetocomments.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class LoginTelaTest {

    @Rule
    @JvmField
    var activity: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java,
            true, true)

    @Test
    fun deve_AparecerTelaLogin(){
        onView(withText("Acesse sua Conta"))
                .check(matches(isDisplayed()))
    }

}

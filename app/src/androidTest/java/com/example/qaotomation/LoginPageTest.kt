package com.example.qaotomation

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import com.example.qaotomation.util.ConstantsTest
import java.util.logging.Handler


class LoginPageTest {

    //Launch the main activity
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val buttonLogin = withId(R.id.buttonLogin)
    private val buttonLoginUser = withId(R.id.buttonLoginUser)
    private val editTextEmail = withId(R.id.editTextEmail)
    private val editTextPw = withId(R.id.editTextPassword)
    private val itemPageDate = withId(R.id.editTextDatePicker)

    //Try to login with not valid email
    @Test
    fun loginWithNotValidEmail(){
        onView(buttonLogin).perform(ViewActions.click())

        onView(editTextEmail)
            .perform((replaceText(ConstantsTest.LOGIN_EMAIL_NOTVALID)))

        onView(editTextPw)
            .perform(replaceText(ConstantsTest.LOGIN_PASSWORD_VALID))

        onView(buttonLoginUser).perform(ViewActions.click())

        //error log
        Espresso.onView(ViewMatchers.withText("Girdiğiniz email geçersizdir."))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //Try to login with wrong password
    @Test
    fun loginWithWrongEmail(){
        onView(buttonLogin).perform(ViewActions.click())

        onView(editTextEmail)
            .perform((replaceText(ConstantsTest.LOGIN_EMAIL_NOTCORRECT)))

        onView(editTextPw)
            .perform(replaceText(ConstantsTest.LOGIN_PASSWORD_VALID))

        onView(buttonLoginUser).perform(ViewActions.click())

        //error log
        Espresso.onView(ViewMatchers.withText("Email veya şifre yanlış."))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //Try to login with wrong email
    @Test
    fun loginWithValidEmailWrongPassword(){
        onView(buttonLogin).perform(ViewActions.click())

        onView(editTextEmail)
            .perform((replaceText(ConstantsTest.LOGIN_EMAIL_VALID)))

        onView(editTextPw)
            .perform(replaceText(ConstantsTest.LOGIN_PASSWORD_NOTCORRECT))

        onView(buttonLoginUser).perform(ViewActions.click())

        //error log
        Espresso.onView(ViewMatchers.withText("Email veya şifre yanlış."))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //Try to login successfully
    @Test
    fun loginWithValidEmail(){
        onView(buttonLogin).perform(ViewActions.click())

        onView(editTextEmail)
            .perform((replaceText(ConstantsTest.LOGIN_EMAIL_VALID)))

        onView(editTextPw)
            .perform(replaceText(ConstantsTest.LOGIN_PASSWORD_VALID))

        onView(buttonLoginUser).perform(ViewActions.click())
        onView(itemPageDate).check(matches(isDisplayed()))
    }

}
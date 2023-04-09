package com.example.qaotomation

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.qaotomation.util.ConstantsTest
import org.junit.Rule
import org.junit.Test

class SignUpPageTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val buttonSignUp = ViewMatchers.withId(R.id.buttonSignup)
    private val buttonSignupUser = ViewMatchers.withId(R.id.buttonSignupUser)
    private val editTextEmail = ViewMatchers.withId(R.id.editTextEmail)
    private val editTextPw = ViewMatchers.withId(R.id.editTextPassword)
    private val editTextRePw = ViewMatchers.withId(R.id.editTextRePassword)

    //Try to signup with not valid email
    @Test
    fun signUpWithNotValidEmail(){
        Espresso.onView(buttonSignUp).perform(ViewActions.click())

        Espresso.onView(editTextEmail)
            .perform((ViewActions.replaceText(ConstantsTest.SIGNUP_EMAIL_NOTVALID)))

        Espresso.onView(editTextPw)
            .perform(ViewActions.replaceText(ConstantsTest.SIGNUP_PASSWORD_VALID))

        Espresso.onView(editTextRePw)
            .perform(ViewActions.replaceText(ConstantsTest.SIGNUP_PASSWORD_VALID))

        Espresso.onView(buttonSignupUser).perform(ViewActions.click())

        //error log
        Espresso.onView(ViewMatchers.withText("Girdiğiniz email geçersizdir. Lütfen geçerli bir email girin."))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //Try to signup with valid email not valid password
    @Test
    fun signUpWithValidEmailNotValidPassword(){
        Espresso.onView(buttonSignUp).perform(ViewActions.click())

        Espresso.onView(editTextEmail)
            .perform((ViewActions.replaceText(ConstantsTest.LOGIN_EMAIL_VALID)))

        Espresso.onView(editTextPw)
            .perform(ViewActions.replaceText(ConstantsTest.SIGNUP_EMAIL_NOTVALID))

        Espresso.onView(editTextRePw)
            .perform(ViewActions.replaceText(ConstantsTest.SIGNUP_EMAIL_NOTVALID))

        Espresso.onView(buttonSignupUser).perform(ViewActions.click())

        //error log
        Espresso.onView(ViewMatchers.withText("En az sekiz karakterden oluşan bir şifre belirleyin."))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //Try to signup with valid email valid password but not matched password
    @Test
    fun signUpWithValidEmailValidPasswordNotMatchedPassword(){
        Espresso.onView(buttonSignUp).perform(ViewActions.click())

        Espresso.onView(editTextEmail)
            .perform((ViewActions.replaceText(ConstantsTest.SIGNUP_EMAIL_VALID)))

        Espresso.onView(editTextPw)
            .perform(ViewActions.replaceText(ConstantsTest.SIGNUP_PASSWORD_VALID))

        Espresso.onView(editTextRePw)
            .perform(ViewActions.replaceText(ConstantsTest.SIGNUP_PASSWORD_NOTCORRECT))

        Espresso.onView(buttonSignupUser).perform(ViewActions.click())

        //error log
        Espresso.onView(ViewMatchers.withText("Şifreler eşleşmiyor, lütfen tekrar kontrol edin."))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //Try to signup with already exist email
    @Test
    fun signUpWithExistEmail(){
        Espresso.onView(buttonSignUp).perform(ViewActions.click())

        Espresso.onView(editTextEmail)
            .perform((ViewActions.replaceText(ConstantsTest.LOGIN_EMAIL_VALID)))

        Espresso.onView(editTextPw)
            .perform(ViewActions.replaceText(ConstantsTest.SIGNUP_PASSWORD_VALID))

        Espresso.onView(editTextRePw)
            .perform(ViewActions.replaceText(ConstantsTest.SIGNUP_PASSWORD_VALID))

        Espresso.onView(buttonSignupUser).perform(ViewActions.click())

        //error log will be checked
        Espresso.onView(ViewMatchers.withText("Bu mail bir kullanıcı tarafından kullanılıyor."))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    //Try to successfully signup
    @Test
    fun signUpWithValidEmail(){
        Espresso.onView(buttonSignUp).perform(ViewActions.click())

        Espresso.onView(editTextEmail)
            .perform((ViewActions.replaceText(ConstantsTest.SIGNUP_EMAIL_VALID)))

        Espresso.onView(editTextPw)
            .perform(ViewActions.replaceText(ConstantsTest.SIGNUP_PASSWORD_VALID))

        Espresso.onView(editTextRePw)
            .perform(ViewActions.replaceText(ConstantsTest.SIGNUP_PASSWORD_VALID))

        Espresso.onView(buttonSignupUser).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Başarılı şekilde kayıt oldun."))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
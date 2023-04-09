package com.example.qaotomation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test


class MainPageTest {

    //this variable will global for all fun that we will create
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val buttonLogin = withId(R.id.buttonLogin)
    private val buttonSignUp = withId(R.id.buttonSignup)
    private val mainLayout = withId(R.id.main_layout)

    //check if MainActivity is displayed
    @Test
    fun checkActivityVisibility(){
        onView(mainLayout).check(matches(isDisplayed()))
    }

    //check if Login and SignUp buttons are displayed
    @Test
    fun checkIfButtonsAreDisplayed(){
        //check login button
        onView(buttonLogin).check(matches(isDisplayed()))

        //check signUp button
        onView(buttonSignUp).check(matches(isDisplayed()))
    }

}
package com.example.qaotomation

import android.view.View
import android.widget.DatePicker
import androidx.databinding.adapters.CalendarViewBindingAdapter.setDate
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.qaotomation.util.ConstantsTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.PickerActions


@RunWith(AndroidJUnit4ClassRunner::class)
class ItemListingPageTest{

    //Launch the main activity
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val itemDate = ViewMatchers.withId(R.id.editTextDatePicker)
    private val itemImage = ViewMatchers.withId(R.id.imageViewItem)
    private val itemTitle = ViewMatchers.withId(R.id.textViewTitle)
    private val itemCaption = ViewMatchers.withId(R.id.textViewCaption)

    private val loginPageTest = LoginPageTest()

    //get list items with index (e.g. boutique's image, title and caption)
    fun withIndex(matcher: Matcher<View?>, index: Int): Matcher<View?>? {
        return object : TypeSafeMatcher<View?>() {
            var currentIndex = 0
            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View?): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }

    //check if boutique items are displayed
    @Test
    fun isItemListsDataVisible(){
        loginPageTest.loginWithValidEmail()

        onView(itemDate).check(matches(isDisplayed()))
        onView(withIndex(itemImage, 0)).check(matches(isDisplayed()))
        onView(withIndex(itemTitle, 0)).check(matches(isDisplayed()))
        onView(withIndex(itemCaption, 0)).check(matches(isDisplayed()))
    }

    //set date and check the date is displayed
    @Test
    fun setNewDateOnItemListingPage(){
        loginPageTest.loginWithValidEmail()

        onView(itemDate).perform(click())

        onView(isAssignableFrom(DatePicker::class.java)).perform(PickerActions.setDate(1980,  11, 3))

        onView(ViewMatchers.withText("OK"))
            .perform(click())

        onView(ViewMatchers.withText("03/11/1980"))
            .check(matches(isDisplayed()))
    }
}
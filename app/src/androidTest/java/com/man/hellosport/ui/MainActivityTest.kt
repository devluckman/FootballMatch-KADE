package com.man.hellosport.ui

import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.man.hellosport.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    // Submission 4 =>
    // Instruments Testing untuk fitur pencarian
    @Test
    fun testSearchMatchBehaviour() {
        //display
        onView(withId(textInputSearch)).check(matches(isDisplayed()))

        // input text
        onView(withId(textInputSearch)).perform(typeText("Barcelona"))

        //press enter
        onView(withId(textInputSearch)).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        // waiting data from API
        Thread.sleep(4000)

        // Result Data
        onView(withId(rvSearch))
            .check(matches(isDisplayed()))

        // Click position 0
        onView(withId(rvSearch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // display
        onView(withId(imgFavorite))
            .check(matches(isDisplayed()))

        // click add to favorite
        onView(withId(imgFavorite)).perform(click())

    }


}
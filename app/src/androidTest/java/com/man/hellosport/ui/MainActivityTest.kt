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

    @Test
    fun testRecyclerViewBehaviour() {
        Thread.sleep(4000)

        onView(withId(rvMatchEvent)).check(matches(isDisplayed()))

        onView(withId(rvMatchEvent)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )

        onView(withId(rvMatchEvent)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                10,
                click()
            )
        )

    }

    @Test
    fun testAppBehaviour() {
        onView(withId(spinner))
            .check(matches(isDisplayed()))

        onView(withId(spinner)).perform(click())

        onView(withText("Spanish La Liga")).perform(click())

        Thread.sleep(4000)

        onView(withId(rvMatchEvent))
            .check(matches(isDisplayed()))

        onView(withId(rvMatchEvent)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                10,
                click()
            )
        )

        onView(withId(imgFavorite))
            .check(matches(isDisplayed()))

        onView(withId(imgFavorite)).perform(click())

        pressBack()

    }

    // Submission 4 =>
//    Instruments Testing untuk fitur pencarian
    @Test
    fun testSearchMatchBehaviour() {
        onView(withId(textInputSearch)).check(matches(isDisplayed()))

        onView(withId(textInputSearch)).perform(typeText("Barcelona"))

        onView(withId(textInputSearch)).perform(pressKey(KeyEvent.KEYCODE_ENTER))

        Thread.sleep(4000)

        onView(withId(rvSearch))
            .check(matches(isDisplayed()))

        onView(withId(rvSearch)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(imgFavorite))
            .check(matches(isDisplayed()))

        onView(withId(imgFavorite)).perform(click())

    }


}
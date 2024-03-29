package com.app.learnkotlin

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavDrawerActivityTest{
    @Rule
    @JvmField
    val itemsActivity = ActivityTestRule(NavDrawerActivity::class.java)

    @Test
    fun selectNoteAfterNavigationDrawerChange(){

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_home))

        val coursePosition = 0
        onView(withId(R.id.listItems)).perform(RecyclerViewActions.actionOnItemAtPosition<NoteRecyclerAdapter.ViewHolder>(coursePosition,click()))
//        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
//        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_notes)

        val notePosition = 0
//        onView(withId(R.id.listItems)).perform(RecyclerViewActions.actionOnItemAtPosition<NoteRecyclerAdapter.ViewHolder>(notePosition,click()))

        val note = DataManager.notes[notePosition]
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString(note.course?.courseTitle))))
        onView(withId(R.id.titleText)).check(matches(withText(containsString(note.course?.courseTitle))))

    }

}
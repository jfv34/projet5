package com.vincler.jf.projet5;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void toolbar_is_displayed() {
        onView(withId(R.id.activity_main_toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void horizontals_slides() {

        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());

        onView(withId(R.id.activity_main_viewpager)).perform(swipeRight());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeRight());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeRight());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeRight());

    }

    @Test
    public void vertical_slides() {

        onView(withId(R.id.activity_main_viewpager)).perform(swipeUp());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeUp());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeUp());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeUp());

        onView(withId(R.id.activity_main_viewpager)).perform(swipeDown());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeDown());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeDown());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeDown());

    }


    @Test
    public void click_search() {

        onView(withId(R.id.menu_activity_main_search)).perform(click());

    }
}

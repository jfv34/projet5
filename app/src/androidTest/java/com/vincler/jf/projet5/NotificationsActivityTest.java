package com.vincler.jf.projet5;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class NotificationsActivityTest {

    @Rule
    public ActivityTestRule<NotificationsActivity> activityTestRule =
            new ActivityTestRule<>(NotificationsActivity.class);


    @Test
    public void all_checkboxes_checked() {
        onView(withId(R.id.activity_search_checkbox_1)).perform(click());
        onView(withId(R.id.activity_search_checkbox_2)).perform(click());
        onView(withId(R.id.activity_search_checkbox_3)).perform(click());
        onView(withId(R.id.activity_search_checkbox_4)).perform(click());
        onView(withId(R.id.activity_search_checkbox_5)).perform(click());
        onView(withId(R.id.activity_search_checkbox_6)).perform(click());
    }

    @Test
    public void write_query() {
        onView(withId(R.id.activity_search_query)).perform(typeText("test"));
    }

    @Test
    public void switch_checked_after_query_and_checkbox_checked() {
        onView(withId(R.id.activity_search_query)).perform(typeText("test"));
        onView(withId(R.id.activity_search_checkbox_1)).perform(click());
        onView(withId(R.id.activity_notifications_switch)).perform(click());
    }

    @Test
    public void switch_checked_whithout_query() {
        onView(withId(R.id.activity_search_checkbox_1)).perform(click());
        onView(withId(R.id.activity_notifications_switch)).perform(click());
        //onView(withText(R.string.search_query_term)).inRoot(withDecorView(not(NotificationsActivity.class.getActivity().getWindows().getDecorView())).check(matches(isDisplayed()));
    }

    // onView(withText(R.string.toast_text)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

    @Test
    public void switch_checked_whithout_checkbox_checked() {
        onView(withId(R.id.activity_search_query)).perform(typeText("test"));
        onView(withId(R.id.activity_notifications_switch)).perform(click());
    }


}


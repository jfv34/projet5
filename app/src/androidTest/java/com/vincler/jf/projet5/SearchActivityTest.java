package com.vincler.jf.projet5;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class SearchActivityTest {

    @Rule
    public ActivityTestRule<SearchActivity> activityTestRule =
            new ActivityTestRule<>(SearchActivity.class);


    @Test
    public void button_clicked() {

        onView(withId(R.id.activity_search_button)).perform(click());
        // onView(withText(R.string.search_query_term)).inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void write_query() {
        onView(withId(R.id.activity_search_query)).perform(typeText("test"));
    }

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
    public void switch_checked_whithout_query() {
        onView(withId(R.id.activity_search_checkbox_1)).perform(click());
        onView(withId(R.id.activity_search_button)).perform(click());
    }

    @Test
    public void switch_checked_after_query_and_checkbox_checked() {
        onView(withId(R.id.activity_search_query)).perform(typeText("world"));
        onView(withId(R.id.activity_search_checkbox_1)).perform(click());
        onView(withId(R.id.activity_search_button)).perform(click());
    }


    @Test
    public void arrowdown_left_for_datePicker_clicked() {

        onView(withId(R.id.activity_search_arrowdown_left_bt)).perform(click());
    }

    @Test
    public void arrowdown_right_for_datePicker_clicked() {

        onView(withId(R.id.activity_search_arrowdown_right_bt)).perform(click());
    }

    @Test
    public void datePicker_completee() {
        onView(withId(R.id.activity_search_arrowdown_left_bt)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2017, 6, 30));
        Espresso.pressBack();
        onView(withId(R.id.activity_search_checkbox_1)).perform(click());

    }


}

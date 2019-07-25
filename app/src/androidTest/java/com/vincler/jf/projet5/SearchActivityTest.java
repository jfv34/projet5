package com.vincler.jf.projet5;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

public class SearchActivityTest {

    @Rule
    public ActivityTestRule<SearchActivity> activityTestRule =
            new ActivityTestRule<>(SearchActivity.class);

    @Test
    public void toolbar_is_displayed() {
        onView(withId(R.id.activity_search_toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void button_clicked() {

        onView(withId(R.id.activity_search_button)).perform(click());

        SearchActivityPresenter presenter = new SearchActivityPresenter();
        Assert.assertEquals(2, presenter.getError());
    }

    @Test
    public void write_query() {
        onView(withId(R.id.activity_search_query)).perform(typeText("test"));
        onView(withId(R.id.activity_search_query)).check(matches(withText("test")));

    }

    @Test
    public void all_checkboxes_checked() {
        onView(withId(R.id.activity_search_checkbox_1)).perform(click());
        onView(withId(R.id.activity_search_checkbox_2)).perform(click());
        onView(withId(R.id.activity_search_checkbox_3)).perform(click());
        onView(withId(R.id.activity_search_checkbox_4)).perform(click());
        onView(withId(R.id.activity_search_checkbox_5)).perform(click());
        onView(withId(R.id.activity_search_checkbox_6)).perform(click());

        onView(withId(R.id.activity_search_checkbox_1)).check(matches(isChecked()));
        onView(withId(R.id.activity_search_checkbox_2)).check(matches(isChecked()));
        onView(withId(R.id.activity_search_checkbox_3)).check(matches(isChecked()));
        onView(withId(R.id.activity_search_checkbox_4)).check(matches(isChecked()));
        onView(withId(R.id.activity_search_checkbox_5)).check(matches(isChecked()));
        onView(withId(R.id.activity_search_checkbox_6)).check(matches(isChecked()));


    }

    @Test
    public void switch_checked_whithout_query() {
        onView(withId(R.id.activity_search_checkbox_1)).perform(click());
        onView(withId(R.id.activity_search_button)).perform(click());
        onView(withText(R.string.enterAtLeastOneKeyWord)).inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void switch_checked_after_query_and_checkbox_checked() {
        onView(withId(R.id.activity_search_query)).perform(typeText("world"));
        onView(withId(R.id.activity_search_checkbox_1)).perform(click());
        onView(withId(R.id.activity_search_button)).perform(click());

        onView(withId(R.id.activity_search_query)).check(matches(withText("world")));
        onView(withId(R.id.activity_search_checkbox_1)).check(matches(isChecked()));
        SystemClock.sleep(4000);
        Intents.init();
        intended(hasComponent(ResultSearchActivity.class.getName()));


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

        SearchActivityPresenter presenter = new SearchActivityPresenter();
        Assert.assertEquals("30/06/2017", presenter.getDateBeginDisplayed());
    }


}

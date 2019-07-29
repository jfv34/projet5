package com.vincler.jf.projet5;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

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

    @Test
    public void various_tests_1() {

        onView(withId(R.id.menu_activity_main_search)).perform(click());
        onView(withId(R.id.activity_search_query)).perform(typeText("company"));
        onView(withId(R.id.activity_search_checkbox_2)).perform(click());
        onView(withId(R.id.activity_search_checkbox_3)).perform(click());
        onView(withId(R.id.activity_search_checkbox_4)).perform(click());
        onView(withId(R.id.activity_search_button)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.resultSearch_recyclerView)).perform(swipeUp());
        onView(withId(R.id.resultSearch_recyclerView)).perform(swipeUp());
        onData(allOf(is(instanceOf(String.class)), is("c"))).perform(click());
        // onView(withText("c")).check(matches(isDisplayed()));

    }

    @Test
    public void various_tests_2() {
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeUp());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeUp());
    }

    @Test
    public void various_tests_3() {

        onView(withId(R.id.activity_main_drawer_layout)).perform(DrawerActions.open());
        SystemClock.sleep(4000);
        onView(withText("Arts")).perform(click());
        //onView(withId(R.id.activity_main_drawer_3)).perform(click());


    }

    @Test
    public void various_tests_4() {
        onView(withId(R.id.menu_activity_main_search)).perform(click());
        onView(withId(R.id.activity_search_arrowdown_left_bt)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2017, 6, 30));
        Espresso.pressBack();
        onView(withId(R.id.activity_search_arrowdown_right_bt)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2018, 7, 31));
        Espresso.pressBack();
        onView(withId(R.id.activity_search_checkbox_1)).perform(click());
        onView(withId(R.id.activity_search_query)).perform(typeText("movies"));
        onView(withId(R.id.activity_search_arrowdown_left_bt)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2014, 5, 15));
        Espresso.pressBack();
        onView(withId(R.id.activity_search_arrowdown_right_bt)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2019, 6, 30));
        Espresso.pressBack();
        onView(withId(R.id.activity_search_button)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.resultSearch_recyclerView)).perform(swipeUp());
        onView(withId(R.id.resultSearch_recyclerView)).perform(swipeUp());
    }

    @Test
    public void various_tests_5() {

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Notifications")).perform(click());
        onView(withId(R.id.activity_search_query)).perform(typeText("test"));
        onView(withId(R.id.activity_search_checkbox_1)).perform(click());
        onView(withId(R.id.activity_notifications_switch)).perform(click());
        Espresso.pressBack();
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Notifications")).perform(click());
        onView(withId(R.id.activity_notifications_switch)).perform(click());
        onView(withId(R.id.activity_search_query)).check(matches(withText("test")));

    }
}

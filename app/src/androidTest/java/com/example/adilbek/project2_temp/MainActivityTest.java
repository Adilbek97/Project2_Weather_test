package com.example.adilbek.project2_temp;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }
   @Test public void TestCelcius(){
       Espresso.onView(allOf(withId(R.id.textCity), isDisplayed())).check(matches(isDisplayed()));
       Espresso.onView(allOf(withId(R.id.textCelcius), isDisplayed())).check(matches(isDisplayed()));
       Espresso.onView(allOf(withId(R.id.textRegion), isDisplayed())).check(matches(isDisplayed()));

       Espresso.onView(allOf(withId(R.id.textUpdate), isDisplayed())).check(matches(isDisplayed()));

       Espresso.onView(allOf(withId(R.id.textparagraf), isDisplayed())).check(matches(isDisplayed()));

       Espresso.onView(allOf(withId(R.id.textRegion), isDisplayed())).check(matches(isDisplayed()));

       Espresso.onView(allOf(withId(R.id.textFarangate), isDisplayed())).check(matches(isDisplayed()));
   }
  /* @Test public void  TestLocalTime(){
     }
   @Test public void  TestUpdate(){
      }
    @Test public void  TestRegion(){
       }
    @Test public void  TestCloud(){
      }
   @Test public void  TestFarangate(){
    }
*/
    @After
    public void tearDown() throws Exception {
    }
}
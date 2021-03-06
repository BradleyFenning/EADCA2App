package com.example.eadca2app;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EADInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void languageChangeTest() {
        // Context of the app under test.
        onView(withId(R.id.languageButton)).perform(click());
        onView(withId(R.id.languageButton)).check(matches(withText("Cambiar idioma")));
    }

//    @Test
//    public void bookListTest() {
//        // Context of the app under test.
//        onView(withId(R.id.button2)).perform(click());
//        onData(anything()).inAdapterView(withId(R.id.bookList))
//                .atPosition(0).check(matches(withText("1 AGE PROOF Professor Rose Anne Kenny")));
//    }

    @Test
    public void bookSearchTest(){
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.userInput)).perform(typeText("3"));
        onView(withId(R.id.button1)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.libraryList)).check(
                matches(withText("Tallaght Library\t\t\t\t\t\t 1 Tallaght Road")));
    }

}
package com.dleal.linkfinder.mainView;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.dleal.linkfinder.R;
import com.dleal.linkfinder.component.LinkFinderApplication;
import com.dleal.linkfinder.component.main.MainActivity;
import com.dleal.linkfinder.di.components.ApplicationComponent;
import com.dleal.linkfinder.di.modules.ApplicationModule;
import com.dleal.linkfinder.matchers.EditTextMatcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Daniel Leal on 29/04/16.
 */
@RunWith(AndroidJUnit4.class) @LargeTest public class MainActivityTest {

    LinkFinderApplication app =
            (LinkFinderApplication) InstrumentationRegistry.getInstrumentation()
                    .getTargetContext()
                    .getApplicationContext();

    @Rule public IntentsTestRule<MainActivity> activityRule =
            new IntentsTestRule<>(MainActivity.class, true, false);

    @Rule public final DaggerMockRule<ApplicationComponent> mockitoRule = new DaggerMockRule<>(ApplicationComponent.class, new ApplicationModule(app))
            .set(applicationComponent -> {
                app.setComponent(applicationComponent);
            });

    @Test
    public void loadsMainView() {
        startActivity();

        onView(withId(R.id.edit_website_url)).check(matches(isDisplayed()));
    }

    @Test
    public void showsErrorWhenUrlIsEmpty() {
        startActivity();
        onView(withId(R.id.edit_website_url)).perform(ViewActions.clearText());

        onView(withId(R.id.btn_main_search)).perform(ViewActions.click());

        onView(withId(R.id.input_layout_website)).check(matches(
                EditTextMatcher.hasTextInputLayoutErrorText(app.getStringRes(R.string.error_website_empty))));
    }

    @Test
    public void showsErrorWhenUrlIsNotValid() {
        startActivity();

        onView(withId(R.id.edit_website_url)).perform(ViewActions.clearText());

        onView(withId(R.id.edit_website_url)).perform(ViewActions.typeText("user"));
        onView(withId(R.id.btn_main_search)).perform(ViewActions.click());

        onView(withId(R.id.input_layout_website)).check(matches(
                EditTextMatcher.hasTextInputLayoutErrorText(app.getStringRes(R.string.error_website_wrong_format))));
    }

    private MainActivity startActivity() {
        Intent intent = new Intent();
        return activityRule.launchActivity(intent);
    }
}

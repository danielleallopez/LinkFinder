package com.dleal.linkfinder.matchers;

import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Daniel Leal on 29/03/16.
 */
public class EditTextMatcher {

    public static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    Log.d(EditTextMatcher.class.getSimpleName(), "!(view instanceof TextInputLayout)");
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();
                Log.d(EditTextMatcher.class.getSimpleName(), "((TextInputLayout) view).getError()");
                if (error == null) {
                    Log.d(EditTextMatcher.class.getSimpleName(), "error == null");
                    return false;
                }

                String hint = error.toString();
                Log.d(EditTextMatcher.class.getSimpleName(), "hint " + hint);
                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    public static Matcher<View> hasNotTextInputLayoutErrorText() {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    Log.d(EditTextMatcher.class.getSimpleName(), "!(view instanceof TextInputLayout)");
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();
                Log.d(EditTextMatcher.class.getSimpleName(), "((TextInputLayout) view).getError() " + error);
                return error == null;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    public static Matcher<View> hasEditTextErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    Log.d(EditTextMatcher.class.getSimpleName(), "!(view instanceof TextInputLayout)");
                    return false;
                }

                CharSequence error = ((EditText) view).getError();
                Log.d(EditTextMatcher.class.getSimpleName(), "((TextInputLayout) view).getError()");
                if (error == null) {
                    Log.d(EditTextMatcher.class.getSimpleName(), "error == null");
                    return false;
                }

                String hint = error.toString();
                Log.d(EditTextMatcher.class.getSimpleName(), "hint " + hint);
                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}

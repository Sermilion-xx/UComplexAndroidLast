package org.ucomplex.ucomplex;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.UiController;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 19/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class TestUtils {

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

    public static Matcher<View> editTextHasError(final String expectedErrorText) {

        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView)) {
                    return false;
                }
                CharSequence error = ((TextView) view).getError();
                if (error == null && expectedErrorText == null) {
                    return true;
                }
                if (error == null) {
                    return false;
                }
                String errorString = error.toString();

                return expectedErrorText.equals(errorString);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    public static Matcher<View> viewHasDrawable(int expectedId) {
        return new TypeSafeMatcher<View>() {

            String resourceName;
            @Override
            protected boolean matchesSafely(View target) {
                if (!(target instanceof ImageView)) {
                    return false;
                }
                ImageView imageView = (ImageView) target;
                if (expectedId < 0) {
                    return imageView.getDrawable() == null;
                }
                Resources resources = target.getContext().getResources();
                Drawable expectedDrawable = resources.getDrawable(expectedId);
                resourceName = resources.getResourceEntryName(expectedId);

                if (expectedDrawable == null) {
                    return false;
                }

                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                Bitmap otherBitmap = ((BitmapDrawable) expectedDrawable).getBitmap();
                return bitmap.sameAs(otherBitmap);
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
}


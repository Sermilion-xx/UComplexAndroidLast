package org.ucomplex.ucomplex;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ucomplex.ucomplex.Modules.Login.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.ucomplex.ucomplex.TestUtils.editTextHasError;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 19/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Before
    public void setup() {
        closeSoftKeyboard();
    }

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void emptyLoginShowsError() {
        emptyFieldShowsError(R.id.login);
    }

    @Test
    public void emptyPasswordShowsError() {
        emptyFieldShowsError(R.id.password);
    }

    @Test
    public void filledLoginShowsPasswordError() {
        onlyEmptyFieldShowError(R.id.login, R.id.password);
    }

    @Test
    public void filledPasswordShowsLoginError() {
        onlyEmptyFieldShowError(R.id.password, R.id.login);
    }

    @Test
    public void allFilledFieldNoError() {
        String text = "text";
            onView(withId(R.id.login)).perform(closeSoftKeyboard());
            onView(withId(R.id.login)).perform(typeText(text));
            onView(withId(R.id.password)).perform(closeSoftKeyboard());
            onView(withId(R.id.password)).perform(typeText(text), closeSoftKeyboard());
            onView(withId(R.id.login)).check(matches(withText(text)));
            onView(withId(R.id.password)).check(matches(withText(text)));
            onView(withId(R.id.login_sign_in_button)).perform(click());
            onView(withId(R.id.login)).check(matches(editTextHasError(null)));
            onView(withId(R.id.password)).check(matches(editTextHasError(null)));
    }

    private void emptyFieldShowsError(int fieldId) {
        onView(withId(fieldId)).perform(closeSoftKeyboard());
        onView(withId(R.id.login_sign_in_button)).perform(click());
        onView(withId(fieldId)).check(matches(withText("")));
        onView(withId(fieldId)).check(matches(editTextHasError(mLoginActivityRule.getActivity().getString(R.string
                .error_field_required))));
    }

    private void onlyEmptyFieldShowError(int filledFieldId, int emptyFieldId) {
        String text = "text";
        try {
            onView(withId(filledFieldId)).perform(typeText(text), closeSoftKeyboard());
            Thread.sleep(1000);
            onView(withId(R.id.login_sign_in_button)).perform(click());
            onView(withId(filledFieldId)).check(matches(withText(text)));
            onView(withId(emptyFieldId)).check(matches(editTextHasError(mLoginActivityRule.getActivity().getString(R.string
                    .error_field_required))));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

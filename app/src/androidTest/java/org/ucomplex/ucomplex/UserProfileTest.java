package org.ucomplex.ucomplex;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ucomplex.ucomplex.Modules.RoleInfo.RoleInfoActivity;
import org.ucomplex.ucomplex.Modules.UserProfile.UserProfileActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

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
public class UserProfileTest {

    @Rule
    public ActivityTestRule<UserProfileActivity> mLoginActivityRule = new ActivityTestRule<UserProfileActivity>(UserProfileActivity.class) {
        @Override
        protected void afterActivityLaunched() {
            Intents.init();
            super.afterActivityLaunched();
        }

        @Override
        protected void afterActivityFinished() {
            super.afterActivityFinished();
            Intents.release();
        }
    };

    @Before
    public void setup() {
        TestUtils.waitFor(30000);
    }

    @Test
    public void blockButtonClickChangesIcon() {
        onView(withId(R.id.block)).check(matches(TestUtils.viewHasDrawable(R.drawable.ic_unlock)));
        clickViewAtPosition(R.id.block, 0);
        onView(withId(R.id.block)).check(matches(TestUtils.viewHasDrawable(R.drawable.ic_lock)));
    }

    @Test
    public void friendButtonClickChangesText() {
        clickViewAtPosition(R.id.add_friend_button, 0);
        onView(withId(R.id.add_friend_button)).check(matches(withText(R.string.remove_friend)));
    }

    @Test
    public void profileImageClickedOpensFullImage() {
        clickViewAtPosition(R.id.profile_picture, 0);
        onView(withId(R.id.full_image)).check(matches(isDisplayed()));
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
    }

    @Test
    public void roleClickOpensRoleActivity() {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        intended(hasComponent(RoleInfoActivity.class.getName()));
    }

    private void clickViewAtPosition (int view, int position) {
        onView(withId(R.id.recyclerView)).perform(scrollToPosition(0));
        onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(position, TestUtils.clickChildViewWithId(view)));
    }

}

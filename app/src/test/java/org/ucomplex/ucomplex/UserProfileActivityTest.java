package org.ucomplex.ucomplex;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.ucomplex.ucomplex.Modules.UserProfile.UserProfileActivity;
/**
 * ---------------------------------------------------
 * Created by Sermilion on 09/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class UserProfileActivityTest {

    @Before
    public void setUp() {
        Activity activity = Robolectric.setupActivity(UserProfileActivity.class);
        
    }
}

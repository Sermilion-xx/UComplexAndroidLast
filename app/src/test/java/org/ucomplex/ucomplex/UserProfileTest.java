package org.ucomplex.ucomplex;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.ucomplex.ucomplex.Domain.users.UserInterface;
import org.ucomplex.ucomplex.Modules.UserProfile.UserProfileModel;
import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileItem;
import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileRaw;

import java.util.List;

import io.reactivex.Observable;

import static org.hamcrest.MatcherAssert.assertThat;


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
public class UserProfileTest {

    private final String OBSERVABLE_NULL_ERROR = "Observable is null";

    private UserProfileModel model;

    @Before
    public void setUp() {
        model = new UserProfileModel();
    }

    @Test
    public void loadDataTest() {
        Observable<UserProfileRaw> observable = model.loadData(1951);
        assertThat(OBSERVABLE_NULL_ERROR, observable != null);
    }

    @Test
    public void addAsFriendTest() {
        Observable<Void> observable = model.addAsFriend(1951);
        assertThat(OBSERVABLE_NULL_ERROR, observable != null);
    }

    @Test
    public void blockTest() {
        Observable<Void> observable = model.block(1951);
        assertThat(OBSERVABLE_NULL_ERROR, observable != null);
    }

    @Test
    public void unblockTest() {
        Observable<Void> observable = model.unblock(1951);
        assertThat(OBSERVABLE_NULL_ERROR, observable != null);
    }

    @Test
    public void unfriendTest() {
        Observable<Void> observable = model.unfriend(1951);
        assertThat(OBSERVABLE_NULL_ERROR, observable != null);
    }

    @Test
    public void extractUserText() {
        UserProfileRaw profileRaw = new UserProfileRaw();
        UserInterface userInterface = profileRaw.extractUser();
        assertThat("User is null", userInterface != null);
    }

    @Test
    public void processDataTest() {
        UserProfileRaw profileRaw = new UserProfileRaw();
        List<UserProfileItem> list = model.processData(profileRaw);
        assertThat("List is null", list != null);
    }
}

package org.ucomplex.ucomplex;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Domain.BlackList;
import org.ucomplex.ucomplex.Domain.FriendList;
import org.ucomplex.ucomplex.Modules.UserProfile.UserProfileAdapter;
import org.ucomplex.ucomplex.Modules.UserProfile.model.ProfileRequestType;
import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileItem;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 20/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class UserProfileAdapterTest {

    private UserProfileAdapter adapter;
    private RecyclerView recyclerView;
    private List<UserProfileItem> list;
    private Context context;

    @Mock
    OnListItemClicked<Object, ProfileRequestType> onListItemClicked;

    @Before
    public void setUp() {
        context = RuntimeEnvironment.application.getApplicationContext();
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new UserProfileAdapter(onListItemClicked);
        list = new ArrayList<>();
        list.add(new UserProfileItem("Student", "Role", 1951, 4));
        list.add(new UserProfileItem("Student", "Role", 1951, 4));
        list.add(new UserProfileItem("Name", "Role", new FriendList(), new BlackList(), "code", 1951));
        adapter.setItems(list);
        recyclerView.setAdapter(adapter);
    }

    @Test
    public void adapterHasTreeRows() {
        assertThat(adapter.getItemCount(), is(list.size()));
    }
}

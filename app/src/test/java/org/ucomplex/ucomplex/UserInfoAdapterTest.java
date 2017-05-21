package org.ucomplex.ucomplex;

import android.content.Context;
import android.support.v4.util.Pair;
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
import org.ucomplex.ucomplex.Modules.RoleInfo.RoleInfoAdapter;
import org.ucomplex.ucomplex.Modules.UserProfile.model.ProfileRequestType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
public class UserInfoAdapterTest {

    private RoleInfoAdapter adapter;
    private RecyclerView recyclerView;
    private List<Pair<String, String>> list;
    private Context context;

    @Mock
    OnListItemClicked<Object, ProfileRequestType> onListItemClicked;

    @Before
    public void setUp() {
        context = RuntimeEnvironment.application.getApplicationContext();
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new RoleInfoAdapter();
        list = new ArrayList<>();
        list.add(new Pair<>("1", "1"));
        list.add(new Pair<>("1", "1"));
        list.add(new Pair<>("1", "1"));
        adapter.setItems(list);
        recyclerView.setAdapter(adapter);
    }

    @Test
    public void adapterHasTreeRows() {
        assertThat(adapter.getItemCount(), is(list.size()));
    }
}

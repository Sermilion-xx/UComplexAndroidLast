package org.ucomplex.ucomplex.Modules.Users;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import org.ucomplex.ucomplex.Common.ViewPagerAdapter;
import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Modules.Users.UsersOnline.UsersFragment;
import org.ucomplex.ucomplex.R;

public class UsersActivity extends BaseActivity {

    private static final String TAG_ONLINE_FRAGMENT = "onlineFragment";

    public static Intent creteIntent(Context context) {
        Intent intent = new Intent(context, UsersActivity.class);
        return intent;
    }

    private UsersFragment onlineFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState, TAG_ONLINE_FRAGMENT, onlineFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_users);
        setupToolbar(getString(R.string.users), R.drawable.ic_menu);
        setupDrawer();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

        if (savedInstanceState != null) {
            onlineFragment = (UsersFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_ONLINE_FRAGMENT);
        } else {
            onlineFragment = UsersFragment.getInstance();
        }

        viewPagerAdapter.addFragment(onlineFragment, getString(R.string.online));
        viewPager.setAdapter(viewPagerAdapter);
    }
}

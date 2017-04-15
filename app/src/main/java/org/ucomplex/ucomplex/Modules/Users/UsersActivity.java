package org.ucomplex.ucomplex.Modules.Users;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;

import org.ucomplex.ucomplex.Common.ViewPagerAdapter;
import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Modules.Users.model.UserRequestType;
import org.ucomplex.ucomplex.R;

public class UsersActivity extends BaseActivity {

    private static final String TAG_ONLINE_FRAGMENT = "onlineFragment";
    private static final String TAG_FRIENDS_FRAGMENT = "friendsFragment";
    private static final String TAG_GROUP_FRAGMENT = "groupFragment";
    private static final String TAG_TEACHERS_FRAGMENT = "teachersFragment";
    private static final String TAG_BLACKLIST_FRAGMENT = "blacklistFragment";

    public static Intent creteIntent(Context context) {
        Intent intent = new Intent(context, UsersActivity.class);
        return intent;
    }

    private UsersFragment onlineFragment;
    private UsersFragment friendsFragment;
    private UsersFragment groupFragment;
    private UsersFragment teachersFragment;
    private UsersFragment blacklistFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (onlineFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, TAG_ONLINE_FRAGMENT, onlineFragment);
        }
        if (friendsFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, TAG_FRIENDS_FRAGMENT, friendsFragment);
        }
        if (groupFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, TAG_GROUP_FRAGMENT, groupFragment);
        }
        if (teachersFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, TAG_TEACHERS_FRAGMENT, teachersFragment);
        }
        if (blacklistFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, TAG_BLACKLIST_FRAGMENT, blacklistFragment);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_users);
        setupToolbar(getString(R.string.users));
        setupDrawer();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        PagerSlidingTabStrip tabLayout = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(2);

        if (savedInstanceState != null) {
            onlineFragment = (UsersFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_ONLINE_FRAGMENT);
            friendsFragment = (UsersFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_FRIENDS_FRAGMENT);
            groupFragment = (UsersFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_GROUP_FRAGMENT);
            teachersFragment = (UsersFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_TEACHERS_FRAGMENT);
            blacklistFragment = (UsersFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_BLACKLIST_FRAGMENT);
        } else {
            onlineFragment = new UsersFragment();
            onlineFragment.setUserType(UserRequestType.ONLINE);
            friendsFragment = new UsersFragment();
            friendsFragment.setUserType(UserRequestType.FRIENDS);
            groupFragment = new UsersFragment();
            groupFragment.setUserType(UserRequestType.GROUP);
            teachersFragment = new UsersFragment();
            teachersFragment.setUserType(UserRequestType.TEACHERS);
            blacklistFragment = new UsersFragment();
            blacklistFragment.setUserType(UserRequestType.BLACKLIST);
        }

        viewPagerAdapter.addFragment(onlineFragment, getString(R.string.online));
        viewPagerAdapter.addFragment(friendsFragment, getString(R.string.friends));
        viewPagerAdapter.addFragment(groupFragment, getString(R.string.group));
        viewPagerAdapter.addFragment(teachersFragment, getString(R.string.teachers));
        viewPagerAdapter.addFragment(blacklistFragment, getString(R.string.blacklist));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setViewPager(viewPager);
    }
}

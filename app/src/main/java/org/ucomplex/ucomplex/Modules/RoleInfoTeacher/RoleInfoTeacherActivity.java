package org.ucomplex.ucomplex.Modules.RoleInfoTeacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.ViewPagerAdapter;
import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Modules.RoleInfo.RoleInfoActivity;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherProfile.RoleInfoTeacherProfileFragment;
import org.ucomplex.ucomplex.R;

public class RoleInfoTeacherActivity extends BaseActivity {

    public static final String ROLE_ID = "roleId";
    public static final String NAME = "name";
    private static final String TAG_PROFILE_FRAGMENT = "profileFragment";

    public static Intent creteIntent(Context context, int roleId, String name) {
        Intent intent = new Intent(context, RoleInfoTeacherActivity.class);
        intent.putExtra(ROLE_ID, roleId);
        intent.putExtra(NAME, name);
        return intent;
    }

    private RoleInfoTeacherProfileFragment profileFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState, TAG_PROFILE_FRAGMENT, profileFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_role_info_teacher);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        Intent intent = getIntent();
        setupToolbar(intent.getStringExtra(NAME), R.drawable.ic_menu);
        setupDrawer();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

        if (savedInstanceState != null) {
            profileFragment = (RoleInfoTeacherProfileFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_PROFILE_FRAGMENT);
        } else {
            int id = intent.getIntExtra(ROLE_ID, -1);
            profileFragment = RoleInfoTeacherProfileFragment.getInstance(id);
        }

        viewPagerAdapter.addFragment(profileFragment, getString(R.string.profile));
        viewPager.setAdapter(viewPagerAdapter);
    }
}

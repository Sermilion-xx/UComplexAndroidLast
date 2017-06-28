package org.ucomplex.ucomplex.Modules.Subject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.ViewPagerAdapter;
import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.SubjectMaterialsFragment;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.SubjectProfileFragment;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.SubjectTimelineFragment;
import org.ucomplex.ucomplex.R;

import static org.ucomplex.ucomplex.Common.FacadeCommon.REQUEST_EXTERNAL_STORAGE;

public class SubjectActivity extends BaseActivity {

    public static final String EXTRA_GCOURSE = "EXTRA_GCOURSE";
    public static final String EXTRA_COURSE_NAME = "EXTRA_COURSE_NAME";
    private static final String TAG_MATERIALS_FRAGMENT = "subjectMaterialsFragment";
    private static final String TAG_DETAILS_FRAGMENT = "subjectProfileFragment";
    private static final String TAG_TIMELINE_FRAGMENT = "subjectTimelineFragment";

    public static Intent creteIntent(Context context, int gcourse, String courseName) {
        Intent intent = new Intent(context, SubjectActivity.class);
        intent.putExtra(EXTRA_GCOURSE, gcourse);
        intent.putExtra(EXTRA_COURSE_NAME, courseName);
        return intent;
    }

    private SubjectProfileFragment subjectProfileFragment;
    private SubjectMaterialsFragment subjectMaterialsFragment;
    private SubjectTimelineFragment subjectTimelineFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState, TAG_MATERIALS_FRAGMENT, subjectMaterialsFragment);
        getSupportFragmentManager().putFragment(outState, TAG_DETAILS_FRAGMENT, subjectProfileFragment);
        getSupportFragmentManager().putFragment(outState, TAG_TIMELINE_FRAGMENT, subjectTimelineFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (subjectProfileFragment != null && subjectProfileFragment.getFilesAndTeachers() != null) {
            subjectMaterialsFragment.setMaterialsItems(subjectProfileFragment.getFilesAndTeachers());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_subject);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        Intent intent = getIntent();
        setupToolbar(intent.getStringExtra(EXTRA_COURSE_NAME), R.drawable.ic_menu);
        setupDrawer();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

        if (savedInstanceState != null) {
            subjectProfileFragment = (SubjectProfileFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_DETAILS_FRAGMENT);
            subjectMaterialsFragment = (SubjectMaterialsFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_MATERIALS_FRAGMENT);
            subjectTimelineFragment = (SubjectTimelineFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_TIMELINE_FRAGMENT);
        } else {
            int gcourse = intent.getIntExtra(EXTRA_GCOURSE, -1);
            subjectProfileFragment = SubjectProfileFragment.getInstance(gcourse);
            subjectMaterialsFragment = SubjectMaterialsFragment.getInstance(false);
            subjectProfileFragment.setOnPresenterInjectedListener(data -> subjectMaterialsFragment.setMaterialsItems(data));
            subjectTimelineFragment = SubjectTimelineFragment.getInstance(gcourse);
        }

        viewPagerAdapter.addFragment(subjectProfileFragment, getString(R.string.disciplines));
        viewPagerAdapter.addFragment(subjectMaterialsFragment, getString(R.string.materials));
        viewPagerAdapter.addFragment(subjectTimelineFragment, getString(R.string.timeline));
        viewPager.setAdapter(viewPagerAdapter);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FacadeCommon.checkStoragePermissions(this);
        }
    }

    @Override
    public void onBackPressed() {
        if(subjectMaterialsFragment.getCurrentPage() > 0){
            subjectMaterialsFragment.onBackPress();
        }else{
            super.onBackPressed();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    showToast(R.string.storage_access_denied);
                }
            }
        }
    }

}

package org.ucomplex.ucomplex.Modules.Calendar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.ViewPagerAdapter;
import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;

import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.CalendarBeltFragment;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.CalendarPageFragment;
import org.ucomplex.ucomplex.R;

public class CalendarActivity extends BaseActivity {

    private static final String TAG_CALENDAR_FRAGMENT = "calendarFragment";
    private static final String TAG_BELT_FRAGMENT = "beltFragment";
//    private static final String TAG_TIMELINE_FRAGMENT = "subjectTimelineFragment";

    public static Intent creteIntent(Context context) {
        return new Intent(context, CalendarActivity.class);
    }

    private CalendarPageFragment calendarFragment;
    private CalendarBeltFragment beltFragment;
//    private SubjectTimelineFragment subjectTimelineFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState, TAG_CALENDAR_FRAGMENT, calendarFragment);
        getSupportFragmentManager().putFragment(outState, TAG_BELT_FRAGMENT, beltFragment);
//        getSupportFragmentManager().putFragment(outState, TAG_TIMELINE_FRAGMENT, subjectTimelineFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_calendar);
        setupToolbar(getString(R.string.calendar) ,R.drawable.ic_menu);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupDrawer();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

        if (savedInstanceState != null) {
            calendarFragment = (CalendarPageFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_CALENDAR_FRAGMENT);
            beltFragment = (CalendarBeltFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_BELT_FRAGMENT);
//            subjectTimelineFragment = (SubjectTimelineFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_TIMELINE_FRAGMENT);
        } else {
            calendarFragment = CalendarPageFragment.getInstance();
            beltFragment = CalendarBeltFragment.getInstance();
//            subjectTimelineFragment = SubjectTimelineFragment.getInstance(gcourse);
        }

        viewPagerAdapter.addFragment(calendarFragment, getString(R.string.calendar));
        viewPagerAdapter.addFragment(beltFragment, getString(R.string.belt));
//        viewPagerAdapter.addFragment(subjectTimelineFragment, getString(R.string.timeline));
        viewPager.setAdapter(viewPagerAdapter);
    }



}
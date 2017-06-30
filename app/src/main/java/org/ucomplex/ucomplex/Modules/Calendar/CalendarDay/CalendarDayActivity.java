package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.ViewPagerAdapter;
import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt.CalendarDayBeltFragment;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable.CalendarDayTimetableFragment;
import org.ucomplex.ucomplex.R;

public class CalendarDayActivity extends BaseActivity {

    private static final String TAG_BELT_FRAGMENT = "beltFragment";
    private static final String TAG_TIMETABLE_FRAGMENT = "timetableFragment";

    public static final String EXTRA_DAY = "day";
    public static final String EXTRA_MONTH = "month";
    public static final String EXTRA_YEAR = "year";

    public static Intent creteIntent(Context context, String day, String month, String year) {
        Intent intent = new Intent(context, CalendarDayActivity.class);
        intent.putExtra(EXTRA_DAY, day);
        intent.putExtra(EXTRA_MONTH, month);
        intent.putExtra(EXTRA_YEAR, year);
        return intent;
    }

    private CalendarDayBeltFragment beltFragment;
    private CalendarDayTimetableFragment timetableFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState, TAG_BELT_FRAGMENT, beltFragment);
        getSupportFragmentManager().putFragment(outState, TAG_TIMETABLE_FRAGMENT, timetableFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_day);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        Intent intent = getIntent();
        String day = intent.getStringExtra(EXTRA_DAY);
        String month = intent.getStringExtra(EXTRA_MONTH);
        String year = intent.getStringExtra(EXTRA_YEAR);
        setupToolbar(day + " " + FacadeCommon.months.get(month) + " " + year, R.drawable.ic_arrow_back_white);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

        if (savedInstanceState != null) {
            beltFragment = (CalendarDayBeltFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_BELT_FRAGMENT);
            timetableFragment = (CalendarDayTimetableFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_TIMETABLE_FRAGMENT);
        } else {
            beltFragment = CalendarDayBeltFragment.getInstance(day);
            timetableFragment = CalendarDayTimetableFragment.getInstance();
        }
        viewPagerAdapter.addFragment(beltFragment, getString(R.string.performance));
        viewPagerAdapter.addFragment(timetableFragment, getString(R.string.timeline));
        viewPager.setAdapter(viewPagerAdapter);
    }
}

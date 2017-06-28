package org.ucomplex.ucomplex.Modules.CalendarDay.CalendarDayPerformance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.R;

public class CalendarDayPerformanceActivity extends BaseMVPActivity<MVPView, CalendarDayPerformancePresenter> {

    public static final String EXTRA_DAY = "extra_day";

    public static Intent creteIntent(Context context) {
        Intent intent = new Intent(context, CalendarDayPerformanceActivity.class);
        return intent;
    }

    private CalendarDayPerformanceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentViewWithNavDrawer(R.layout.activity_calendar_day);
        setupToolbar(intent.getStringExtra(EXTRA_DAY),R.drawable.ic_menu);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupDrawer();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CalendarDayPerformanceAdapter();
        mRecyclerView.setAdapter(mAdapter);
        presenter.loadData(null);
    }

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getData());
        mAdapter.notifyDataSetChanged();
    }
}
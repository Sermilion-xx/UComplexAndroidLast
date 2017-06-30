package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.base.BaseMvpFragment;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.CalendarBeltAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt.CalendarDayBeltFragment;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt.CalendarDayBeltPresenter;
import org.ucomplex.ucomplex.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 29/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class CalendarDayTimetableFragment extends BaseMvpFragment<CalendarDayTimetablePresenter> {

    public static final String EXTRA_DAY = "day";

    public static CalendarDayTimetableFragment getInstance(String day) {
        CalendarDayTimetableFragment fragment = new CalendarDayTimetableFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_DAY, day);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.progressBar)
    protected ProgressBar mProgress;
    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @Inject
    protected CalendarDayTimetableAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivityContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.loadData(getArguments().getString(EXTRA_DAY));
    }

    @Override
    public void dataLoaded() {
        if (presenter.getData() != null) {
            mAdapter.setItems(presenter.getData());
            mAdapter.notifyDataSetChanged();
        }
    }
}

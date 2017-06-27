package org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import org.ucomplex.ucomplex.Common.base.BaseMvpFragment;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.DownloadCallback;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.model.CalendarBeltItem;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.CalendarPagePresenter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageParams;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.utility.CalendarDayDecorator;
import org.ucomplex.ucomplex.Modules.Events.LoadMoreCallback;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.SubjectMaterialsAdapter;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.utility.CalendarDayDecorator.TYPE_ATTESTATION;
import static org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.utility.CalendarDayDecorator.TYPE_EVENTS;
import static org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.utility.CalendarDayDecorator.TYPE_EXAM;
import static org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.utility.CalendarDayDecorator.TYPE_INDIVID_LESSON;
import static org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.utility.CalendarDayDecorator.TYPE_LESSON;
import static org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.utility.CalendarDayDecorator.TYPE_TIMETABLE;
import static org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.utility.CalendarDayDecorator.TYPE_TODAY;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class CalendarBeltFragment extends BaseMvpFragment<CalendarBeltPresenter> {

    public static CalendarBeltFragment getInstance() {
        return new CalendarBeltFragment();
    }

    @BindView(R.id.progressBar)
    protected ProgressBar mProgress;
    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @Inject
    protected CalendarBeltAdapter mAdapter;

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
        presenter.setDownloadCallback(new DownloadCallback<List<CalendarBeltItem>>() {
            @Override
            public void onResponse(List<CalendarBeltItem> response) {
                mAdapter.updateAdapterItems(response, UCApplication.getInstance().isConnectedToInternet());
            }

            @Override
            public void onError(Throwable t) {

            }
        });
        mAdapter.setmMoreCallback(start -> {
            presenter.loadData(start);
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter.getData() == null) {
            presenter.loadData(0);
        } else {
            dataLoaded();
        }
    }

    @Override
    public void dataLoaded() {

    }
}

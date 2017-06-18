package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.ucomplex.ucomplex.Common.base.BaseMvpFragment;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class CalendarPageFragment extends BaseMvpFragment<CalendarPagePresenter> {

    public static CalendarPageFragment getInstance() {
        return new CalendarPageFragment();
    }

    public CalendarPageFragment() {
        spinnerOptions = new ArrayList<>();
        spinnerOptions.add("Показать все");
        spinnerOptions.add("Все дисциплины");
        spinnerOptions.add("События");
    }

    @BindView(R.id.calendar)
    protected MaterialCalendarView materialCalendarView;
    @BindView(R.id.spinner)
    protected Spinner spinner;
    private final String[] monthsTitles = {"Январь", "Февряль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    private List<String> spinnerOptions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter.getData() == null) {
            presenter.loadData(null);
        } else {
            dataLoaded();
        }
    }

    @Override
    public void dataLoaded() {

    }
}

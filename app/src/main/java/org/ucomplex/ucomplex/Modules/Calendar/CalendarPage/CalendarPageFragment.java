package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import org.ucomplex.ucomplex.Common.base.BaseMvpFragment;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageParams;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.utility.CalendarDayDecorator;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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

public class CalendarPageFragment extends BaseMvpFragment<CalendarPagePresenter> {

    public static CalendarPageFragment getInstance() {
        return new CalendarPageFragment();
    }

    public CalendarPageFragment() {
        spinnerOptions = new ArrayList<>();

    }

    @BindView(R.id.calendar)
    protected MaterialCalendarView materialCalendarView;
    @BindView(R.id.spinner)
    protected Spinner spinner;
    private final String[] monthsTitles = {"Январь", "Февряль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    private List<String> spinnerOptions;
    private ArrayAdapter<String> spinnerAdapter;

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

        spinner.setOnItemSelectedListener(subjectSelectedListener);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
        spinnerAdapter = new ArrayAdapter<>(getActivityContext(),
                R.layout.spinner_item, spinnerOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        initializeSpinnerOptions(null);

        MonthArrayTitleFormatter monthArrayTitleFormatter = new MonthArrayTitleFormatter(monthsTitles);
        materialCalendarView.setTitleFormatter(monthArrayTitleFormatter);
        materialCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, final CalendarDay date) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        spinner.setSelection(0);
                        int month = date.getMonth() + 1;
                        int year = date.getYear();
                        String monthStr = String.valueOf(month > 9 ? month : "0" + month);
                        String dateStr = 1 + "." + monthStr + "." + year;
                        int Year = Calendar.getInstance().get(Calendar.YEAR);
                        if (year <= Year) {
                            presenter.loadData(new CalendarPageParams(monthStr, dateStr));
                        } else {
                            Toast.makeText(getContext(), R.string.no_data_for_next_year, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1000);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter.getData() == null) {
            presenter.loadData(new CalendarPageParams());
        } else {
            dataLoaded();
        }
    }

    @Override
    public void dataLoaded() {
        setupCalendar(0);
    }

    AdapterView.OnItemSelectedListener subjectSelectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> spinner, View container,
                                   int position, long id) {
            if (presenter.getData() != null)
                setupCalendar(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    };

    private void setupCalendar(int position) {
        CalendarPageRaw calendar = presenter.getData();
        Map<Integer, String> courses = calendar.getCourses();
        List<Integer> keys = new ArrayList<>();
        for (Integer key : calendar.getCourses().keySet()) {
            keys.add(key);
        }
        initializeSpinnerOptions(calendar.getCourses());


        if (position == 0) {
            refreshMonth(calendar);
        } else if (position == 1) {
            materialCalendarView.removeDecorators();
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_TIMETABLE, getActivityContext()));
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_LESSON, getActivityContext()));
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_INDIVID_LESSON, getActivityContext()));
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_ATTESTATION, getActivityContext()));
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_EXAM, getActivityContext()));
        } else if (position == 2) {
            //событие
            materialCalendarView.removeDecorators();
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_EVENTS, getActivityContext()));
        } else {
            materialCalendarView.removeDecorators();
            String courseValue = spinnerOptions.get(position);
            List<Pair<String, CalendarPageRaw.ChangedDayLesson>> filteredDays = presenter.filteredChangedDays(courseValue, calendar, courses);
            materialCalendarView.addDecorator(new CalendarDayDecorator(filteredDays, calendar.getYear(), calendar.getMonth(), TYPE_TIMETABLE, getActivityContext()));
            materialCalendarView.addDecorator(new CalendarDayDecorator(filteredDays, calendar.getYear(), calendar.getMonth(), TYPE_LESSON, getActivityContext()));
            materialCalendarView.addDecorator(new CalendarDayDecorator(filteredDays, calendar.getYear(), calendar.getMonth(), TYPE_INDIVID_LESSON, getActivityContext()));
            materialCalendarView.addDecorator(new CalendarDayDecorator(filteredDays, calendar.getYear(), calendar.getMonth(), TYPE_ATTESTATION, getActivityContext()));
            materialCalendarView.addDecorator(new CalendarDayDecorator(filteredDays, calendar.getYear(), calendar.getMonth(), TYPE_EXAM, getActivityContext()));
        }
    }

    private void initializeSpinnerOptions(Map<Integer, String> courses) {
        spinnerOptions.clear();
        spinnerOptions.add(getString(R.string.show_all));
        spinnerOptions.add(getString(R.string.all_disciplines));
        spinnerOptions.add(getString(R.string.events));
        if (courses != null) {
            List<Integer> keys = new ArrayList<>();
            for (Integer key : courses.keySet()) {
                keys.add(key);
            }
            for (int i = 0; i < courses.size(); i++) {
                spinnerOptions.add(courses.get(keys.get(i)));
            }
        }
        spinnerAdapter.notifyDataSetChanged();
    }

    private void refreshMonth(CalendarPageRaw calendar) {
        //выставленны по приоритету, так как каджый декоратор накладываеться на предыдущий
        materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_TODAY, getActivityContext()));
        materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_TIMETABLE, getActivityContext()));
        materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_INDIVID_LESSON, getActivityContext()));
        materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_LESSON, getActivityContext()));
        materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_ATTESTATION, getActivityContext()));
        materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_EXAM, getActivityContext()));
        materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, TYPE_EVENTS, getActivityContext()));
    }
}

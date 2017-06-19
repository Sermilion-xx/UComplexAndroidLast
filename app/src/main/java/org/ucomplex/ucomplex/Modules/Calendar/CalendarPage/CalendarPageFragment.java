package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseMvpFragment;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.utility.CalendarDayDecorator;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    AdapterView.OnItemSelectedListener subjectSelectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> spinner, View container,
                                   int position, long id) {
            CalendarPageRaw calendar = presenter.getData();
            Map<Integer, String> courses = calendar.getCourses();
            String courseValue;
            int courseKey;
            if (position == 0) {
                refreshMonth(calendar);
            } else if (position == 1) {
                materialCalendarView.removeDecorators();
                //Расписание
                materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 5, getActivityContext()));
                //занятие
                materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 0, getActivityContext()));
                //индивидуадбное занятие
                materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 3, getActivityContext()));
                //аттестация
                materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 1, getActivityContext()));
                //экзамен
                materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 2, getActivityContext()));

            } else if (position == 2) {
                //событие
                materialCalendarView.removeDecorators();
                materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 4, getActivityContext()));
            } else {
                materialCalendarView.removeDecorators();
                courseValue = spinnerOptions.get(position);
                courseKey = FacadeCommon.getKeyFromValue(courses, courseValue);

                List<CalendarPageRaw.ChangedDay> filteredDays = new ArrayList<>();
                for (CalendarPageRaw.ChangedDay day : calendar.getChangedDays()) {
                    for (Lesson lesson : day.getLessons()) {
                        if (lesson.getCourse() == Integer.parseInt(courseKey)) {
                            filteredDays.add(day);
                        }
                    }
                }
                materialCalendarView.addDecorator(new CalendarDayDecorator(filteredDays, calendar.getYear(), calendar.getMonth(), 5, getActivityContext()));
                materialCalendarView.addDecorator(new CalendarDayDecorator(filteredDays, calendar.getYear(), calendar.getMonth(), 0, getActivityContext()));
                materialCalendarView.addDecorator(new CalendarDayDecorator(filteredDays, calendar.getYear(), calendar.getMonth(), 3, getActivityContext()));
                materialCalendarView.addDecorator(new CalendarDayDecorator(filteredDays, calendar.getYear(), calendar.getMonth(), 1, getActivityContext()));
                materialCalendarView.addDecorator(new CalendarDayDecorator(filteredDays, calendar.getYear(), calendar.getMonth(), 2, getActivityContext()));
            }

        }

        private void refreshMonth(CalendarPageRaw calendar) {
            //выставленны по приоритету, так как каджый декоратор накладываеться на предыдущий
            //сегодня
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 6, getActivityContext()));
            //Расписание
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 5, getActivityContext()));
            //индивидуадбное занятие
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 3, getActivityContext()));
            //занятие
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 0, getActivityContext()));
            //аттестация
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 1, getActivityContext()));
            //экзамен
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 2, getActivityContext()));
            //события
            materialCalendarView.addDecorator(new CalendarDayDecorator(calendar, 4, getActivityContext()));
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    };
}

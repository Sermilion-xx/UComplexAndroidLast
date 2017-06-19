package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.utility;

import android.content.Context;
import android.graphics.Color;

import com.amulyakhare.textdrawable.TextDrawable;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by Sermilion on 23/12/2015.
 */
public class CalendarDayDecorator implements DayViewDecorator {

    private String color;
    private int year;
    private int month;
    private int type;
    private Set<CalendarDay> dates = new HashSet<>();
    String[] colors = {"#51cde7","#fecd71","#9ece2b","#d18ec0","#fe7877","#8ea3d1","#c3ccd3"};
    private Context context;

    //"Занятие",
    //"Аттестация",
    //"Экзамен",
    //"Индивидуальное занятие"

    public CalendarDayDecorator(CalendarPageRaw calendar, int type, Context context) {
        this.type = type;
        year = calendar.getYear();
        month = calendar.getMonth();
        this.color = colors[type];
        if(type<4){
            dates = changedDaysToCalendarDays(calendar.getChangedDays(), type);
        }else if(type==4){
            dates = eventsToCalendarDays(calendar.getEvents());
        }else if(type==5){
            dates = timetableDaysToCalendarDays(calendar.getTimetable().getEntries());
        }else if(type==6){
            dates = new HashSet<>();
            Calendar cal = Calendar.getInstance();
            dates.add(CalendarDay.from(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)));
        }
        this.context = context;
    }

    public CalendarDayDecorator(Map<String, Map<Integer, CalendarPageRaw.ChangedDay>> days, int year, int month, int type, Context context ) {
        this.year = year;
        this.month = month;
        this.color = colors[type];
        dates = changedDaysToCalendarDays(days, type);
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        if(this.type == 6) {
            TextDrawable drawable = TextDrawable.builder().beginConfig()
                    .width(20)
                    .height(20)
                    .endConfig()
                    .buildRound(null, Color.parseColor("#09c8fa"));
            view.setBackgroundDrawable(drawable);
        }else {
            int dotSize = 5;
            int density = (int) context.getResources().getDisplayMetrics().density;
            if(density>2){
                dotSize = 8;
            }else if(density<2){
                TextDrawable drawable = TextDrawable.builder().beginConfig()
                        .width(20)
                        .height(20)
                        .endConfig()
                        .buildRound(null, Color.parseColor(color));
                view.setBackgroundDrawable(drawable);
            }
            view.addSpan(new DayDecoratorSpan(dotSize, Color.parseColor(color)));
        }
    }

    private Set<CalendarDay> changedDaysToCalendarDays(Map<String, Map<Integer, CalendarPageRaw.ChangedDay>> changedDays, int type){
        List<String> changeDaysKeys = FacadeCommon.getKeys(changedDays);

        for (int i = 0; i < changeDaysKeys.size(); i++) {
            int dayInt = Integer.parseInt(changeDaysKeys.get(i));
            CalendarDay calendarDay = CalendarDay.from(year, month - 1, dayInt);

            Map<Integer, CalendarPageRaw.ChangedDay> changedDayLessons = changedDays.get(changeDaysKeys.get(i));
            List<Integer> changeDayKeys = FacadeCommon.getKeys(changedDayLessons);
            for (int j = 0; j < changedDayLessons.size(); j++) {
                CalendarPageRaw.ChangedDay day = changedDayLessons.get(changeDayKeys.get(j));
                if(day.getType() == type){
                    dates.add(calendarDay);
                }
            }
        }
        return dates;
    }

    private Set<CalendarDay> eventsToCalendarDays(Map<Integer, List<CalendarPageRaw.Event>> eventsMap){
        List<Integer> changeDaysKeys = FacadeCommon.getKeys(eventsMap);
        Set<CalendarDay> dates = new HashSet<>();

        for (int i = 0; i < changeDaysKeys.size(); i++) {
            List<CalendarPageRaw.Event> events = eventsMap.get(changeDaysKeys.get(i));
            for (int j = 0; j < events.size(); j++) {
                CalendarPageRaw.Event event = events.get(j);
                String date = event.getStart();
                String[] dateList = date.split("-");
                int year = Integer.parseInt(dateList[0]);
                int month = dateList[1].charAt(0) == '0' ? Character.getNumericValue(dateList[1].charAt(1)) : Integer.valueOf(dateList[1]);
                int day = dateList[2].charAt(0)== '0' ? Character.getNumericValue(dateList[2].charAt(1)) : Integer.valueOf(dateList[2]);
                CalendarDay calendarDay = CalendarDay.from(year, month-1,day);
                dates.add(calendarDay);
            }
        }
        return dates;
    }

    private Set<CalendarDay> timetableDaysToCalendarDays(Map<Integer, Map<String, String>> entries){
        Set<CalendarDay> dates = new HashSet<>();
        List<Integer> changeDaysKeys = FacadeCommon.getKeys(entries);
        for(Integer lessonDay : changeDaysKeys){
            CalendarDay calendarDay = CalendarDay.from(year, month-1, lessonDay);
            dates.add(calendarDay);
        }
        return dates;
    }

}
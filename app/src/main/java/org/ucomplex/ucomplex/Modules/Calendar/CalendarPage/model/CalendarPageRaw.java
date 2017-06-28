package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class CalendarPageRaw {

    private final String method;
    private final Map<Integer, List<Event>> events;
    private final int day;
    private final String pre_month;
    private final String next_months;
    private final int subjId;
    private final int year;
    private final int month;
    private final String group;
    private final String subgroup;
    private final String course;
    private final Map<Integer, String> courses;
    private final Map<String, Map<Integer, ChangedDayLesson>> changedDays;
    private final Map<Integer, Map<Integer, String>> days;
    private final String calendar;
    @SerializedName("timetable")
    private final Timetable timetable;

    public CalendarPageRaw() {
        this.method = null;
        this.events = null;
        this.day = -1;
        this.pre_month = null;
        this.next_months = null;
        this.subjId = -1;
        this.year = -1;
        this.month = -1;
        this.group = null;
        this.subgroup = null;
        this.course = null;
        this.courses = null;
        this.changedDays = null;
        this.days = null;
        this.calendar = null;
        this.timetable = null;
    }

    public String getMethod() {
        return method;
    }

    public Map<Integer, List<Event>> getEvents() {
        if (events == null) {
            return new HashMap<>();
        }
        return events;
    }

    public int getDay() {
        return day;
    }

    public String getPre_month() {
        return pre_month;
    }

    public String getNext_months() {
        return next_months;
    }

    public int getSubjId() {
        return subjId;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getGroup() {
        return group;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public String getCourse() {
        return course;
    }

    public Map<Integer, String> getCourses() {
        return courses;
    }

    public Map<String, Map<Integer, ChangedDayLesson>> getChangedDays() {
        return changedDays;
    }

    public Map<Integer, Map<Integer, String>> getDays() {
        return days;
    }

    public String getCalendar() {
        return calendar;
    }

    public Timetable getTimetable() {
        if (timetable == null) {
            return new Timetable();
        }
        return timetable;
    }

    public final class Event {

        private final String name;
        private final String descr;
        private final String start;
        private final String till;
        private final int holiday;

        public Event() {
            this.name = null;
            this.descr = null;
            this.start = null;
            this.till = null;
            this.holiday = -1;
        }

        public String getName() {
            return name;
        }

        public String getDescr() {
            return descr;
        }

        public String getStart() {
            return start;
        }

        public String getTill() {
            return till;
        }

        public int getHoliday() {
            return holiday;
        }
    }

    public class ChangedDayLesson {

        private final int type;
        private final int mark;
        private final int course;

        public ChangedDayLesson() {
            this.type = -1;
            this.mark = -1;
            this.course = -1;
        }

        public int getType() {
            return type;
        }

        public int getMark() {
            return mark;
        }

        public int getCourse() {
            return course;
        }
    }
}

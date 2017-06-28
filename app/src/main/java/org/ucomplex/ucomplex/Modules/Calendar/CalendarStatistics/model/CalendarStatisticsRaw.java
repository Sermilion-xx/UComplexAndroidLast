package org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.model;

import java.util.List;
import java.util.Map;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 27/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class CalendarStatisticsRaw {

    private final Map<Integer, String> courses;
    private final List<Statistics> statistic;
    private final int group_table;

    public CalendarStatisticsRaw() {
        this.courses = null;
        this.statistic = null;
        this.group_table = -1;
    }

    public Map<Integer, String> getCourses() {
        return courses;
    }

    public List<Statistics> getStatistics() {
        return statistic;
    }

    public int getGroup_table() {
        return group_table;
    }

    public static class Statistics {
        private final int student;
        private final int course;
        private final int teacher;
        private final int table;
        private final long time;
        private final int mark;
        private final int type;
        private final int _mark;
        private final int marksCount;
        private final int absence;
        private final int individ;
        private final int hours;

        public Statistics() {
            this.student = -1;
            this.course = -1;
            this.teacher = -1;
            this.table = -1;
            this.time = -1;
            this.mark = -1;
            this.type = -1;
            this._mark = -1;
            this.marksCount = -1;
            this.absence = -1;
            this.individ = -1;
            this.hours = -1;
        }

        public int getStudent() {
            return student;
        }

        public int getCourse() {
            return course;
        }

        public int getTeacher() {
            return teacher;
        }

        public int getTable() {
            return table;
        }

        public long getTime() {
            return time;
        }

        public int getMark() {
            return mark;
        }

        public int getType() {
            return type;
        }

        public int get_mark() {
            return _mark;
        }

        public int getMarksCount() {
            return marksCount;
        }

        public int getAbsence() {
            return absence;
        }

        public int getIndivid() {
            return individ;
        }

        public int getHours() {
            return hours;
        }
    }
}

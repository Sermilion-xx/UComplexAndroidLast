package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model;

import java.util.ArrayList;
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

public final class Timetable {

    private final Map<Integer, String> teachers;
//    private final Map<Integer, String> groups;
//    private final Map<Integer, String> hours;
//    private final Map<Integer, String> rooms;
//    private final Map<Integer, String> subjects;
//    private final Map<String, List<Entry>> entries;

    public Timetable() {
        this.teachers = new HashMap<>();
//        this.groups = new HashMap<>();
//        this.hours = new HashMap<>();
//        this.rooms = new HashMap<>();
//        this.subjects = new HashMap<>();
//        this.entries = new HashMap<>();
    }

    public Map<Integer, String> getTeachers() {
        return teachers;
    }

//    public Map<Integer, String> getGroups() {
//        return groups;
//    }
//
//    public Map<Integer, String> getHours() {
//        return hours;
//    }
//
//    public Map<Integer, String> getRooms() {
//        return rooms;
//    }
//
//    public Map<Integer, String> getSubjects() {
//        return subjects;
//    }

//    public Map<String, List<Entry>> getEntries() {
//        return entries;
//    }

    private class Entry {
        private final int week;
        private final int day;
        private final String date;
        private final int hour;
        private final int type;
        private final int course;
        private final int teacher;
        private final int room;
        private final int p;
        private final int table;

        public Entry() {
            this.week = -1;
            this.day = -1;
            this.date = null;
            this.hour = -1;
            this.type = -1;
            this.course = -1;
            this.teacher = -1;
            this.room = -1;
            this.p = -1;
            this.table = -1;
        }

        public int getWeek() {
            return week;
        }

        public int getDay() {
            return day;
        }

        public String getDate() {
            return date;
        }

        public int getHour() {
            return hour;
        }

        public int getType() {
            return type;
        }

        public int getCourse() {
            return course;
        }

        public int getTeacher() {
            return teacher;
        }

        public int getRoom() {
            return room;
        }

        public int getP() {
            return p;
        }

        public int getTable() {
            return table;
        }
    }
}

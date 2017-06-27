package org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.model;

import java.util.List;
import java.util.Map;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 23/-16/2-117.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class CalendarBeltRaw {

    private final Map<Integer, String> teachers;
    private final Map<Integer, String> courses;
    private final List<Mark> marks;
    private final int gcourse;
    private final int user;

    public CalendarBeltRaw() {
        this.teachers = null;
        this.courses = null;
        this.marks = null;
        this.gcourse = -1;
        this.user = -1;
    }

    public Map<Integer, String> getTeachers() {
        return teachers;
    }

    public Map<Integer, String> getCourses() {
        return courses;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public int getGcourse() {
        return gcourse;
    }

    public int getUser() {
        return user;
    }

    public static class Mark {

        private final int time;
        private final int number;
        private final int mark;
        private final int type;
        private final int course;
        private final int teacher;
        private final int gcourse;

        public Mark() {
            this.time = -1;
            this.number = -1;
            this.mark = -1;
            this.type = -1;
            this.course = -1;
            this.teacher = -1;
            this.gcourse = -1;
        }

        public int getTime() {
            return time;
        }

        public int getNumber() {
            return number;
        }

        public int getMark() {
            return mark;
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

        public int getGcourse() {
            return gcourse;
        }
    }
}

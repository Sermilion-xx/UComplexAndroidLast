package org.ucomplex.ucomplex.Modules.CalendarDay.CalendarDayPerformance.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class CalendarDayPerformanceItem {

    private final String disciplineName;
    private final int mark;
    private final String teacherNme;
    private final String room;
    private final String time;

    public CalendarDayPerformanceItem() {
        this.disciplineName = null;
        this.mark = -1;
        this.teacherNme = null;
        this.room = null;
        this.time = null;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public int getMark() {
        return mark;
    }

    public String getTeacherNme() {
        return teacherNme;
    }

    public String getRoom() {
        return room;
    }

    public String getTime() {
        return time;
    }
}

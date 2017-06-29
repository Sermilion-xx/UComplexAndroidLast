package org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 23/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public  final class CalendarBeltItem {

    private final String disciplineName;
    private final String teacherName;
    private final String time;
    private final int mark;
    private final int type;

    public CalendarBeltItem(String disciplineName, String teacherName, String time, int mark, int type) {
        this.disciplineName = disciplineName;
        this.teacherName = teacherName;
        this.time = time;
        this.mark = mark;
        this.type = type;
    }

    public CalendarBeltItem(String disciplineName, int mark, int type) {
        this.disciplineName = disciplineName;
        this.teacherName = null;
        this.time = null;
        this.mark = mark;
        this.type = type;
    }

    public CalendarBeltItem() {
        this.disciplineName = null;
        this.teacherName = null;
        this.time = null;
        this.mark = -1;
        this.type = -1;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTime() {
        return time;
    }

    public int getMark() {
        return mark;
    }

    public int getType() {
        return type;
    }
}

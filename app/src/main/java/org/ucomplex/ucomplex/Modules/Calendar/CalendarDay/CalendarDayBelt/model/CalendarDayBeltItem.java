package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class CalendarDayBeltItem {

    private final String disciplineName;
    private final int mark;
    private final int type;

    public CalendarDayBeltItem() {
        this.disciplineName = null;
        this.mark = -1;
        this.type = -1;
    }

    public CalendarDayBeltItem(String disciplineName, int mark, int type) {
        this.disciplineName = disciplineName;
        this.mark = mark;
        this.type = type;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public int getMark() {
        return mark;
    }

    public int getTeacherNme() {
        return type;
    }

}

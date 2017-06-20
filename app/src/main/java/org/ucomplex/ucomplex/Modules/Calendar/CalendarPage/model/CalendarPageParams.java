package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 19/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class CalendarPageParams {

    private final String month;
    private final String time;

    public CalendarPageParams(String month, String time) {
        this.month = month;
        this.time = time;
    }

    public CalendarPageParams() {
        this.month = null;
        this.time = null;
    }

    public String getMonth() {
        return month;
    }

    public String getTime() {
        return time;
    }
}

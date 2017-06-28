package org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 27/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class CalendarStatisticsItem {

    private final String name;
    private final double mark;
    private final double attendance;
    private final boolean isCurrent;
    private final int headerNumber;

    public CalendarStatisticsItem(String name, double mark, double attendance, boolean isCurrent) {
        this.name = name;
        this.mark = mark;
        this.attendance = attendance;
        this.isCurrent = isCurrent;
        this.headerNumber = -1;
    }

    public CalendarStatisticsItem(int headerNumber) {
        this.headerNumber = headerNumber;
        this.name = null;
        this.mark = -1.0;
        this.attendance = -1.0;
        this.isCurrent = false;
    }

    public String getName() {
        return name;
    }

    public double getMark() {
        return mark;
    }

    public double getAttendance() {
        return attendance;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public int getHeaderNumber() {
        return headerNumber;
    }
}

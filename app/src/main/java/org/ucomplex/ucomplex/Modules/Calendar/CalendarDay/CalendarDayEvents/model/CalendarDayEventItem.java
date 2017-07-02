package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayEvents.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/07/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public  final class CalendarDayEventItem {
    private final String name;
    private final String descr;
    private final String startDate;
    private final String till;
    private final int holiday;

    public CalendarDayEventItem(String name,
                                String descr,
                                String startDate,
                                String till,
                                int holiday) {
        this.name = name;
        this.descr = descr;
        this.startDate = startDate;
        this.till = till;
        this.holiday = holiday;
    }

    public String getName() {
        return name;
    }

    public String getDescr() {
        return descr;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getTill() {
        return till;
    }

    public int getHoliday() {
        return holiday;
    }
}

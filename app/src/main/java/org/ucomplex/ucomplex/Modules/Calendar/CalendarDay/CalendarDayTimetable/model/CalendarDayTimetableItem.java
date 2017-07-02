package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class CalendarDayTimetableItem {

    private final String disciplineName;
    private final String teacherName;
    private final String room;
    private final String time;
    private final int lessonType;

    public CalendarDayTimetableItem(String disciplineName, String teacherName, String room, String time, int lessonType) {
        this.disciplineName = disciplineName;
        this.teacherName = teacherName;
        this.room = room;
        this.time = time;
        this.lessonType = lessonType;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getRoom() {
        return room;
    }

    public String getTime() {
        return time;
    }

    public int getLessonType() {
        return lessonType;
    }
}

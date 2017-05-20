package org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class Progress {

    private final int student;
    private final int course;
    private final int teacher;
    private final int table;
    private final int time;
    private final int mark;
    private final int type;
    private final int _mark;
    private final int marksCount;
    private final int absence;
    private final int individ;
    private final int hours;

    public Progress() {
        this.student = 0;
        this.course = 0;
        this.teacher = 0;
        this.table = 0;
        this.time = 0;
        this.mark = 0;
        this.type = 0;
        this._mark = 0;
        this.marksCount = 0;
        this.absence = 0;
        this.individ = 0;
        this.hours = 0;
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

    public int getTime() {
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

package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 21/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleInfoTeacherRaw {

    private final int id;
    private final String name;
    private final int type;
    private final int closed;
    private final String alias;
    private final int agent;
    private final long online;
    private final int department;
    private final String upqualification;
    private final int rank;
    private final String courses;
    private final int degree;
    private final String bio;
    private final int plan;
    private final int fact;
    private final List<TimetableCourse> timetable_courses;
    private final double activity;
    private final String department_name;
    private final String faculty_name;

    private RoleInfoTeacherRaw() {
        this.id = 0;
        this.name = "";
        this.type = 0;
        this.closed = 0;
        this.alias = "";
        this.agent = 0;
        this.online = 0;
        this.department = 0;
        this.upqualification = "";
        this.rank = 0;
        this.courses = "";
        this.degree = 0;
        this.bio = "";
        this.plan = 0;
        this.fact = 0;
        this.timetable_courses = new ArrayList<>();
        this.activity = 0;
        this.department_name = "";
        this.faculty_name = "";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getClosed() {
        return closed;
    }

    public String getAlias() {
        return alias;
    }

    public int getAgent() {
        return agent;
    }

    public long getOnline() {
        return online;
    }

    public int getDepartment() {
        return department;
    }

    public String getUpqualification() {
        return upqualification;
    }

    public int getRank() {
        return rank;
    }

    public String getCourses() {
        return courses;
    }

    public int getDegree() {
        return degree;
    }

    public String getBio() {
        return bio;
    }

    public int getPlan() {
        return plan;
    }

    public int getFact() {
        return fact;
    }

    public List<TimetableCourse> getTimetable_courses() {
        return timetable_courses;
    }

    public double getActivity() {
        return activity;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public String getFaculty_name() {
        return faculty_name;
    }
}

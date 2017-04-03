package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model;

import android.support.v4.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectTimelineRaw {
    private Map<Integer, String> teachers;
    private Map<Integer, String> courses;
    private List<Marks> marks;
    private String gcourse;
    private String user;

    public Map<Integer, String> getTeachers() {
        return teachers;
    }

    public void setTeachers(Map<Integer, String> teachers) {
        this.teachers = teachers;
    }

    public Map<Integer, String> getCourses() {
        return courses;
    }

    public void setCourses(Map<Integer, String> courses) {
        this.courses = courses;
    }

    public List<Marks> getMarks() {
        return marks;
    }

    public void setMarks(List<Marks> marks) {
        this.marks = marks;
    }

    public String getGcourse() {
        return gcourse;
    }

    public void setGcourse(String gcourse) {
        this.gcourse = gcourse;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

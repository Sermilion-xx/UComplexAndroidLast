package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectTimelineItem {

    private int    mark;
    private String courseName;
    private String teacherName;
    private String time;
    private int    type;

    public boolean isEmpty() {
        return mark == 0 &&
                courseName == null &&
                teacherName == null &&
                time == null &&
                type == 0;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

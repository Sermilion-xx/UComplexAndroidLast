package org.ucomplex.ucomplex.Modules.SubjectsList.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectsListItem {

    private int courseId;
    private String courseName;
    private int assessmentType;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(int assessmentType) {
        this.assessmentType = assessmentType;
    }
}

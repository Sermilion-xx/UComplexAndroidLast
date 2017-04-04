package org.ucomplex.ucomplex.Modules.SubjectsList.model;

import java.util.List;
import java.util.Map;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectsListRaw {

    private Map<Integer,String> courses;
    private Map<Integer,Integer> courses_forms;
    private List<StudentSubjectList> studentSubjectsList;

    public Map<Integer, String> getCourses() {
        return courses;
    }

    public void setCourses(Map<Integer, String> courses) {
        this.courses = courses;
    }

    public Map<Integer, Integer> getCourses_forms() {
        return courses_forms;
    }

    public void setCourses_forms(Map<Integer, Integer> courses_forms) {
        this.courses_forms = courses_forms;
    }

    public List<StudentSubjectList> getStudentSubjectsList() {
        return studentSubjectsList;
    }

    public void setStudentSubjectsList(List<StudentSubjectList> studentSubjectsList) {
        this.studentSubjectsList = studentSubjectsList;
    }
}

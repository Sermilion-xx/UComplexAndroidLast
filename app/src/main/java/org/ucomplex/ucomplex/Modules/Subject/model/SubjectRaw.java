package org.ucomplex.ucomplex.Modules.Subject.model;

import org.ucomplex.ucomplex.Domain.Subject.Course;
import org.ucomplex.ucomplex.Domain.Subject.Depart;
import org.ucomplex.ucomplex.Domain.Subject.Files;
import org.ucomplex.ucomplex.Domain.Subject.Progress;
import org.ucomplex.ucomplex.Domain.Users.Teacher;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectRaw {

    private Course      course;
    private Teacher     teacher;
    private Depart      depart;
    private Progress    progress;
    private List<Files> files;


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public List<Files> getFiles() {
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }
}

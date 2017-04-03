package org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model;

import org.ucomplex.ucomplex.Domain.Users.File;
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

public class Files {

    private Teacher teacher;
    private List<File> files;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}

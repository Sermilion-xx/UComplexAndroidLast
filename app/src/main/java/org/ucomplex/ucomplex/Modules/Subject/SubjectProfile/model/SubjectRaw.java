package org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import org.ucomplex.ucomplex.Common.utility.FalseAsNullTypeAdapterFactory;
import org.ucomplex.ucomplex.Domain.role.Role;
import org.ucomplex.ucomplex.Domain.role.RoleExtractorFactory.RoleExtractorFactory;
import org.ucomplex.ucomplex.Domain.role.RoleExtractorFactory.TeacherExtractorFactory;

import java.util.ArrayList;
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

public final class SubjectRaw {

    private final Course course;
    private TeacherRaw teacher;
    private Role roleTeacher;
    private final Depart depart;
    @SerializedName("progress")
    @JsonAdapter(FalseAsNullTypeAdapterFactory.class)
    private final Progress progress;
    private final List<FilesRaw> files;

    public SubjectRaw() {
        this.course = new Course();
        this.teacher = new TeacherRaw();
        this.roleTeacher = null;
        this.depart = new Depart();
        this.progress = new Progress();
        this.files = new ArrayList<>();
    }

    public Course getCourse() {
        return course;
    }

    public Role getTeacher() {
        if (roleTeacher == null) {
            this.roleTeacher = RoleExtractorFactory.extractRole(new TeacherExtractorFactory(teacher));
            this.teacher = null;
        }
        return roleTeacher;
    }

    public Depart getDepart() {
        return depart;
    }

    public Progress getProgress() {
        return progress;
    }

    public List<FilesRaw> getFiles() {
        return files;
    }
}

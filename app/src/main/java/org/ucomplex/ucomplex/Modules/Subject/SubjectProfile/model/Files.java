package org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model;

import org.ucomplex.ucomplex.Domain.Users.MaterialsFile;
import org.ucomplex.ucomplex.Domain.Users.role.RoleTeacher;

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

    private RoleTeacher teacher;
    private List<MaterialsFile> materialsFiles;

    public RoleTeacher getTeacher() {
        return teacher;
    }

    public void setTeacher(RoleTeacher teacher) {
        this.teacher = teacher;
    }

    public List<MaterialsFile> getMaterialsFiles() {
        return materialsFiles;
    }

    public void setMaterialsFiles(List<MaterialsFile> materialsFiles) {
        this.materialsFiles = materialsFiles;
    }
}

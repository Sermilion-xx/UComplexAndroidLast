package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model;

import org.ucomplex.ucomplex.Domain.MaterialsFile;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 01/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class MaterialsRaw {

    private List<MaterialsFile> files;

    public List<MaterialsFile> getFiles() {
        return files;
    }

    public void setFiles(List<MaterialsFile> files) {
        this.files = files;
    }
}

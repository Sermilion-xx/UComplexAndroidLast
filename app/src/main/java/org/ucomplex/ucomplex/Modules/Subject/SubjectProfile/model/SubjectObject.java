package org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model;

import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectItemFile;

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

public class SubjectObject {

    private List<SubjectItemProfile> profileItems;
    private List<SubjectItemFile> materialsItems;

    public List<SubjectItemProfile> getProfileItems() {
        return profileItems;
    }

    public void setProfileItems(List<SubjectItemProfile> profileItems) {
        this.profileItems = profileItems;
    }

    public List<SubjectItemFile> getMaterialsItems() {
        return materialsItems;
    }

    public void setMaterialsItems(List<SubjectItemFile> materialsItems) {
        this.materialsItems = materialsItems;
    }
}

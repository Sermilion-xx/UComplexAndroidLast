package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.SubjectModel;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectObject;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectRaw;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 31/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectMaterialsPresenter extends AbstractPresenter<
        SubjectRaw, SubjectObject, Integer, SubjectModel> {


    public void setModel(SubjectModel model) {
        mModel = model;
    }

    @Override
    public void loadData(Integer params) {
        throw new UnsupportedOperationException("Method should not be called.");
    }
}

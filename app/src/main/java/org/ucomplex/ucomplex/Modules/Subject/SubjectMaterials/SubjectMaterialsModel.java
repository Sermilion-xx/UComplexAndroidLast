package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectItemFile;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 01/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectMaterialsModel implements MVPModel<MaterialsRaw, List<SubjectItemFile>, String> {

    private List<SubjectItemFile> mData;
    private SubjectMaterialsService subjectMaterialsService;

    public SubjectMaterialsModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setSubjectMaterialsService(SubjectMaterialsService service) {
        this.subjectMaterialsService = service;
    }

    @Override
    public Observable<MaterialsRaw> loadData(String folder) {
        return subjectMaterialsService.getMaterials(folder).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<SubjectItemFile> data) {
        mData = data;
    }

    @Override
    public void addData(List<SubjectItemFile> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<SubjectItemFile> getData() {
        return mData;
    }

    @Override
    public void processData(MaterialsRaw data) {

    }
}

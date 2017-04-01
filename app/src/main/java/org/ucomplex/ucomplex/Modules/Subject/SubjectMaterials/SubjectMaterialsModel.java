package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import android.support.v4.util.Pair;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectItemFile;

import java.util.ArrayList;
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

public class SubjectMaterialsModel implements MVPModel<MaterialsRaw, List<SubjectItemFile>, SubjectMaterialsParams> {

    private List<SubjectItemFile> mData;
    private SubjectMaterialsService subjectMaterialsService;
    private List<Pair<List<SubjectItemFile> , String>> mPageHistory;
    private int currentPage = -1;
    private String currentFolder = null;

    public void setCurrentFolder(String currentFolder) {
        this.currentFolder = currentFolder;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void pageUp() {
        currentPage++;
    }

    public void pageDown() {
        currentPage--;
        if(currentPage<1){
            currentFolder = "null";
        }else {
            currentFolder = getHistory(currentPage).second;
        }
    }

    void addHistory(Pair<List<SubjectItemFile>, String> list) {
        this.mPageHistory.add(list);
    }

    int getHistoryCount(){
        return this.mPageHistory.size();
    }

    public Pair<List<SubjectItemFile>, String> getHistory(int index) {
        if(index<this.mPageHistory.size()){
            return this.mPageHistory.get(index);
        }else {
            return null;
        }
    }

    public SubjectMaterialsModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        mPageHistory = new ArrayList<>();
    }

    @Inject
    public void setSubjectMaterialsService(SubjectMaterialsService service) {
        this.subjectMaterialsService = service;
    }

    @Override
    public Observable<MaterialsRaw> loadData(SubjectMaterialsParams folder) {
        return subjectMaterialsService.getMaterials(folder.getFolder()).subscribeOn(Schedulers.io())
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

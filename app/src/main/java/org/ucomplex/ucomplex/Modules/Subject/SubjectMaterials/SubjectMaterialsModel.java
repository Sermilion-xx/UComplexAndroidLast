package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import android.support.v4.util.Pair;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.Users.File;
import org.ucomplex.ucomplex.Domain.Users.Teacher;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectItemFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public class SubjectMaterialsModel implements MVPModel<MaterialsRaw, List<Pair<List<SubjectItemFile>, String>>, SubjectMaterialsParams> {

    private static final String DEFAULT_FOLDER_NAME = "null";
    private SubjectTeachersMaterialsService subjectTeachersMaterialsService;
    private List<Pair<List<SubjectItemFile> , String>> mPageHistory;
    private Map<Integer, Teacher> mTeachers;
    private int currentPage = 0;
    private String currentFolder = "null";

    public void setTeachers(Map<Integer, Teacher> mTeachers) {
        this.mTeachers = mTeachers;
    }

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
            currentFolder = DEFAULT_FOLDER_NAME;
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
    public void setSubjectTeachersMaterialsService(SubjectTeachersMaterialsService service) {
        this.subjectTeachersMaterialsService = service;
    }

    @Override
    public Observable<MaterialsRaw> loadData(SubjectMaterialsParams folder) {
        return subjectTeachersMaterialsService.getMaterials(folder.getFolder()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<Pair<List<SubjectItemFile>, String>> data) {
        mPageHistory = data;
    }

    @Override
    public void addData(List<Pair<List<SubjectItemFile>, String>> data) {
        mPageHistory.addAll(data);
    }

    @Override
    public void clear() {
        mPageHistory.clear();
    }

    @Override
    public List<Pair<List<SubjectItemFile>, String>> getData() {
        return mPageHistory;
    }

    @Override
    public void processData(MaterialsRaw data) {
        List<SubjectItemFile> list = new ArrayList<>();
        for (File file : data.getFiles()) {
            list.add(extractFileItem(file));
        }
        mPageHistory.add(new Pair<>(list, ""));
    }

    private SubjectItemFile extractFileItem(File file) {
        SubjectItemFile item = new SubjectItemFile();
        item.setAddress(file.getAddress());
        item.setName(file.getName());
        item.setData(file.getData());
        item.setOwnersName(mTeachers.get(file.getOwner()).getName());
        item.setSize(file.getSize());
        item.setType(file.getType());
        item.setTime(file.getTime());
        return item;
    }

}

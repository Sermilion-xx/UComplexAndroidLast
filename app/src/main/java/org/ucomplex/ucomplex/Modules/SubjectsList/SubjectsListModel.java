package org.ucomplex.ucomplex.Modules.SubjectsList;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.SubjectsList.model.StudentSubjectList;
import org.ucomplex.ucomplex.Modules.SubjectsList.model.SubjectsListItem;
import org.ucomplex.ucomplex.Modules.SubjectsList.model.SubjectsListRaw;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectsListModel implements MVPModel<SubjectsListRaw, List<SubjectsListItem>, Void> {

    private List<SubjectsListItem> mDate;
    private SubjectsListService mService;

    private SubjectsListModel() {

    }

    private void inject() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    public static SubjectsListModel getInstance() {
        SubjectsListModel model = new SubjectsListModel();
        model.inject();
        return model;
    }

    public static SubjectsListModel getTestInstance() {
        return new SubjectsListModel();
    }

    @Inject
    public void setSubjectsListService(SubjectsListService service) {
        this.mService = service;
    }

    @Override
    public Observable<SubjectsListRaw> loadData(Void params) {
        return mService.getSubjectList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<SubjectsListItem> data) {
        mDate = data;
    }

    @Override
    public void addData(List<SubjectsListItem> data) {
        mDate.addAll(data);
    }

    @Override
    public void clear() {
        mDate.clear();
    }

    @Override
    public List<SubjectsListItem> getData() {
        return mDate;
    }

    @Override
    public void processData(SubjectsListRaw data) {
        mDate = new ArrayList<>();
        for (int i = 0; i < data.getStudentSubjectsList().size(); i++) {
            SubjectsListItem item = new SubjectsListItem();
            StudentSubjectList subject = data.getStudentSubjectsList().get(i);
            item.setAssessmentType(data.getCourses_forms().get(subject.getCourse()));
            item.setCourseId(subject.getCourse());
            item.setCourseName(data.getCourses().get(subject.getCourse()));
            mDate.add(item);
        }
    }
}

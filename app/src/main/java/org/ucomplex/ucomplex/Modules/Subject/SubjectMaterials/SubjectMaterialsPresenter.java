package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;


import android.support.v4.util.Pair;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.MaterialsRaw;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectItemFile;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectMaterialsParams;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
        MaterialsRaw, List<Pair<List<SubjectItemFile>, String>>, SubjectMaterialsParams, SubjectMaterialsModel> {

    public SubjectMaterialsPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setModel(SubjectMaterialsModel model) {
        mModel = model;
    }

    void setMaterialsItems(List<SubjectItemFile> items) {
        mModel.addHistory(new Pair<>(items, ""));
        pageUp();
    }

    private void pageUp() {
        mModel.pageUp();
    }

    void pageDown() {
        mModel.pageDown();
        Pair<List<SubjectItemFile>, String> history = getHistory(mModel.getCurrentPage());
        if (getView() != null) {
            ((SubjectMaterialsFragment) getView()).getAdapter().setItems(history.first);
        }
    }

    public int getCurrentPage() {
        return mModel.getCurrentPage();
    }

    private void addHistory(Pair<List<SubjectItemFile>, String> list) {
        mModel.addHistory(list);
    }

    private Pair<List<SubjectItemFile>, String> getHistory(int index) {
        return mModel.getHistory(index);
    }

    public List<SubjectItemFile> getCurrentHistory() {
        Pair<List<SubjectItemFile>, String> pair = mModel.getHistory(mModel.getCurrentPage());
        if (pair != null) {
            return pair.first;
        }
        return null;
    }

    @Override
    public void loadData(SubjectMaterialsParams params) {
        if (mModel.getHistoryCount() > 1 && !params.isMyFolder()) {
            if (getView() != null) {
                pageUp();
                getView().dataLoaded();
            }
        } else {
            Observable<MaterialsRaw> dataObservable = mModel.loadData(params);
            dataObservable.subscribe(new Observer<MaterialsRaw>() {
                @Override
                public void onSubscribe(Disposable d) {
                    showProgress();
                }

                @Override
                public void onNext(MaterialsRaw value) {
                    mModel.processData(value);
                    if (getView() != null) {
                        mModel.setCurrentFolder(params.getFolderName());
                        pageUp();
                        getView().dataLoaded();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    hideProgress();
                }

                @Override
                public void onComplete() {
                    hideProgress();
                }
            });
        }
    }
}

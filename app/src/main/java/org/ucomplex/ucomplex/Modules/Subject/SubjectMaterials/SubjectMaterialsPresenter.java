package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;



import android.support.v4.util.Pair;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectItemFile;

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

    public void setMaterialsItems(List<SubjectItemFile> items) {
        mModel.addHistory(new Pair<>(items, ""));
    }

    private void pageUp() {
        mModel.pageUp();
    }

    void pageDown() {
        mModel.pageDown();
        Pair<List<SubjectItemFile>, String> history = getHistory(mModel.getCurrentPage());
        if (getView() != null) {
            ((SubjectMaterialsFragment)getView()).getAdapter().setItems(history.first);
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
        return mModel.getHistory(mModel.getCurrentPage()).first;
    }

    @Override
    public void loadData(SubjectMaterialsParams params) {
        Observable<MaterialsRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<MaterialsRaw>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(MaterialsRaw value) {
                mModel.processData(value);
                if(getView()!=null){
                    mModel.setCurrentFolder(params.getFolderName());
                    pageUp();
                    ((SubjectMaterialsFragment)getView()).dataLoaded();
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

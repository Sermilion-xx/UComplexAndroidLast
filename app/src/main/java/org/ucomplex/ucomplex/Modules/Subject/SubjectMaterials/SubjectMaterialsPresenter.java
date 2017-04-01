package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

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
        MaterialsRaw, List<SubjectItemFile>, String, SubjectMaterialsModel> {


    public SubjectMaterialsPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setModel(SubjectMaterialsModel model) {
        mModel = model;
    }

    public void setMaterialsItems(List<SubjectItemFile> items) {
        mModel.setData(items);
    }

    @Override
    public void loadData(String folder) {
        Observable<MaterialsRaw> dataObservable = mModel.loadData(folder);
        dataObservable.subscribe(new Observer<MaterialsRaw>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(MaterialsRaw value) {
                mModel.processData(value);
                if(getView()!=null){
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

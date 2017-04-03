package org.ucomplex.ucomplex.Modules.Subject.SubjectProfile;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model.SubjectObject;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model.SubjectRaw;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectPresenter extends AbstractPresenter<
        SubjectRaw, SubjectObject,
        Integer, SubjectModel> {

    public SubjectPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setModel(SubjectModel model) {
        mModel = model;
    }

    @Override
    public void loadData(Integer gcourse) {
        Observable<SubjectRaw> dataObservable = mModel.loadData(gcourse);
        dataObservable.subscribe(new Observer<SubjectRaw>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(SubjectRaw value) {
                mModel.processData(value);
                if(getView()!=null){
                    ((SubjectProfileFragment)getView()).dataLoaded();
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

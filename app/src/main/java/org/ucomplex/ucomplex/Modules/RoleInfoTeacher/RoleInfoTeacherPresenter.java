package org.ucomplex.ucomplex.Modules.RoleInfoTeacher;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoTeacherRaw;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoteacherItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleInfoTeacherPresenter extends AbstractPresenter<
        RoleInfoTeacherRaw, List<RoleInfoteacherItem>,
        Integer, RoleInfoTeacherModel> {

    public RoleInfoTeacherPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(Integer params) {
        Observable<RoleInfoTeacherRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<RoleInfoTeacherRaw>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(RoleInfoTeacherRaw value) {
                mModel.processData(value);
                if (getView() != null) {
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

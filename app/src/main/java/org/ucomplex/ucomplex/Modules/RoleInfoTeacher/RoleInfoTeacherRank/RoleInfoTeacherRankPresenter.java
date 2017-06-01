package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank;

import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Login.model.LoginUser;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model.RoleInfoTeacherRankItem;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model.RoleInfoTeacherRankRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RoleInfoTeacherRankPresenter extends AbstractPresenter<
        RoleInfoTeacherRankRaw, List<RoleInfoTeacherRankItem>,
        Integer, RoleInfoTeacherRankModel> {

    public RoleInfoTeacherRankPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(Integer params) {
        Observable<RoleInfoTeacherRankRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<RoleInfoTeacherRankRaw>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(RoleInfoTeacherRankRaw value) {
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

    public void rate(SparseIntArray rating) {
        mModel.rate(rating).subscribe(new Observer<LoginUser>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(LoginUser value) {
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
                if (getView() != null) {
                    getView().dataLoaded();
                }
            }
        });
    }
}
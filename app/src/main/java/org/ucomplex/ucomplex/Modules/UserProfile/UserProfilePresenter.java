package org.ucomplex.ucomplex.Modules.UserProfile;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.interfaces.DownloadCallback;
import org.ucomplex.ucomplex.Modules.UserProfile.model.ResponseAddFriend;
import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileItem;
import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 03/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UserProfilePresenter extends AbstractPresenter<
        UserProfileRaw, List<UserProfileItem>,
        Integer, UserProfileModel> {

    public UserProfilePresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(Integer params) {
        Observable<UserProfileRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<UserProfileRaw>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(UserProfileRaw value) {
                mModel.processData(value);
                if(getView()!=null){
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

    public void addAsFriend(int user, DownloadCallback callback) {
        Observable<ResponseAddFriend> observable = mModel.addAsFriend(user);
        observable.subscribe(new Observer<ResponseAddFriend>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseAddFriend value) {

            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void block(int user, DownloadCallback callback) {
        Observable<Void> observable = mModel.block(user);
        observable.subscribe(new Observer<Void>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Void value) {

            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void unblock(int user, DownloadCallback callback) {
        Observable<Void> observable = mModel.unblock(user);
        observable.subscribe(new Observer<Void>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Void value) {

            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}

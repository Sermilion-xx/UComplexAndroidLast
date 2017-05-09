package org.ucomplex.ucomplex.Modules.UserProfile;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
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
}

package org.ucomplex.ucomplex.Modules.Users;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.Modules.Users.model.UserRequestType;
import org.ucomplex.ucomplex.Modules.Users.model.UsersParams;
import org.ucomplex.ucomplex.Modules.Users.model.UsersRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 14/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UsersPresenter extends AbstractPresenter<
        UsersRaw, List<User>,
        UsersParams, UsersModel> {

    private UserRequestType userRequestType;

    public UsersPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    public void setUserRequestType(UserRequestType userRequestType) {
        this.userRequestType = userRequestType;
    }

    @Override
    public void loadData(UsersParams params) {
        params.setRequestType(userRequestType);
        Observable<UsersRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<UsersRaw>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(UsersRaw value) {
                List<User> users = mModel.processData(value);
                if (getView() != null) {
                    ((UsersFragment) getView()).dataLoaded(users);
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

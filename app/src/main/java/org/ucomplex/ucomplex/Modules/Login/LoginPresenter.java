package org.ucomplex.ucomplex.Modules.Login;

import android.text.TextUtils;

import org.ucomplex.ucomplex.Common.FacadePreferences;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.ActivityExtensions;
import org.ucomplex.ucomplex.Domain.LoginErrorType;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.Modules.Login.model.LoginUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.ucomplex.ucomplex.Domain.LoginErrorType.EMPTY_EMAIL;
import static org.ucomplex.ucomplex.Domain.LoginErrorType.INVALID_PASSWORD;
import static org.ucomplex.ucomplex.Domain.LoginErrorType.NO_ERROR;
import static org.ucomplex.ucomplex.Domain.LoginErrorType.PASSWORD_REQUIRED;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class LoginPresenter extends AbstractPresenter<LoginUser, UserInterface, LoginParams, LoginModel> {

    @Inject
    public void setModel(LoginModel model) {
        mModel = model;
    }

    public LoginPresenter () {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData() {
        Observable<LoginUser> dataObservable = mModel.loadData(mRequestParams);
        dataObservable.subscribe(new Observer<LoginUser>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(LoginUser value) {
                mModel.processData(value);
                if (getView() != null) {
                    ((LoginActivity)getView()).onLogin();
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

    public void saveLoginData () {
        FacadePreferences.setUserDataToPref(getActivityContext(), getData());
    }

    public void restorePassword(String email) {
        //TODO
    }

    public List<LoginErrorType> checkCredentials() {
        List<LoginErrorType> error = runCheck();
        if (error.get(0) == NO_ERROR) {
            loadData();
        }
        return error;
    }

    private List<LoginErrorType> runCheck() {
        List<LoginErrorType> errors = new ArrayList<>();
        String password = mRequestParams.getLogin();
        String login = mRequestParams.getPassword();
        if (TextUtils.isEmpty(password)) {
            errors.add(PASSWORD_REQUIRED);
        } else if (!isPasswordValid(password)) {
            errors.add(INVALID_PASSWORD);
        }
        if (TextUtils.isEmpty(login)) {
            errors.add(EMPTY_EMAIL);
        }
        if (errors.size() == 0) {
            errors.add(NO_ERROR);
        }
        return errors;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 3;
    }
}

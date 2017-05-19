package org.ucomplex.ucomplex.Modules.Login;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Modules.Events.EventsActivity;
import org.ucomplex.ucomplex.Modules.Login.model.LoginErrorType;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.Modules.Login.model.LoginUser;
import org.ucomplex.ucomplex.Modules.RoleSelect.RoleSelectActivity;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.ucomplex.ucomplex.Modules.Login.model.LoginErrorType.LOGIN_REQUIRED;
import static org.ucomplex.ucomplex.Modules.Login.model.LoginErrorType.INVALID_PASSWORD;
import static org.ucomplex.ucomplex.Modules.Login.model.LoginErrorType.NO_ERROR;
import static org.ucomplex.ucomplex.Modules.Login.model.LoginErrorType.PASSWORD_REQUIRED;

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

    private static final int ROLE_ONE = 0;

    @Inject
    public void setModel(LoginModel model) {
        mModel = model;
    }

    public LoginPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(LoginParams mRequestParams) {
        Observable<LoginUser> dataObservable = mModel.loadData(mRequestParams);
        dataObservable.subscribe(new Observer<LoginUser>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(LoginUser value) {
                if (value == null) {
                    onError(new Throwable("Ошибка"));
                } else {
                    forwardPostLogin(value);
                }
            }

            @Override
            public void onError(Throwable e) {
                hideProgress();
                if (getView() != null) {
                    getView().showToast(R.string.authorization_error, Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onComplete() {
                hideProgress();
            }
        });
    }

    private void forwardPostLogin(LoginUser loginUser) {
        Intent intent;
        if (loginUser.getRoles().size() == 1) {
            UserInterface userInterface = loginUser.extractUser(ROLE_ONE);
            mModel.setData(userInterface);
            persistLoginInfo();
            intent = EventsActivity.creteIntent(getActivityContext());
        } else {
            intent = RoleSelectActivity.creteIntent(getActivityContext(), loginUser);
        }
        if (getView() != null) {
            getView().dataLoaded();
        }
        getActivityContext().startActivity(intent);
    }

    private void persistLoginInfo() {
        String authData = getData().getLogin() + ":" + getData().getPassword() + ":" + getData().getId();
        UCApplication.getInstance().saveLoginData(authData, getData());
    }

    void restorePassword(String email) {
        //TODO
    }

    List<LoginErrorType> checkCredentials(LoginParams mRequestParams) {
        List<LoginErrorType> error = runCheck(mRequestParams);
        return error;
    }

    private List<LoginErrorType> runCheck(LoginParams mRequestParams) {
        List<LoginErrorType> errors = new ArrayList<>();
        String login = mRequestParams.getLogin();
        String password = mRequestParams.getPassword();
        if (TextUtils.isEmpty(password)) {
            errors.add(PASSWORD_REQUIRED);
        } else if (!isPasswordValid(password)) {
            errors.add(INVALID_PASSWORD);
        }
        if (TextUtils.isEmpty(login)) {
            errors.add(LOGIN_REQUIRED);
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

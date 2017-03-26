package org.ucomplex.ucomplex.Modules.Login;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FacadePreferences;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.Users.UserFactory;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.Modules.Login.model.LoginUser;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class LoginModel implements MVPModel<LoginUser, UserInterface, LoginParams> {

    private UserInterface userInterface ;
    private LoginService loginService;

    public LoginModel () {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setLoginService(LoginService service) {
        this.loginService = service;
    }

    @Override
    public Observable<LoginUser> loadData(LoginParams params) {
        String loginData = FacadeCommon.encodeLoginData(params.getLogin().substring(2) + ":" + params.getPassword());
        FacadePreferences.setLoginDataToPref(params.getContext(), loginData);
        return loginService.login().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(UserInterface data) {
        this.userInterface = data;
    }

    @Override
    public void addData(UserInterface data) {
        this.userInterface = data;
    }

    @Override
    public void clear() {
        this.userInterface = null;
    }

    @Override
    public UserInterface getData() {
        return this.userInterface;
    }

    @Override
    public UserInterface processData(LoginUser data) {
        userInterface = UserFactory.getUserForType(0);
        userInterface.setRoles(data.getRoles());
        userInterface.setMobile(data.getSession().getMobile());
        userInterface.setCode(data.getSession().getCode());
        userInterface.setPhoto(data.getSession().getPhoto());
        userInterface.setName(data.getSession().getName());
        userInterface.setLogin(data.getSession().getLogin());
        userInterface.setPassword(data.getSession().getPass());
        userInterface.setPhone(data.getSession().getPhone());
        userInterface.setEmail(data.getSession().getEmail());
        userInterface.setSession(data.getSession().getSession());
        userInterface.setPerson(data.getSession().getPerson());
        userInterface.setClient(data.getSession().getClient());
        return userInterface;
    }
}

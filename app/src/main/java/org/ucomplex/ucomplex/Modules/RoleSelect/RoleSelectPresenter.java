package org.ucomplex.ucomplex.Modules.RoleSelect;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.Modules.Login.model.LoginUser;

import java.util.List;

import javax.inject.Inject;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 26/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleSelectPresenter extends AbstractPresenter<LoginUser, List<RoleItem>, LoginUser, RoleSelectModel> {

    private static final String TOKEN_SEPARATOR = ":";

    public RoleSelectPresenter () {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setModel(RoleSelectModel model) {
        mModel = model;
    }

    @Override
    public void loadData(LoginUser params) {
        if (getView() != null) {
            mModel.setLoginUser(params);
            mModel.processData(params);
            getView().dataLoaded();
        }
    }

    void onRoleSelected(int position) {
        UserInterface user = mModel.extractUser(position);
        if (user != null) {
            String authString = user.getLogin() + TOKEN_SEPARATOR + user.getPassword() + TOKEN_SEPARATOR + user.getRoles().get(position).getRole();
            FacadeCommon.saveLoginData(authString, user);
        }
    }
}

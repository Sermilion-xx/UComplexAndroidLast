package org.ucomplex.ucomplex.Modules.RoleSelect;

import org.ucomplex.ucomplex.Common.FacadePreferences;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.ucomplex.ucomplex.Common.FacadeCommon.encodeLoginData;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 26/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleSelectPresenter extends AbstractPresenter<UserInterface, List<RoleItem>, UserInterface, RoleSelectModel> {

    private static final String TOKEN_SEPARATOR = ":";

    public RoleSelectPresenter () {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setModel(RoleSelectModel model) {
        mModel = model;
    }

    @Override
    public void loadData(UserInterface params) {
        if (getView() != null) {
            mModel.processData(params);
            ((RoleSelectActivity) getView()).initRecyclerView(getData());
        }
    }

    public void onRoleSelected(int position) {
        UserInterface user = UCApplication.getInstance().getLoggedUser();
        String login = user.getLogin();
        String password = user.getPassword();
        int roleId = user.getRoles().get(position).getId();
        user.setType(user.getRoles().get(position).getType());
        String encodedAuth = encodeLoginData(login + TOKEN_SEPARATOR + password + TOKEN_SEPARATOR + roleId);
        FacadePreferences.setLoginDataToPref(getActivityContext(), encodedAuth, true);
        UCApplication.getInstance().setLoggedUser(user);
    }

    public void onDestroy() {
        mModel = null;
    }
}

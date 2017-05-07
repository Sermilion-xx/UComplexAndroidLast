package org.ucomplex.ucomplex.Modules.RoleSelect;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.Users.Role;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.Modules.Login.model.LoginUser;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 26/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleSelectModel implements MVPModel<LoginUser, List<RoleItem>, LoginUser> {

    private List<RoleItem> mData;
    private LoginUser loginUser;

    private int[] roleIcons = {
            R.drawable.role_select_1,
            R.drawable.role_select_2,
            R.drawable.role_select_3,
            R.drawable.role_select_4,
            R.drawable.role_select_5};

    void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    UserInterface extractUser(int rolePos) {
        return loginUser.extractUser(rolePos);
    }

    @Override
    public Observable<LoginUser> loadData(LoginUser params) {
        throw new UnsupportedOperationException("Method is not implemented for selecting role.");
    }

    @Override
    public void setData(List<RoleItem> data) {
        mData = data;
    }

    @Override
    public void addData(List<RoleItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<RoleItem> getData() {
        return mData;
    }

    @Override
    public List<RoleItem> processData(LoginUser user) {
        List<RoleItem> roles = new ArrayList<>();
        if (user!=null) {
            Random random = new Random();
            for (int i = 0; i < user.getRoles().size(); i++) {
                Role role = user.getRoles().get(i);
                String roleStr = "";
                if (role.getType() == 3) {
                    roleStr = UCApplication.getInstance().getResources().getString(R.string.prepodvatel);
                } else if (role.getType() == 4) {
                    roleStr = UCApplication.getInstance().getResources().getString(R.string.student);
                } else if (role.getType() == 0) {
                    roleStr = UCApplication.getInstance().getResources().getString(R.string.sotrudnik);
                } else if (role.getType() == 3) {
                    roleStr = UCApplication.getInstance().getResources().getString(R.string.prepodvatel);
                }
                int index = random.nextInt(5);
                roles.add(new RoleItem(roleIcons[index], roleStr));
            }
        }
        mData = roles;
        return mData;
    }
}

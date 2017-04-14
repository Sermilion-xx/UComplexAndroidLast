package org.ucomplex.ucomplex.Modules.Users;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.Modules.Users.model.UserRequestType;
import org.ucomplex.ucomplex.Modules.Users.model.UsersParams;
import org.ucomplex.ucomplex.Modules.Users.model.UsersRaw;
import org.ucomplex.ucomplex.Modules.Users.retrofit.UsersService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 13/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UsersModel implements MVPModel<UsersRaw, List<User>, UsersParams> {

    private List<User> mData;
    private UsersService usersService;

    public UsersModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    void setUsersService(UsersService service) {
        this.usersService = service;
    }

    @Override
    public Observable<UsersRaw> loadData(UsersParams params) {
        Observable<UsersRaw> observable = null;
        if (params.getRequestType() == UserRequestType.ONLINE) {
            observable =  usersService.getOnlineUsers(params.getStart());
        } else if (params.getRequestType() == UserRequestType.FRIENDS) {
            observable =  usersService.getFriendsUsers(params.getStart());
        } else if (params.getRequestType() == UserRequestType.GROUP) {
            observable =  usersService.getGroupUsers(params.getStart());
        } else if (params.getRequestType() == UserRequestType.TEACHERS) {
            observable =  usersService.getTeachersUsers(params.getStart());
        } else if (params.getRequestType() == UserRequestType.BLACKLIST) {
            observable =  usersService.getBlacklistUsers(params.getStart());
        }
        if (observable != null) {
            return observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
        return null;
    }

    @Override
    public void setData(List<User> data) {
        mData = data;
    }

    @Override
    public void addData(List<User> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<User> getData() {
        return mData;
    }

    @Override
    public List<User> processData(UsersRaw data) {
        if (data.getFriends() != null) {
            mData = data.getFriends();
            return data.getFriends();
        } else {
            mData = data.getUsers();
            return data.getUsers();
        }
    }
}

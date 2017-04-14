package org.ucomplex.ucomplex.Modules.Users.UsersOnline;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.Modules.Users.model.UserItem;
import org.ucomplex.ucomplex.Modules.Users.model.UsersParams;
import org.ucomplex.ucomplex.Modules.Users.model.UsersRaw;
import org.ucomplex.ucomplex.Modules.Users.retrofit.UsersOnlineService;

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

public class UsersOnlineModel implements MVPModel<UsersRaw, List<User>, UsersParams> {

    private List<User> mData;
    private UsersOnlineService service;

    public UsersOnlineModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setService(UsersOnlineService service) {
        this.service = service;
    }

    @Override
    public Observable<UsersRaw> loadData(UsersParams params) {
        return service.getOnlineUsers(params.getStart()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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
        mData = data.getUsers();
        return data.getUsers();
    }
}

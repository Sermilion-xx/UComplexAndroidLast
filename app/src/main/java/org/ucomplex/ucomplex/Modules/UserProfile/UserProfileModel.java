package org.ucomplex.ucomplex.Modules.UserProfile;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileItem;
import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileRaw;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 03/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UserProfileModel implements MVPModel<UserProfileRaw, List<UserProfileItem>, Integer> {

    private List<UserProfileItem> mData;
    private UserProfileService mService;

    public UserProfileModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setService(UserProfileService service) {
        this.mService = service;
    }

    @Override
    public Observable<UserProfileRaw> loadData(Integer params) {
        return mService.getUserProfile(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<UserProfileItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<UserProfileItem> data) {
        this.mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<UserProfileItem> getData() {
        return mData;
    }

    @Override
    public List<UserProfileItem> processData(UserProfileRaw data) {
        List<UserProfileItem>  items = new ArrayList<>();
        UserInterface user = data.extractUser();
        String positionName = FacadeCommon.getStringUserType(UCApplication.getInstance(), user.getType());
        UserProfileItem header = new UserProfileItem(user.getName(),
                                                     positionName,
                                                     user.getIsFriend(),
                                                     user.getIsBlack());
        items.add(header);
        for (int i = 0; i < user.getRoles().size(); i++) {
            UserProfileItem item = new UserProfileItem(user.getRoles().get(i));
            items.add(item);
        }
        mData = items;
        return items;
    }
}

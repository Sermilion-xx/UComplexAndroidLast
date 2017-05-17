package org.ucomplex.ucomplex.Modules.UserProfile;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.Domain.Users.role.Role;
import org.ucomplex.ucomplex.Domain.Users.role.RoleBase;
import org.ucomplex.ucomplex.Domain.Users.role.RoleStudent;
import org.ucomplex.ucomplex.Domain.Users.role.RoleTeacher;
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
                                                     user.getIsBlack(),
                                                     user.getCode(),
                                                     user.getId());
        items.add(header);
        for (int i = 0; i < user.getRoles().size(); i++) {
            //request for user profile returns json which has fields of Teacher role
            //so we use RoleTeacher class to hold the data
            Role role = user.getRoles().get(i);
            int type = role.getType();
            String rolePositionName;
            String sectionName;
            if (role instanceof RoleTeacher) {
                rolePositionName = role.getPosition_name();
                sectionName = ((RoleTeacher) role).getSection_name();
            } else if((role instanceof RoleStudent)) {
                rolePositionName = FacadeCommon.getStringUserType(UCApplication.getInstance(), role.getType());
                sectionName = role.getPosition_name();
            } else {
                rolePositionName = FacadeCommon.getStringUserType(UCApplication.getInstance(), role.getType());
                sectionName = role.getPosition_name();
            }
            UserProfileItem item = new UserProfileItem(rolePositionName, sectionName);
            items.add(item);
        }
        mData = items;
        return items;
    }
}

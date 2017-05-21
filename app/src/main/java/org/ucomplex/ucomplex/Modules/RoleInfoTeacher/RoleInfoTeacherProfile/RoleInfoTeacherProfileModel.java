package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherProfile;

import android.content.Context;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoTeacherRaw;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoTeacherItem;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleInfoTeacherProfileModel implements MVPModel<RoleInfoTeacherRaw, List<RoleInfoTeacherItem>, Integer> {

    private List<RoleInfoTeacherItem> mData;
    private RoleInfoTeacherProfileService mService;

    public RoleInfoTeacherProfileModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    private RoleInfoTeacherProfileModel(boolean ignored) {

    }

    public static RoleInfoTeacherProfileModel createTestModel() {
        return new RoleInfoTeacherProfileModel(true);
    }

    @Inject
    public void setService(RoleInfoTeacherProfileService service) {
        this.mService = service;
    }

    @Override
    public Observable<RoleInfoTeacherRaw> loadData(Integer params) {
        return mService.getRoleInfo(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<RoleInfoTeacherItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<RoleInfoTeacherItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<RoleInfoTeacherItem> getData() {
        return mData;
    }

    @Override
    public List<RoleInfoTeacherItem> processData(RoleInfoTeacherRaw data) {
        Context context = UCApplication.getInstance();
        List<RoleInfoTeacherItem> list = new ArrayList<>();
        list.add(new RoleInfoTeacherItem(context.getString(R.string.activity), data.getActivity()));
        if(data.getFaculty_name().length() > 0 && data.getDepartment_name().length() > 0){
            list.add(new RoleInfoTeacherItem(context.getString(R.string.faculty), data.getFaculty_name()));
            list.add(new RoleInfoTeacherItem(context.getString(R.string.department), data.getDepartment_name()));
        }else {
            list.add(new RoleInfoTeacherItem(context.getString(R.string.profile_is_closed), ""));
        }
        mData = list;
        return mData;
    }
}

package org.ucomplex.ucomplex.Modules.RoleInfoTeacher;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoTeacherRaw;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoteacherItem;

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

public class RoleInfoTeacherModel implements MVPModel<RoleInfoTeacherRaw, List<RoleInfoteacherItem>, Integer> {

    private List<RoleInfoteacherItem> mData;
    private RoleInfoTeacherService mService;

    public RoleInfoTeacherModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    private RoleInfoTeacherModel(boolean ignored) {

    }

    public static RoleInfoTeacherModel createTestModel() {
        return new RoleInfoTeacherModel(true);
    }

    @Inject
    public void setService(RoleInfoTeacherService service) {
        this.mService = service;
    }

    @Override
    public Observable<RoleInfoTeacherRaw> loadData(Integer params) {
        return mService.getRoleInfo(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<RoleInfoteacherItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<RoleInfoteacherItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<RoleInfoteacherItem> getData() {
        return mData;
    }

    @Override
    public List<RoleInfoteacherItem> processData(RoleInfoTeacherRaw data) {

        return mData;
    }

}

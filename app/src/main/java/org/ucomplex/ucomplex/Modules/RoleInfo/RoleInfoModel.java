package org.ucomplex.ucomplex.Modules.RoleInfo;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoItem;
import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoRaw;

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

public class RoleInfoModel implements MVPModel<RoleInfoRaw, List<RoleInfoItem>, Integer> {

    private List<RoleInfoItem> mData;
    private RoleInfoService mService;

    public RoleInfoModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setService(RoleInfoService service) {
        this.mService = service;
    }

    @Override
    public Observable<RoleInfoRaw> loadData(Integer params) {
        return mService.getRoleInfo(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<RoleInfoItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<RoleInfoItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<RoleInfoItem> getData() {
        return mData;
    }

    @Override
    public List<RoleInfoItem> processData(RoleInfoRaw data) {
        return null;
    }
}

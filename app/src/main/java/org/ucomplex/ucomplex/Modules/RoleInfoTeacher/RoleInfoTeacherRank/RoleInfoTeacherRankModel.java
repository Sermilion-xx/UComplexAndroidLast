package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model.RoleInfoTeacherRankItem;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model.RoleInfoTeacherRankRaw;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class RoleInfoTeacherRankModel implements MVPModel<RoleInfoTeacherRankRaw, List<RoleInfoTeacherRankItem>, Integer> {

    private List<RoleInfoTeacherRankItem> mData;
    private RoleInfoTeacherRankService mService;

    public RoleInfoTeacherRankModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    private RoleInfoTeacherRankModel(boolean ignored) {

    }

    public static RoleInfoTeacherRankModel createTestModel() {
        return new RoleInfoTeacherRankModel(true);
    }

    @Inject
    public void setService(RoleInfoTeacherRankService service) {
        this.mService = service;
    }

    @Override
    public Observable<RoleInfoTeacherRankRaw> loadData(Integer params) {
        return mService.getRank(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<RoleInfoTeacherRankItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<RoleInfoTeacherRankItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<RoleInfoTeacherRankItem> getData() {
        return mData;
    }

    @Override
    public List<RoleInfoTeacherRankItem> processData(RoleInfoTeacherRankRaw data) {
        return mData;
    }

}

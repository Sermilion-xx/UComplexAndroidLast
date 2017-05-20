package org.ucomplex.ucomplex.Modules.RoleInfo;

import android.content.Context;
import android.support.v4.util.Pair;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoRaw;
import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

public class RoleInfoModel implements MVPModel<RoleInfoRaw, List<Pair<String, String>>, Integer> {

    private List<Pair<String, String>> mData;
    private RoleInfoService mService;

    public RoleInfoModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    private RoleInfoModel(boolean ignored) {

    }

    public static RoleInfoModel createTestModel() {
        return new RoleInfoModel(true);
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
    public void setData(List<Pair<String, String>> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<Pair<String, String>> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<Pair<String, String>> getData() {
        return mData;
    }

    @Override
    public List<Pair<String, String>> processData(RoleInfoRaw data) {
        Context context = UCApplication.getInstance();
        String[] keys = new String[] {
                "Год поступления:",
                "Форма обучения:",
                "Уровень образования:",
                "Форма оплаты:",
                "Факультет:",
                "Специальность:",
                "Группа:",
                "Общая посещаемость:",
                "Средняя успеваемость:",
                "Место в общем рейтинге:",
                "Место в рейтинге по факультету:"};
        List<Pair<String, String>> items = new ArrayList<>();
        String lastOnline = getLastOnline(data.getOnline());
        items.add(new Pair<>(FacadeCommon.getStringUserType(context, data.getType()), lastOnline));
        items.add(new Pair<>(keys[0], String.valueOf(data.getYear())));
        items.add(new Pair<>(keys[1], RoleInfoUtils.getStudyForm(context, data.getStudy())));
        items.add(new Pair<>(keys[2], RoleInfoUtils.getStudyLevel(context, data.getStudy_level())));
        items.add(new Pair<>(keys[3], RoleInfoUtils.getPayment(context, data.getPayment())));
        items.add(new Pair<>(keys[4], data.getFaculty_name()));
        items.add(new Pair<>(keys[5], data.getMajor_name()));
        items.add(new Pair<>(keys[6], data.getGroup_name()));
        double marks = FacadeCommon.round((double) data.getProgress().getMark() / (double)data.getProgress().getMarksCount(), 2);
        items.add(new Pair<>(keys[7], String.valueOf(marks)));
        double attendance =  FacadeCommon.round(100-((double) data.getProgress().getAbsence() / (double) data.getProgress().getHours())*100,2);
        items.add(new Pair<>(keys[8], String.valueOf(attendance)));
        items.add(new Pair<>(keys[9], String.valueOf(data.getRating().getGeneral())));
        items.add(new Pair<>(keys[10], String.valueOf(data.getRating().getFaculty())));
        mData = items;
        return items;
    }

    private String getLastOnline(long lastOnlineMilliseconds) {
        Date date = new Date(lastOnlineMilliseconds*1000);
        Locale locale = new Locale("ru", "RU");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
        return FacadeCommon.makeHumanReadableDate(sdfDate.format(date));
    }
}

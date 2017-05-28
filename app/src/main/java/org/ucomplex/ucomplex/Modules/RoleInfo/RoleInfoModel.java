package org.ucomplex.ucomplex.Modules.RoleInfo;

import android.content.Context;
import android.support.v4.util.Pair;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.users.User;
import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoRaw;
import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoUtils;
import org.ucomplex.ucomplex.R;

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
                context.getString(R.string.enrolment_year) + ":",
                context.getString(R.string.study_form) + ":",
                context.getString(R.string.education_level) + ":",
                context.getString(R.string.payment_form) + ":",
                context.getString(R.string.faculty) + ":",
                context.getString(R.string.speciality) + ":",
                context.getString(R.string.group) + ":",
                context.getString(R.string.overall_attendance) + ":",
                context.getString(R.string.average_mark) + ":",
                context.getString(R.string.average_rating) + ":",
                context.getString(R.string.average_rating_in_faculty) + ":",
                context.getString(R.string.profile_is_closed)};
        List<Pair<String, String>> items = new ArrayList<>();
        String lastOnline = context.getString(R.string.never_been_online);
        if (data.getOnline() != 0) {
            lastOnline = FacadeCommon.getLastOnline(data.getOnline());
        }
        items.add(new Pair<>(FacadeCommon.getStringUserType(context, data.getType()), lastOnline));
        if (data.getClosed() != 1 && data.getType() == User.USER_TYPE_STUDENT) {
            items.add(new Pair<>(keys[0], String.valueOf(data.getYear())));
            items.add(new Pair<>(keys[1], RoleInfoUtils.getStudyForm(context, data.getStudy())));
            items.add(new Pair<>(keys[2], RoleInfoUtils.getStudyLevel(context, data.getStudy_level())));
            items.add(new Pair<>(keys[3], RoleInfoUtils.getPayment(context, data.getPayment())));
            items.add(new Pair<>(keys[4], data.getFaculty_name()));
            items.add(new Pair<>(keys[5], data.getMajor_name()));
            items.add(new Pair<>(keys[6], data.getGroup_name()));
            double marks = FacadeCommon.round((double) data.getProgress().getMark() / (double) data.getProgress().getMarksCount(), 2);
            items.add(new Pair<>(keys[7], String.valueOf(marks)));
            double attendance = FacadeCommon.round(100 - ((double) data.getProgress().getAbsence() / (double) data.getProgress().getHours()) * 100, 2);
            items.add(new Pair<>(keys[8], String.valueOf(attendance)));
            items.add(new Pair<>(keys[9], String.valueOf(data.getRating().getGeneral())));
            items.add(new Pair<>(keys[10], String.valueOf(data.getRating().getFaculty())));
        }
        if (data.getClosed() == 1)  {
            items.add(new Pair<>(keys[11], ""));
        }
        mData = items;
        return items;
    }
}

package org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.model.CalendarBeltItem;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.model.CalendarBeltRaw;

import java.text.DateFormat;
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
 * Created by Sermilion on 23/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class CalendarBeltModel implements MVPModel<CalendarBeltRaw, List<CalendarBeltItem>, Integer> {

    private List<CalendarBeltItem> mData;
    private CalendarBeltService mService;

    public CalendarBeltModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setService(CalendarBeltService service) {
        this.mService = service;
    }

    @Override
    public Observable<CalendarBeltRaw> loadData(Integer params) {
        return mService.getCalendarBelt(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<CalendarBeltItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<CalendarBeltItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<CalendarBeltItem> getData() {
        return mData;
    }

    @Override
    public List<CalendarBeltItem> processData(CalendarBeltRaw data) {
        List<CalendarBeltItem> items = new ArrayList<>();
        if (mData == null) {
            mData = new ArrayList<>();
        }
        for (int i = 0; i < data.getMarks().size(); i++) {
            CalendarBeltRaw.Mark markObject = data.getMarks().get(i);
            String disciplineName = data.getCourses().get(markObject.getCourse());
            String teacherName = data.getTeachers().get(markObject.getTeacher());
            long timeMills = (long) markObject.getTime() * 1000;
            Date date = new Date(timeMills);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
            String dateFormatted = formatter.format(date);
            String time = FacadeCommon.makeHumanReadableDate(dateFormatted, true);
            int markValue = markObject.getMark();
            int type = markObject.getType();
            CalendarBeltItem item = new CalendarBeltItem(disciplineName, teacherName, time, markValue, type);
            items.add(item);
        }
        mData.addAll(items);
        return items;
    }
}

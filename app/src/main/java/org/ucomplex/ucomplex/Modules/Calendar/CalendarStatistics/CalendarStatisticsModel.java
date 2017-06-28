package org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.model.CalendarStatisticsItem;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.model.CalendarStatisticsRaw;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 27/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class CalendarStatisticsModel implements MVPModel<CalendarStatisticsRaw, List<CalendarStatisticsItem>, Void> {

    private List<CalendarStatisticsItem> mData;
    private CalendarStatisticsService mService;


    public CalendarStatisticsModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setService(CalendarStatisticsService service) {
        this.mService = service;
    }

    @Override
    public Observable<CalendarStatisticsRaw> loadData(Void params) {
        return mService.getStatistics().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<CalendarStatisticsItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<CalendarStatisticsItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<CalendarStatisticsItem> getData() {
        return mData;
    }

    @Override
    public List<CalendarStatisticsItem> processData(CalendarStatisticsRaw data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        CalendarStatisticsItem headerOne = new CalendarStatisticsItem(0);
        mData.add(headerOne);
        int i = 0;
        CalendarStatisticsRaw.Statistics stat = data.getStatistics().get(i);
        while (data.getGroup_table() == stat.getTable()) {
            boolean isCurrent = data.getGroup_table() == stat.getTable();
            if (isCurrent) {
                createItem(data, stat, true);
            }
            i++;
            stat = data.getStatistics().get(i);
        }
        CalendarStatisticsItem headerTwo = new CalendarStatisticsItem(1);
        mData.add(headerTwo);
        for (int j = i; j < data.getStatistics().size(); j++) {
            createItem(data, stat, false);
        }
        return mData;
    }

    private void createItem(CalendarStatisticsRaw data, CalendarStatisticsRaw.Statistics stat, boolean isCurrent) {
        DecimalFormat df = new DecimalFormat("#.##",  new DecimalFormatSymbols(Locale.US));
        double mark = (double) stat.getMark()/(double) stat.getMarksCount();
        mark = Double.valueOf(df.format(mark));
        double absence = calculateAbsence(stat.getAbsence(), stat.getHours(), df);
        CalendarStatisticsItem item = new CalendarStatisticsItem(data.getCourses().get(stat.getCourse()),
                mark, absence, isCurrent);
        mData.add(item);
    }

    private double calculateAbsence(int absence, int hours, DecimalFormat df) {
        double absenceResult = ((double) absence / (double) hours) * 100;
        absenceResult = 100.0 - absenceResult;
        return Double.valueOf(df.format(absenceResult));
    }
}

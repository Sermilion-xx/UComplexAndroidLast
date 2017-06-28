package org.ucomplex.ucomplex.Modules.CalendarDay.CalendarDayPerformance;

import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.CalendarDay.CalendarDayPerformance.model.CalendarDayPerformanceItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class CalendarDayPerformanceModel implements MVPModel<List<CalendarDayPerformanceItem>, List<CalendarDayPerformanceItem>, Void> {

    private List<CalendarDayPerformanceItem> mData;

    @Override
    public Observable<List<CalendarDayPerformanceItem>> loadData(Void params) {
        return new Observable<List<CalendarDayPerformanceItem>>() {
            @Override
            protected void subscribeActual(Observer<? super List<CalendarDayPerformanceItem>> observer) {
                observer.onNext(mData);
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<CalendarDayPerformanceItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<CalendarDayPerformanceItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<CalendarDayPerformanceItem> getData() {
        return mData;
    }

    @Override
    public List<CalendarDayPerformanceItem> processData(List<CalendarDayPerformanceItem> data) {
        return mData;
    }
}

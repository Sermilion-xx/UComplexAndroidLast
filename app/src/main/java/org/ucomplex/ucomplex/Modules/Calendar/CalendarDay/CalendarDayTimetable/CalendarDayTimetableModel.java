package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable;

import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable.model.CalendarDayTimetableItem;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.CalendarPageModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class CalendarDayTimetableModel implements MVPModel<CalendarPageRaw, List<CalendarDayTimetableItem>, String> {

    private List<CalendarDayTimetableItem> mData;
    private String dayInt;

    @Override
    public Observable<CalendarPageRaw> loadData(String params) {
        this.dayInt = params;
        return new Observable<CalendarPageRaw>() {
            @Override
            protected void subscribeActual(Observer<? super CalendarPageRaw> observer) {
                observer.onNext(CalendarPageModel.getInstance().getData());
                observer.onComplete();
            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<CalendarDayTimetableItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<CalendarDayTimetableItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<CalendarDayTimetableItem> getData() {
        return mData;
    }

    @Override
    public List<CalendarDayTimetableItem> processData(CalendarPageRaw data) {
        return mData;
    }
}

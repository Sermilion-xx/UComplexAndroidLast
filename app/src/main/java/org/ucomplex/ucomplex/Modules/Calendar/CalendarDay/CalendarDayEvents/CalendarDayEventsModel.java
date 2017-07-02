package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayEvents;

import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayEvents.model.CalendarDayEventItem;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.CalendarPageModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/07/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class CalendarDayEventsModel implements MVPModel<CalendarPageRaw, List<CalendarDayEventItem>, String> {

    private List<CalendarDayEventItem> mData;
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
    public void setData(List<CalendarDayEventItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<CalendarDayEventItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<CalendarDayEventItem> getData() {
        return mData;
    }

    @Override
    public List<CalendarDayEventItem> processData(CalendarPageRaw data) {
        mData = new ArrayList<>();
        List<CalendarPageRaw.Event> events =  data.getEvents().get(dayInt);
        if (events != null) {
            for (CalendarPageRaw.Event event : events) {
                CalendarDayEventItem item = new CalendarDayEventItem(event.getName(),
                        event.getDescr(),
                        event.getStart(),
                        event.getTill(),
                        event.getHoliday());
                mData.add(item);
            }
        }
        return mData;
    }
}

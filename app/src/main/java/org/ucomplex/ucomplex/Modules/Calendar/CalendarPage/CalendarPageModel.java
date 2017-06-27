package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage;


import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageParams;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class CalendarPageModel implements MVPModel<CalendarPageRaw, CalendarPageRaw, CalendarPageParams> {

    private CalendarPageRaw mData;
    private CalendarPageService mService;

    public CalendarPageModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setService(CalendarPageService service) {
        this.mService = service;
    }

    @Override
    public Observable<CalendarPageRaw> loadData(CalendarPageParams params) {
        return mService.getCalendar(params.getMonth(), params.getTime()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(CalendarPageRaw data) {
        this.mData = data;
    }

    @Override
    public void addData(CalendarPageRaw data) {
        throw new UnsupportedOperationException("Operation not suported");
    }

    @Override
    public void clear() {
        mData = null;
    }

    @Override
    public CalendarPageRaw getData() {
        return mData;
    }

    @Override
    public CalendarPageRaw processData(CalendarPageRaw data) {
        mData = data;
        return mData;
    }
}

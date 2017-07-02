package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayEvents;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayEvents.model.CalendarDayEventItem;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CalendarDayEventsPresenter extends AbstractPresenter<
        CalendarPageRaw, List<CalendarDayEventItem>,
        String, CalendarDayEventsModel> {

    public CalendarDayEventsPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(String params) {
        Observable<CalendarPageRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<CalendarPageRaw>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(CalendarPageRaw value) {
                mModel.processData(value);
                if (getView() != null) {
                    getView().dataLoaded();
                }
            }

            @Override
            public void onError(Throwable e) {
                hideProgress();
            }

            @Override
            public void onComplete() {
                hideProgress();
            }
        });
    }
}
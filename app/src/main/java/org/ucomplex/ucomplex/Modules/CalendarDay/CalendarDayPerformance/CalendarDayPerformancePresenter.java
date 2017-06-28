package org.ucomplex.ucomplex.Modules.CalendarDay.CalendarDayPerformance;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Modules.CalendarDay.CalendarDayPerformance.model.CalendarDayPerformanceItem;

import org.ucomplex.ucomplex.Common.base.UCApplication;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CalendarDayPerformancePresenter extends AbstractPresenter<
        List<CalendarDayPerformanceItem>, List<CalendarDayPerformanceItem>,
        Void, CalendarDayPerformanceModel> {

    public CalendarDayPerformancePresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(Void params) {
        Observable<List<CalendarDayPerformanceItem>> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<List<CalendarDayPerformanceItem>>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(List<CalendarDayPerformanceItem> value) {
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
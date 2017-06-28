package org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.model.CalendarStatisticsItem;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.model.CalendarStatisticsRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CalendarStatisticsPresenter extends AbstractPresenter<
        CalendarStatisticsRaw, List<CalendarStatisticsItem>,
        Void, CalendarStatisticsModel> {

    public CalendarStatisticsPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(Void params) {
        Observable<CalendarStatisticsRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<CalendarStatisticsRaw>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(CalendarStatisticsRaw value) {
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
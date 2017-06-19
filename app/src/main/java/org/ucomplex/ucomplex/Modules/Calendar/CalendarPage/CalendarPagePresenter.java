package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageItem;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CalendarPagePresenter extends AbstractPresenter<
        CalendarPageRaw, CalendarPageRaw,
        Void, CalendarPageModel> {

    public CalendarPagePresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(Void params) {
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
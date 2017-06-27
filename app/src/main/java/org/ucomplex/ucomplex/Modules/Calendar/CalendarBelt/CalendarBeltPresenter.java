package org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.DownloadCallback;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.model.CalendarBeltItem;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.model.CalendarBeltRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CalendarBeltPresenter extends AbstractPresenter<
        CalendarBeltRaw, List<CalendarBeltItem>,
        Integer, CalendarBeltModel> {

    private DownloadCallback<List<CalendarBeltItem>> mDownloadCallback;

    public void setDownloadCallback(DownloadCallback<List<CalendarBeltItem>> mDownloadCallback) {
        this.mDownloadCallback = mDownloadCallback;
    }

    public CalendarBeltPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(Integer params) {
        Observable<CalendarBeltRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<CalendarBeltRaw>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(CalendarBeltRaw value) {
                mDownloadCallback.onResponse(mModel.processData(value));
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
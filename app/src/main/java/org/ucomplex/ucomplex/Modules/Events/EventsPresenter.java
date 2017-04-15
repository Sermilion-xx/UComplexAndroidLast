package org.ucomplex.ucomplex.Modules.Events;

import android.database.sqlite.SQLiteDatabase;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.interfaces.DownloadCallback;
import org.ucomplex.ucomplex.Modules.Events.model.EventItem;
import org.ucomplex.ucomplex.Modules.Events.model.EventsRaw;
import org.ucomplex.ucomplex.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class EventsPresenter extends AbstractPresenter<
        EventsRaw, List<EventItem>,
        Integer, EventsModel> {

    private DownloadCallback<List<EventItem>> downloadCallback;

    public EventsPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    public void setDownloadCallback(DownloadCallback<List<EventItem>> downloadCallback) {
        this.downloadCallback = downloadCallback;
    }

    @Override
    public void loadData(Integer start) {
        SQLiteDatabase db = EventsDBOpenHelper.getConnection(getActivityContext());
        if (!UCApplication.getInstance().isConnectedToInternet()) {
            if (getView() != null) {
                getView().showToast(R.string.offline_mode);
            }
            showProgress();
            List<EventItem> items = mModel.getEvents(getActivityContext());
            downloadCallback.onResponse(items);
            hideProgress();
        } else {
            Observable<EventsRaw> dataObservable = mModel.loadData(start);
            dataObservable.subscribe(new Observer<EventsRaw>() {
                @Override
                public void onSubscribe(Disposable d) {
                    showProgress();
                }

                @Override
                public void onNext(EventsRaw value) {
                    List<EventItem> items = mModel.processData(value);
                    if (!UCApplication.getInstance().isEventsCached()) {
                        mModel.saveEvents(mModel.getData(), getActivityContext());
                        UCApplication.getInstance().setEventsCached(true);
                    }
                    downloadCallback.onResponse(items);
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
}

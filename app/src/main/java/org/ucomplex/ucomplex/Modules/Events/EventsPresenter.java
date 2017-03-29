package org.ucomplex.ucomplex.Modules.Events;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.RecyclerFragment;
import org.ucomplex.ucomplex.Common.UCApplication;

import java.util.List;

import javax.inject.Inject;

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
        Events, List<EventItem>,
        EventsParams, EventsModel> {

    @Inject
    public void setModel(EventsModel model) {
        mModel = model;
    }

    public EventsPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(EventsParams mRequestParams) {
        Observable<Events> dataObservable = mModel.loadData(mRequestParams);
        dataObservable.subscribe(new Observer<Events>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(Events value) {
                mModel.processData(value);
                if(getView()!=null){
                    ((EventsActivity)getView()).updateEvents(getData());
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

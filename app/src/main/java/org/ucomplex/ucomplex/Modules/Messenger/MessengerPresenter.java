package org.ucomplex.ucomplex.Modules.Messenger;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerItem;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MessengerPresenter extends AbstractPresenter<
        MessengerRaw, List<MessengerItem>,
        Integer, MessengerModel> {

    public MessengerPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(Integer params) {
        Observable<MessengerRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<MessengerRaw>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(MessengerRaw value) {
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
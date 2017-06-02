package org.ucomplex.ucomplex.Modules.MessagesList;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.MessagesList.model.MessagesListItem;
import org.ucomplex.ucomplex.Modules.MessagesList.model.MessagesListRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MessagesListPresenter extends AbstractPresenter<
        MessagesListRaw, List<MessagesListItem>,
        Void, MessagesListModel> {

    public MessagesListPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(Void params) {
        Observable<MessagesListRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<MessagesListRaw>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(MessagesListRaw value) {
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
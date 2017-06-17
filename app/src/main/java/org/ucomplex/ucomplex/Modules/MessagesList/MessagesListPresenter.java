package org.ucomplex.ucomplex.Modules.MessagesList;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import org.ucomplex.ucomplex.Common.NewMessageBroadcastReceiver;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.MessagesList.model.MessagesListItem;
import org.ucomplex.ucomplex.Modules.MessagesList.model.MessagesListRaw;
import org.ucomplex.ucomplex.Modules.Messenger.MessengerActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.ucomplex.ucomplex.Common.Constants.UC_ACTION_NEW_MESSAGE;
import static org.ucomplex.ucomplex.Modules.Updates.UpdatesService.MESSAGE_COUNT;

public class MessagesListPresenter extends AbstractPresenter<
        MessagesListRaw, List<MessagesListItem>,
        Void, MessagesListModel> {

    private NewMessageBroadcastReceiver receiver = new NewMessageBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(UC_ACTION_NEW_MESSAGE)) {
                int count = intent.getIntExtra(MESSAGE_COUNT, 0);
                if (count > NewMessageBroadcastReceiver.getMessageCount()) {
                    loadData(null);
                }
            }
        }
    };

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

    void onStart() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UC_ACTION_NEW_MESSAGE);
        getActivityContext().registerReceiver(receiver, filter);
    }

    void onStop() {
        getActivityContext().unregisterReceiver(receiver);
    }

}
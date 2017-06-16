package org.ucomplex.ucomplex.Modules.Updates;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.R;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static org.ucomplex.ucomplex.Common.Constants.UC_ACTION_NEW_MESSAGE;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 16/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UpdatesService extends Service {

    public static final String MESSAGE_COUNT = "message_count";
    private UpdatesRetrofitService mService;

    @Inject
    public void setService(UpdatesRetrofitService mService) {
        this.mService = mService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Handler h = new Handler();
        int delay = 5000; //milliseconds

        h.postDelayed(new Runnable() {
            public void run() {
                checkUpdates();
                h.postDelayed(this, delay);
            }
        }, delay);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void checkUpdates() {
        mService.getUpdates()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdatesRaw>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdatesRaw value) {
                        if (value.getMessages().getSum() > 0) {
                            Intent intent = new Intent();
                            intent.setAction(UC_ACTION_NEW_MESSAGE);
                            intent.putExtra(MESSAGE_COUNT, value.getMessages().getSum());
                            sendBroadcast(intent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

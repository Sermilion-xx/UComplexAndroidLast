package org.ucomplex.ucomplex.Modules.Messenger.FullscreenView;

import android.graphics.Bitmap;
import android.net.Uri;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FullscreenViewPresenter extends AbstractPresenter<
        Bitmap, Bitmap,
        Uri, FullscreenViewModel> {

    public FullscreenViewPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(Uri params) {
        Observable<Bitmap> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<Bitmap>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(Bitmap value) {
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
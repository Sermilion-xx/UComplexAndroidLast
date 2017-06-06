package org.ucomplex.ucomplex.Modules.Messenger.FullscreenView;

import android.graphics.Bitmap;
import android.net.Uri;

import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 06/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class FullscreenViewModel implements MVPModel<Bitmap, Bitmap, Uri> {

    private Bitmap mData;

    @Override
    public Observable<Bitmap> loadData(Uri params) {
        return new Observable<Bitmap>() {
            @Override
            protected void subscribeActual(Observer<? super Bitmap> observer) {
                try {
                    observer.onNext(FacadeMedia.getBitmapFromStorage(params, UCApplication.getInstance()));
                } catch (IOException e) {
                    observer.onError(e);
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public void setData(Bitmap data) {
        this.mData = data;
    }

    @Override
    public void addData(Bitmap data) {
        this.mData = data;
    }

    @Override
    public void clear() {
        mData = null;
    }

    @Override
    public Bitmap getData() {
        return mData;
    }

    @Override
    public Bitmap processData(Bitmap bitmap) {
        mData = bitmap;
        return mData;
    }
}

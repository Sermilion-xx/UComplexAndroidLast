package org.ucomplex.ucomplex.Modules.Messenger;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.download.DownloadService;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerItem;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerRaw;
import org.ucomplex.ucomplex.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.ucomplex.ucomplex.Common.base.UCApplication.MESSAGE_FILES_URL;

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

    void downloadFile(String name, int id, String address) {
        checkStoragePermissions();
        String url = MESSAGE_FILES_URL + id + "/"  + address;
        if (getView() != null) {
            getView().showToast(R.string.download_started);
        }
        getActivityContext().startService(DownloadService.createIntent(getAppContext(), url, name));
    }

    private void checkStoragePermissions() {
        if (ContextCompat.checkSelfPermission(getActivityContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (getView() != null) {
                getView().showToast(R.string.need_storage_permissions, Toast.LENGTH_LONG);
            }
        }
    }

    public void sendMessage(String message, List<Uri> filesToSend) {

    }
}
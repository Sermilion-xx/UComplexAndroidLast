package org.ucomplex.ucomplex.Modules.Messenger;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.download.DownloadService;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessageFile;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerItem;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerRaw;
import org.ucomplex.ucomplex.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static org.ucomplex.ucomplex.Common.base.UCApplication.BASE_FILES_URL;
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
        String url = BASE_FILES_URL + MESSAGE_FILES_URL + id + "/" + address;
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

    void sendMessage(String message, int companion, List<Uri> fileUris, Context context) {
        int myId = UCApplication.getInstance().getLoggedUser().getId();
        MessengerItem item = MessengerItem.createTempMessage(
                myId,
                message,
                context.getString(R.string.sending));

        for (int i = 0; i < fileUris.size(); i++) {
            MessageFile file = new MessageFile(
                    FacadeMedia.getFileNameFromUri(fileUris.get(i), getActivityContext()),
                    fileUris.get(i),
                    myId);
            item.getFiles().add(file);
        }
        getData().add(0, item);
        List<MultipartBody.Part> multiParts = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        for (int i = 0; i < fileUris.size(); i++) {
            Uri uri = fileUris.get(i);
            multiParts.add(createMultipart(uri, contentResolver));
        }
        RequestBody description = createPartFromString("hello, this is description speaking");
        Observable<MessengerRaw> observable = mModel.sendMessage(message, companion, description, multiParts);
        observable.subscribe(new Observer<MessengerRaw>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(MessengerRaw value) {
                getData().remove(0);
                mModel.addData(value.getMessages());
                if (getView() != null) {
                    ((MessengerActivity) getView()).resetMessegeView();
                    ((MessengerActivity) getView()).updateMessageList();
                }
            }

            @Override
            public void onError(Throwable e) {
                getData().remove(0);
                if (getView() != null) {
                    ((MessengerActivity) getView()).updateMessageList();
                }
                hideProgress();
            }

            @Override
            public void onComplete() {
                hideProgress();
            }
        });
    }

    @NonNull
    private MultipartBody.Part createMultipart(Uri fileUri, ContentResolver contentResolver) {
        File file = new File(fileUri.toString());
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData("file", file.getName(), requestFile);
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

}
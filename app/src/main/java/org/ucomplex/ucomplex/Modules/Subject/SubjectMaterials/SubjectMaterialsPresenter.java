package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.interfaces.DownloadCallback;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.MaterialsRaw;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectItemFile;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectMaterialsParams;
import org.ucomplex.ucomplex.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static org.ucomplex.ucomplex.Common.Constants.UC_ACTION_DOWNLOAD_COMPLETE;
import static org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.NotificationService.EXTRA_BODY;
import static org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.NotificationService.EXTRA_LARGE_ICON;
import static org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.NotificationService.EXTRA_TITLE;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 31/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectMaterialsPresenter extends AbstractPresenter<
        MaterialsRaw, List<Pair<List<SubjectItemFile>, String>>, SubjectMaterialsParams, SubjectMaterialsModel> {

    private DownloadTask saveFileTask;

    public SubjectMaterialsPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setModel(SubjectMaterialsModel model) {
        mModel = model;
    }

    void setMaterialsItems(List<SubjectItemFile> items) {
        mModel.addHistory(new Pair<>(items, ""));
        pageUp();
    }

    private void pageUp() {
        mModel.pageUp();
    }

    void pageDown() {
        mModel.pageDown();
        Pair<List<SubjectItemFile>, String> history = getHistory(mModel.getCurrentPage());
        if (getView() != null) {
            ((SubjectMaterialsFragment) getView()).getAdapter().setItems(history.first);
        }
    }

    public int getCurrentPage() {
        return mModel.getCurrentPage();
    }

    private void addHistory(Pair<List<SubjectItemFile>, String> list) {
        mModel.addHistory(list);
    }

    private Pair<List<SubjectItemFile>, String> getHistory(int index) {
        return mModel.getHistory(index);
    }

    public List<SubjectItemFile> getCurrentHistory() {
        Pair<List<SubjectItemFile>, String> pair = mModel.getHistory(mModel.getCurrentPage());
        if (pair != null) {
            return pair.first;
        }
        return null;
    }

    @Override
    public void loadData(SubjectMaterialsParams params) {
        if (params.getFileName() == null) {
            if (mModel.getHistoryCount() > 1 && !params.isMyFolder()) {
                if (getView() != null) {
                    pageUp();
                    getView().dataLoaded();
                }
            } else {
                Observable<MaterialsRaw> dataObservable = mModel.loadData(params);
                dataObservable.subscribe(new Observer<MaterialsRaw>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showProgress();
                    }

                    @Override
                    public void onNext(MaterialsRaw value) {
                        mModel.processData(value);
                        if (getView() != null) {
                            mModel.setCurrentFolder(params.getFolderName());
                            pageUp();
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
        } else {
            if (ContextCompat.checkSelfPermission(getActivityContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (getView() != null) {
                    getView().showToast(R.string.need_storage_permissions, Toast.LENGTH_LONG);
                }
            }
            startNotificationService(params.getFileName(), R.string.file_download_started, null, getActivityContext());

            mModel.downloadFile(params, new DownloadCallback<Response<ResponseBody>>() {
                @Override
                public void onResponse(Response<ResponseBody> response) {
                    saveFileTask = new DownloadTask(response, getActivityContext());
                    saveFileTask.execute(params);
                }

                @Override
                public void onError(Throwable t) {
                    t.printStackTrace();
                    if (getView() != null) {
                        getView().showToast(R.string.error_loadig_file, Toast.LENGTH_LONG);
                    }
                }
            });
//            Observable<ResponseBody> dataObservable = mModel.downloadFile(params);
//            dataObservable.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<ResponseBody>() {
//
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                        }
//
//                        @Override
//                        public void onNext(ResponseBody value) {
//                            try {
//                                String name = params.getFileName();
//                                MaterialsFile file = new MaterialsFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);
//                                FileOutputStream out = new FileOutputStream(file.getPath());
//                                long lenght = value.contentLength();
//                                out.write(value.bytes());
//                                out.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            e.printStackTrace();
//                            if (getView() != null) {
//                                getView().showToast(R.string.error_loadig_file, Toast.LENGTH_LONG);
//                            }
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            Intent intent = new Intent();
//                            intent.setAction(UC_ACTION_DOWNLOAD_COMPLETE);
//                            getActivityContext().sendBroadcast(intent);
//                        }
//                    });
        }
    }

    private void showRenameDialog(String file, String oldName, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivityContext());
        View promptView = layoutInflater.inflate(R.layout.dialog_input, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivityContext());
        alertDialogBuilder.setView(promptView);
        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        editText.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.color_uc_ListText));
        editText.setText(oldName);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.Done, (dialog, id) -> {
                    if (FacadeCommon.isNetworkConnected(getActivityContext())) {
                        String newName = editText.getText().toString();
                        if (!newName.equals("")) {
                            mModel.renameFile(file, newName, getCurrentHistory().get(position).getName());
                        } else {
                            if (getView() != null) {
                                getView().showToast(R.string.name_cant_be_empty, Toast.LENGTH_LONG);
                            }
                        }
                    } else {
                        if (getView() != null) {
                            getView().showToast(R.string.check_internet, Toast.LENGTH_LONG);
                        }
                    }
                }).setNegativeButton(R.string.cancel,
                (dialog, id) -> dialog.cancel());
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private AlertDialog.Builder createItemMenu(String code, String name, int position, String[] menuItems) {
        AlertDialog.Builder build = new AlertDialog.Builder(getActivityContext());
        build.setItems(menuItems, (dialog, which) -> {
            switch (which) {
                case 0:
                    showRenameDialog(code, name, position);
                    break;
                case 1:
                    mModel.deleteFile(code);
                    break;
                case 2:
                    mModel.shareFile(code);
                    break;
            }
        });
        return build;
    }


    public void uploadFile(Uri uri) {
        Intent intent = new Intent();
        intent.setAction(UC_ACTION_DOWNLOAD_COMPLETE);
        File file = new File(uri.getPath());
        startNotificationService(file.getName(), R.string.upload_started, uri, getActivityContext());
        mModel.uploadFile(uri);
    }

    public void createFolder(String folderName) {
        mModel.createFolder(folderName);
    }


    private void startNotificationService(String filename, int messageRes, Uri largeIcon, Context context) {
        String message = context.getString(messageRes);
        Intent notificationIntent = new Intent(context, NotificationService.class);
        notificationIntent.putExtra(EXTRA_TITLE, filename);
        notificationIntent.putExtra(EXTRA_BODY, message);
        if (largeIcon != null) {
            notificationIntent.putExtra(EXTRA_LARGE_ICON, largeIcon);
        }
        context.startService(notificationIntent);
    }

    private static class DownloadTask extends AsyncTask<SubjectMaterialsParams, Void, Void> {

        private Response<ResponseBody> response;
        private Context context;

        public DownloadTask(Response<ResponseBody> responseBodyResponse, Context context) {
            this.response = responseBodyResponse;
            this.context = context;
        }

        @Override
        protected Void doInBackground(SubjectMaterialsParams... params) {
            try {
                String name = params[0].getFileName();
                File materialsFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);
                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    byte[] fileReader = new byte[4096];
                    long fileSize = response.body().contentLength();
                    long fileSizeDownloaded = 0;
                    inputStream = response.body().byteStream();
                    outputStream = new FileOutputStream(materialsFile);
                    while (true) {
                        int read = inputStream.read(fileReader);
                        if (read == -1) {
                            break;
                        }
                        outputStream.write(fileReader, 0, read);
                        fileSizeDownloaded += read;
                    }
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent();
            intent.setAction(UC_ACTION_DOWNLOAD_COMPLETE);
            context.sendBroadcast(intent);
            Toast.makeText(context, R.string.file_download_complete, Toast.LENGTH_LONG).show();
        }
    }


}

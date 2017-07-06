package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.download.DownloadService;
import org.ucomplex.ucomplex.Modules.Portfolio.PortfolioActivity;
import org.ucomplex.ucomplex.Modules.Portfolio.model.RequestResult;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.MaterialsRaw;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectItemFile;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectMaterialsParams;
import org.ucomplex.ucomplex.R;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.ucomplex.ucomplex.Common.Constants.TYPE_FOLDER;
import static org.ucomplex.ucomplex.Common.Constants.UC_ACTION_DOWNLOAD_COMPLETE;
import static org.ucomplex.ucomplex.Common.base.UCApplication.BASE_FILES_URL;
import static org.ucomplex.ucomplex.Common.base.UCApplication.FILES_PATH;

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
        MaterialsRaw, List<Pair<List<SubjectItemFile>, SubjectMaterialsModel.CurrentFolderNameAndCode>>, SubjectMaterialsParams, SubjectMaterialsModel> {


    private String[] folderMenuActions;
    private String[] fileMenuActions;
    public SubjectMaterialsPresenter() {

        UCApplication application = UCApplication.getInstance();
        application.getAppDiComponent().inject(this);
        folderMenuActions = new String[]{application.getString(R.string.rename), application.getString(R.string.delete)};
        fileMenuActions = new String[]{application.getString(R.string.rename), application.getString(R.string.delete)};
    }

    @Inject
    public void setModel(SubjectMaterialsModel model) {
        mModel = model;
    }

    void setMaterialsItems(List<SubjectItemFile> items) {
        mModel.addHistory(new Pair<>(items, new SubjectMaterialsModel.CurrentFolderNameAndCode()));
        pageUp();
    }

    private void pageUp() {
        mModel.pageUp();
    }

    public void pageDown() {
        mModel.pageDown();
        Pair<List<SubjectItemFile>, SubjectMaterialsModel.CurrentFolderNameAndCode> history = getHistory(mModel.getCurrentPage());
        if (getView() != null) {
            if (getView() instanceof PortfolioActivity) {
                ((PortfolioActivity) getView()).getAdapter().setItems(history.first);
            } else {
                ((SubjectMaterialsFragment) getView()).getAdapter().setItems(history.first);
            }
        }
    }

    public int getCurrentPage() {
        return mModel.getCurrentPage();
    }

    private Pair<List<SubjectItemFile>, SubjectMaterialsModel.CurrentFolderNameAndCode> getHistory(int index) {
        return mModel.getHistory(index);
    }

    public String getCurrentFolderCode() {
        return mModel.getCurrentFolderCode();
    }

    public List<SubjectItemFile> getCurrentHistory() {
        Pair<List<SubjectItemFile>, SubjectMaterialsModel.CurrentFolderNameAndCode> pair = mModel.getHistory(mModel.getCurrentPage());
        if (pair != null) {
            return pair.first;
        }
        return null;
    }

    @Override
    public void loadData(SubjectMaterialsParams params) {
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
                        mModel.setCurrentFolder(params.getFileName());
                        mModel.setCurrentFolderCode(params.getFileAddress());
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
    }

    public void downloadFile(int ownerId, String fileName) {
        String url = BASE_FILES_URL + FILES_PATH + ownerId + "/" + fileName;
        if (ContextCompat.checkSelfPermission(getActivityContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (getView() != null) {
                getView().showToast(R.string.need_storage_permissions, Toast.LENGTH_LONG);
            }
        }
        getView().showToast(R.string.download_started);
        getActivityContext().startService(DownloadService.createIntent(getAppContext(), url, fileName));
    }

    private void showRenameDialog(SubjectItemFile file, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivityContext());
        View promptView = layoutInflater.inflate(R.layout.dialog_input, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivityContext());
        alertDialogBuilder.setView(promptView);
        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        editText.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.color_uc_ListText));
        editText.setText(file.getName());
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(R.string.Done, (dialog, id) -> {
                    sendRenameRequest(file, position, editText.getText().toString());
                }).setNegativeButton(R.string.cancel,
                (dialog, id) -> dialog.cancel());
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void sendRenameRequest(final SubjectItemFile file, final int position, String newName) {
        if (UCApplication.getInstance().isConnectedToInternet()) {
            if (!newName.equals("")) {
                Observable<RequestResult> renameObservable = mModel.renameFile(file.getAddress(), newName);
                renameObservable.subscribe(new Observer<RequestResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showProgress();
                    }

                    @Override
                    public void onNext(RequestResult value) {
                        if (value.isGeneral()) {
                            file.setName(newName);
                            if (getView() != null) {
                                ((PortfolioActivity) getView()).notifyItemChanged(position);
                            }
                        } else {
                            onError(new Throwable(getActivityContext().getString(R.string.error_renaming_file)));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgress();
                        if (getView() != null) {
                            getView().showToast(R.string.error_renaming_file, Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onComplete() {
                        hideProgress();
                    }
                });
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
    }

    public void uploadFile(Uri uri) {
        Intent intent = new Intent();
        intent.setAction(UC_ACTION_DOWNLOAD_COMPLETE);
        File file = new File(uri.getPath());
        FacadeCommon.startNotificationService(file.getName(), R.string.upload_started, uri, getActivityContext());
        Observable<MaterialsRaw> observable = mModel.uploadFile(uri);
        if (observable != null) {
            observable.subscribe(new Observer<MaterialsRaw>() {
                @Override
                public void onSubscribe(Disposable d) {
                    if (getView() != null) {
                        getView().showToast(R.string.upload_started);
                    }
                }

                @Override
                public void onNext(MaterialsRaw value) {
                    mModel.processDataToCurrentHistory(value);
                    if (getView() != null) {
                        ((PortfolioActivity) getView()).notifyItemInserted(getCurrentHistory().size());
                    }
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    NotificationService.sendDownloadCompleteBroadcast(getActivityContext());
                    if (getView() != null) {
                        getView().showToast(R.string.error_file_upload);
                    }
                }

                @Override
                public void onComplete() {
                    NotificationService.sendDownloadCompleteBroadcast(getActivityContext());
                    if (getView() != null) {
                        getView().showToast(R.string.upload_finished);
                    }
                }
            });
        } else {
            if (getView() != null) {
                getView().showToast(R.string.error_file_not_found);
            }
        }

    }

    public void createFolder(String folderName, String folder) {
        Observable<MaterialsRaw> deleteObservable = mModel.createFolder(folderName, folder);
        deleteObservable.subscribe(new Observer<MaterialsRaw>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(MaterialsRaw value) {
                mModel.processDataToCurrentHistory(value);
                if (getView() != null) {
                    ((PortfolioActivity) getView()).notifyItemInserted(getCurrentHistory().size());
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


    private void deleteFile(SubjectItemFile params, int position) {
        Observable<RequestResult> deleteObservable = mModel.deleteFile(params.getAddress());
        deleteObservable.subscribe(new Observer<RequestResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(RequestResult value) {
                getCurrentHistory().remove(params);
                if (value.isGeneral()) {
                    if (getView() != null) {
                        ((PortfolioActivity) getView()).notifyItemRemoved(position);
                    }
                } else {
                    onError(new Throwable(getActivityContext().getString(R.string.error_deleting_file)));
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                hideProgress();
                if (getView() != null) {
                    getView().showToast(R.string.error_deleting_file, Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onComplete() {
                hideProgress();
            }
        });
    }

    public AlertDialog.Builder createItemMenu(SubjectMaterialsParams params) {
        AlertDialog.Builder build = new AlertDialog.Builder(getActivityContext());
        String[] menuItems = params.getFile().getType().equals(TYPE_FOLDER) ? folderMenuActions : fileMenuActions;
        build.setItems(menuItems, (dialog, which) -> {
            switch (which) {
                case 0:
                    showRenameDialog(params.getFile(), params.getPosition());
                    break;
                case 1:
                    deleteFile(params.getFile(), params.getPosition());
                    break;
                case 2:
                    mModel.shareFile(params.getFileAddress());
                    break;
            }
        });
        return build;
    }


}

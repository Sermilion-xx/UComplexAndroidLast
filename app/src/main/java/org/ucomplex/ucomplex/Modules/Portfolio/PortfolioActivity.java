package org.ucomplex.ucomplex.Modules.Portfolio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.PresenterCache;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.SubjectMaterialsAdapter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.SubjectMaterialsPresenter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.FileOperationType;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectMaterialsParams;
import org.ucomplex.ucomplex.R;

import javax.inject.Inject;

public class PortfolioActivity extends BaseMVPActivity<MVPView, SubjectMaterialsPresenter> {

    private static final int GALLERY_KITKAT_INTENT_CALLED = 0;
    private static final int GALLERY_INTENT_CALLED = 1;
    private static final String TAG =  PortfolioActivity.class.getName();
    private static final String DESTROYED_BY_SYSTEM = "DESTROYED_BY_SYSTEM";


    public static Intent creteIntent(Context context) {
        return new Intent(context, PortfolioActivity.class);
    }

    private SubjectMaterialsAdapter mAdapter;
    private PresenterCache presenterCache = PresenterCache.getInstance();
    private boolean isDestroyedBySystem;

    @Inject
    @Override
    public void setPresenter(@NonNull SubjectMaterialsPresenter presenter) {
        super.setPresenter(presenter);
        presenterCache.addToChache(TAG, presenter);
    }

    @Inject
    public void setAdapter(SubjectMaterialsAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @NonNull
    @Override
    public SubjectMaterialsPresenter createPresenter() {
        if (isDestroyedBySystem) {
            presenter =  (SubjectMaterialsPresenter) presenterCache.getFromChache(TAG);
        }
        return presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isDestroyedBySystem = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            isDestroyedBySystem = true;
        } else {
            UCApplication.getInstance().getAppDiComponent().inject(this);
        }
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_portfolio);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupToolbar(getString(R.string.portfolio), R.drawable.ic_menu);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setupDrawer();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter.setMyFiles(true);
        mAdapter.setOnListItemClicked(this::performFileOperation);
        mRecyclerView.setAdapter(mAdapter);
        if (presenter.getCurrentHistory() == null || presenter.getCurrentHistory().size() == 0) {
            SubjectMaterialsParams params = new SubjectMaterialsParams(FileOperationType.DOWNLOAD);
            params.setMyFolder(true);
            presenter.loadData(params);
        } else {
            dataLoaded();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.my_files_add_file:
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (FacadeCommon.checkStoragePermissions(this)) {
                        pickImage();
                    }
                } else {
                    pickImage();
                }
                return true;
            case R.id.my_files_add_folder:
                showCreateFolderDialog();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    protected void showCreateFolderDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.dialog_input, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);
        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        editText.setTextColor(ContextCompat.getColor(this, R.color.color_uc_ListTextColorPrimary));
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(PortfolioActivity.this.getString(R.string.ok), (dialog, id) -> {
                    if (FacadeCommon.isNetworkConnected(PortfolioActivity.this)) {
                        String folderName = editText.getText().toString();
                        presenter.createFolder(folderName);
                    } else {
                        showToast(R.string.error_check_internet, Toast.LENGTH_LONG);
                    }
                })
                .setNegativeButton(PortfolioActivity.this.getString(R.string.cancel),
                        (dialog, id) -> dialog.cancel());
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void pickImage() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.pick_document)), GALLERY_INTENT_CALLED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_materials, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (null == data) return;
        Uri originalUri = null;
        if (requestCode == GALLERY_INTENT_CALLED) {
            originalUri = data.getData();
        } else if (requestCode == GALLERY_KITKAT_INTENT_CALLED) {
            originalUri = data.getData();
            grantUriPermission(FacadeMedia.PACKAGE, originalUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

        if (originalUri != null) {
            if (FacadeCommon.isNetworkConnected(this)) {
                presenter.uploadFile(originalUri);
            } else {
                showToast(R.string.check_internet, Toast.LENGTH_LONG);
            }
        } else {
            showToast(R.string.error_while_choosing_file, Toast.LENGTH_LONG);
        }
        this.revokeUriPermission(originalUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
    }

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getCurrentHistory());
        mAdapter.notifyDataSetChanged();
    }

    public void notifyItemChanged(int position) {
        mAdapter.notifyItemChanged(position);
    }

    public void notifyItemRemoved(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    @Override public void onResume() {
        super.onResume();
        isDestroyedBySystem = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter = null;
        if (!isDestroyedBySystem) {
            presenterCache.removePresenter(TAG);
        }
    }

    public void performFileOperation(SubjectMaterialsParams params) {
        if (params.getOperationType() == FileOperationType.CREATE_FOLDER) {

        } else if (params.getOperationType() == FileOperationType.MENU) {
            presenter.createItemMenu(params).show();
        }  else if (params.getOperationType() == FileOperationType.DOWNLOAD) {
            presenter.loadData(params);
        }
    }
}

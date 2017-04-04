package org.ucomplex.ucomplex.Modules.Events;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Events.model.EventItem;
import org.ucomplex.ucomplex.R;

import java.util.List;

import javax.inject.Inject;

import static org.ucomplex.ucomplex.Common.FacadeCommon.REQUEST_EXTERNAL_STORAGE;

public class EventsActivity extends BaseMVPActivity<MVPView, EventsPresenter> implements MVPView {

    private static final String EXTRA_REFRESH = "REFRESH";

    public static Intent creteIntent (Context context) {
        Intent intent = new Intent(context, EventsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public static Intent creteRefreshIntent (Context context) {
        Intent intent = new Intent(context, EventsActivity.class);
        intent.putExtra(EXTRA_REFRESH, true);
        return intent;
    }

    private RecyclerView  mRecyclerView;
    private EventsAdapter mAdapter;

    @Inject
    @Override
    public void setPresenter(@NonNull EventsPresenter presenter) {
        super.setPresenter(presenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_events);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupToolbar(getString(R.string.events), R.drawable.ic_menu);
        setupDrawer();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventsAdapter(params -> presenter.loadData(params));
        mRecyclerView.setAdapter(mAdapter);
        if (presenter.getData() == null || getIntent().getBooleanExtra(EXTRA_REFRESH, false)) {
            presenter.loadData(0);
        } else {
            updateEvents(presenter.getData());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FacadeCommon.checkStoragePermissions(this);
        }
    }

    @NonNull
    @Override
    public EventsPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    showToast(R.string.storage_permission_declined, Toast.LENGTH_LONG);
                }
            }
        }
    }

    public void updateEvents(List<EventItem> items){
        mAdapter.updateAdapterItems(items);
    }

}

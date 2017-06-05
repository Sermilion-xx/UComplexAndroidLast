package org.ucomplex.ucomplex.Modules.Messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessageFileType;
import org.ucomplex.ucomplex.R;

public class MessengerActivity extends BaseMVPActivity<MVPView, MessengerPresenter> {

    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_COMPANION = "EXTRA_COMPANION";

    public static Intent creteIntent(Context context, String name, int  companion) {
        Intent intent = new Intent(context, MessengerActivity.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_COMPANION, companion);
        return intent;
    }

    private MessengerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentViewWithNavDrawer(R.layout.activity_messenger);
        setupToolbar(intent.getStringExtra(EXTRA_NAME),R.drawable.ic_menu);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupDrawer();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MessengerAdapter(UCApplication.getInstance().getLoggedUser().getId(), (params, type) -> {

        });
        mRecyclerView.setAdapter(mAdapter);
        if (presenter.getData() == null) {
            presenter.loadData(intent.getIntExtra(EXTRA_COMPANION, -1));
        } else {
            dataLoaded();
        }
    }

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getData());
        mAdapter.notifyDataSetChanged();
    }
}
package org.ucomplex.ucomplex.Modules.UserProfile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.R;

public class UserProfileActivity extends BaseMVPActivity<MVPView, UserProfilePresenter> {

    private static final String USER_ID = "USER_ID";

    public static Intent creteIntent (Context context, int id) {
        Intent intent = new Intent(context, UserProfileActivity.class);
        intent.putExtra(USER_ID, id);
        return intent;
    }

    private UserProfileAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_user_profile);
        setupToolbar(getString(R.string.events), R.drawable.ic_menu);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupDrawer();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new UserProfileAdapter();
        mRecyclerView.setAdapter(mAdapter);
        presenter.loadData(getIntent().getIntExtra(USER_ID, -1));
    }

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getData());
        mAdapter.notifyDataSetChanged();
    }
}

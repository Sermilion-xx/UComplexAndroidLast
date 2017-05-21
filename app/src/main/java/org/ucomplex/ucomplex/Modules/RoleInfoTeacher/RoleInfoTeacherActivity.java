package org.ucomplex.ucomplex.Modules.RoleInfoTeacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.RoleInfo.RoleInfoActivity;
import org.ucomplex.ucomplex.R;

public class RoleInfoTeacherActivity extends BaseMVPActivity<MVPView, RoleInfoTeacherPresenter> {

    public static final String ROLE_ID = "roleId";
    public static final String NAME = "name";

    public static Intent creteIntent(Context context, int roleId, String name) {
        Intent intent = new Intent(context, RoleInfoActivity.class);
        intent.putExtra(ROLE_ID, roleId);
        intent.putExtra(NAME, name);
        return intent;
    }

    private RoleInfoTeacherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentViewWithNavDrawer(R.layout.activity_role_info_teacher);
        setupToolbar(intent.getStringExtra(NAME), R.drawable.ic_menu);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupDrawer();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RoleInfoTeacherAdapter();
        mRecyclerView.setAdapter(mAdapter);
        presenter.loadData(intent.getIntExtra(ROLE_ID, -1));
    }

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getData());
        mAdapter.notifyDataSetChanged();
    }
}

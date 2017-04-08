package org.ucomplex.ucomplex.Modules.SubjectsList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Events.EventsActivity;
import org.ucomplex.ucomplex.Modules.Events.EventsAdapter;
import org.ucomplex.ucomplex.R;

import javax.inject.Inject;

public class SubjectsListActivity extends BaseMVPActivity<MVPView, SubjectsListPresenter> {

    public static Intent creteIntent (Context context) {
        Intent intent = new Intent(context, SubjectsListActivity.class);
        return intent;
    }

    private RecyclerView mRecyclerView;
    private SubjectsListAdapter mAdapter;

    @Inject
    @Override
    public void setPresenter(@NonNull SubjectsListPresenter presenter) {
        super.setPresenter(presenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_subjects_list);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupToolbar(getString(R.string.subjects), R.drawable.ic_menu);
        setupDrawer();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SubjectsListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        if (presenter.getData() == null || presenter.getData().size() == 0) {
            presenter.loadData(null);
        } else {
            dataLoaded();
        }
    }

    public void dataLoaded() {
        mAdapter.setItems(presenter.getData());
        mAdapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectsListPresenter createPresenter() {
        return presenter;
    }
}

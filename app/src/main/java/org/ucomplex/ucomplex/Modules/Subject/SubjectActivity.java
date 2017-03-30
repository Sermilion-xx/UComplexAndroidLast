package org.ucomplex.ucomplex.Modules.Subject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Events.EventsActivity;
import org.ucomplex.ucomplex.R;

import javax.inject.Inject;

public class SubjectActivity extends BaseActivity<MVPView, SubjectPresenter> implements MVPView{

    private static final String EXTRA_GCOURSE = "EXTRA_GCOURSE";
    private static final String EXTRA_COURSE_NAME = "EXTRA_COURSE_NAME";

    public static Intent creteIntent (Context context, int gcourse, String courseName) {
        Intent intent = new Intent(context, SubjectActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA_GCOURSE, gcourse);
        intent.putExtra(EXTRA_COURSE_NAME, courseName);
        return intent;
    }

    private RecyclerView mRecyclerView;
    private SubjectProfileAdapter mAdapter;

    @Inject
    @Override
    public void setPresenter(@NonNull SubjectPresenter presenter) {
        super.setPresenter(presenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_subject);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        Intent intent = getIntent();
        setupToolbar(intent.getStringExtra(EXTRA_COURSE_NAME), R.drawable.ic_menu);
        setupDrawer();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SubjectProfileAdapter();
        mRecyclerView.setAdapter(mAdapter);
        if (presenter.getData() == null) {
            presenter.loadData(intent.getIntExtra(EXTRA_GCOURSE, 0));
        }
    }

    public void subjectLoaded() {
        mAdapter.setItems(presenter.getData().getProfileItems());
        mAdapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectPresenter createPresenter() {
        return presenter;
    }

    @Override
    public Context getAppContext() {
        return UCApplication.getInstance();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }
}

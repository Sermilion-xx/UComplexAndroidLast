package org.ucomplex.ucomplex.Modules.Portfolio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.SubjectMaterialsAdapter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.SubjectMaterialsPresenter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectMaterialsParams;
import org.ucomplex.ucomplex.R;

public class PortfolioActivity extends BaseMVPActivity<MVPView, SubjectMaterialsPresenter> {

    public static Intent creteIntent(Context context) {
        return new Intent(context, PortfolioActivity.class);
    }

    private SubjectMaterialsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_portfolio);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupToolbar(getString(R.string.portfolio), R.drawable.ic_menu);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setupDrawer();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SubjectMaterialsAdapter();
        mRecyclerView.setAdapter(mAdapter);
        if (presenter.getData() == null || presenter.getData().size() == 0) {
            SubjectMaterialsParams params = new SubjectMaterialsParams();
            params.setMyFolder(true);
            presenter.loadData(params);
        } else {
            dataLoaded();
        }

    }

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getCurrentHistory());
        mAdapter.notifyDataSetChanged();
    }
}

package org.ucomplex.ucomplex.Modules.RoleSelect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.IntentCallback;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Events.EventsActivity;
import org.ucomplex.ucomplex.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoleSelectActivity extends BaseActivity<MVPView, RoleSelectPresenter> implements MVPView, IntentCallback<Integer> {

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;

    private RoleSelectAdapter mAdapter;

    @Inject
    @Override
    public void setPresenter(@NonNull RoleSelectPresenter presenter) {
        super.setPresenter(presenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_select);
        ButterKnife.bind(this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RoleSelectAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void updateAdapter(List<RoleItem> items) {
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoleSelectPresenter createPresenter() {
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

    @Override
    public void creteIntent(Integer position) {
        presenter.onRoleSelected(position);
        startActivity(EventsActivity.creteIntent(this));
        finish();
    }
}

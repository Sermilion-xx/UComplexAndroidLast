package org.ucomplex.ucomplex.Modules.RoleSelect;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.interfaces.IntentCallback;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.Modules.Events.EventsActivity;
import org.ucomplex.ucomplex.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoleSelectActivity extends BaseMVPActivity<MVPView, RoleSelectPresenter> implements IntentCallback<Integer> {

    private static final String EXTRA_USER = "EXTRA_USER";
    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;

    private RoleSelectAdapter mAdapter;

    public static Intent creteIntent (Context context, UserInterface userInterface) {
        Intent intent = new Intent(context, RoleSelectActivity.class);
        intent.putExtra(EXTRA_USER, (Parcelable) userInterface);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Inject
    @Override
    public void setPresenter(@NonNull RoleSelectPresenter presenter) {
        super.setPresenter(presenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_select);
        ButterKnife.bind(this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RoleSelectAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        if (presenter.getData() == null) {
            presenter.loadData(getIntent().getParcelableExtra(EXTRA_USER));
        } else {
            mAdapter.setItems(presenter.getData());
            mAdapter.notifyDataSetChanged();
        }
    }

    public void initRecyclerView(List<RoleItem> items) {
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoleSelectPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void creteIntent(Integer position) {
        presenter.onRoleSelected(position);
        startActivity(EventsActivity.creteIntent(this));
        finish();
    }
}

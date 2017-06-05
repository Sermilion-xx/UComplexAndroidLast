package org.ucomplex.ucomplex.Modules.Users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseMvpFragment;
import org.ucomplex.ucomplex.Domain.users.User;
import org.ucomplex.ucomplex.Modules.UserProfile.UserProfileActivity;
import org.ucomplex.ucomplex.Modules.Users.model.UserRequestType;
import org.ucomplex.ucomplex.Modules.Users.model.UsersParams;
import org.ucomplex.ucomplex.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 14/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UsersFragment extends BaseMvpFragment<UsersPresenter> {

    private UserRequestType userType;

    @BindView(R.id.progressBar)
    protected ProgressBar mProgress;
    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    private UsersAdapter mAdapter;

    public void setUserType(UserRequestType userType) {
        this.userType = userType;
    }

    @Override @Inject
    public void setPresenter(@NonNull UsersPresenter presenter) {
        super.setPresenter(presenter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivityContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new UsersAdapter(userType);
        mAdapter.setOnListItemClicked((param, type) -> {
            if (type == UsersAdapter.TYPE_USER) {
                startActivity(UserProfileActivity.creteIntent(getActivityContext(), param));
            } else if (type == UsersAdapter.TYPE_FOOTER) {
                UsersParams params = UsersParams.createLoadMoreParams(userType, param);
                presenter.loadData(params);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.attachView(this);
        if (presenter.getData() == null) {
            UsersParams params = UsersParams.createLoadMoreParams(userType, 0);
            presenter.loadData(params);
        } else {
            mAdapter.setItems(presenter.getData());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getData());
        mAdapter.notifyDataSetChanged();
    }

    public void dataLoaded(List<User> users) {
        mAdapter.addItems(users);
    }
}
package org.ucomplex.ucomplex.Modules.Subject.SubjectProfile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.OnPresenterInjectedListener;
import org.ucomplex.ucomplex.Common.interfaces.ViewExtensions;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectItemFile;
import org.ucomplex.ucomplex.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.ucomplex.ucomplex.Modules.Subject.SubjectActivity.EXTRA_GCOURSE;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 31/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectProfileFragment extends MvpFragment<MVPView, SubjectPresenter> implements MVPView, ViewExtensions {

    public static SubjectProfileFragment getInstance(int gcourse) {
        SubjectProfileFragment fragment = new SubjectProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_GCOURSE, gcourse);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.progressBar)
    protected ProgressBar mProgress;
    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    private SubjectProfileAdapter mAdapter;
    private OnPresenterInjectedListener<List<SubjectItemFile>> onPresenterInjectedListener;

    public void setOnPresenterInjectedListener(OnPresenterInjectedListener<List<SubjectItemFile>> onPresenterInjectedListener) {
        this.onPresenterInjectedListener = onPresenterInjectedListener;
    }

    @Inject
    @Override
    public void setPresenter(@NonNull SubjectPresenter presenter) {
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
        View view = inflater.inflate(R.layout.fragment_subject_profile, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivityContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SubjectProfileAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.attachView(this);
        if (presenter.getData() == null) {
            presenter.loadData(getArguments().getInt(EXTRA_GCOURSE, 0));
        } else {
            mAdapter.setItems(presenter.getData().getProfileItems());
            mAdapter.notifyDataSetChanged();
        }
    }

    public void subjectLoaded() {
        mAdapter.setItems(presenter.getData().getProfileItems());
        mAdapter.notifyDataSetChanged();
        onPresenterInjectedListener.presenterInjected(presenter.getModel().getData().getMaterialsItems());
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
        return getContext();
    }

    @Override
    public void showProgress() {
        if (mProgress != null) {
            mProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (mProgress != null) {
            mProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showToast(int textId, int length) {
        Toast.makeText(getActivityContext(), textId, length).show();
    }
}
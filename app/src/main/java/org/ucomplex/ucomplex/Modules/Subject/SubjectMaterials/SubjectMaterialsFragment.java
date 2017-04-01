package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.OnlIstItemClicked;
import org.ucomplex.ucomplex.Common.interfaces.ViewExtensions;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Domain.Users.Teacher;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectItemFile;
import org.ucomplex.ucomplex.R;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 31/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectMaterialsFragment extends MvpFragment<MVPView, SubjectMaterialsPresenter> implements MVPView, ViewExtensions {

    private static final String MY_FILES = "MY_FILES";

    public static SubjectMaterialsFragment getInstance(boolean myFiles) {
        SubjectMaterialsFragment fragment = new SubjectMaterialsFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(MY_FILES, myFiles);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.progressBar)
    protected ProgressBar mProgress;
    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    private SubjectMaterialsAdapter mAdapter;

    public SubjectMaterialsAdapter getAdapter() {
        return mAdapter;
    }

    public void onBackPress(){
        presenter.pageDown();
        mAdapter.notifyDataSetChanged();
    }

    public int getCurrentPage(){
        return presenter.getCurrentPage();
    }

    public void setMaterialsItems(Pair<List<SubjectItemFile>, Map<Integer, Teacher>> items) {
        presenter.setMaterialsItems(items.first);
        presenter.getModel().setTeachers(items.second);
        mAdapter.setItems(items.first);
        mAdapter.notifyDataSetChanged();
    }

    @Inject
    @Override
    public void setPresenter(@NonNull SubjectMaterialsPresenter presenter) {
        super.setPresenter(presenter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mAdapter.getAdapterSize() == 0) {
                mAdapter.setItems(presenter.getCurrentHistory());
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject_profile, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivityContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SubjectMaterialsAdapter();
        mAdapter.setMyFiles(getArguments().getBoolean(MY_FILES));
        mAdapter.setOnlIstItemClicked(params -> presenter.loadData(params));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public void dataLoaded() {
        mAdapter.setItems(presenter.getCurrentHistory());
        mAdapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectMaterialsPresenter createPresenter() {
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

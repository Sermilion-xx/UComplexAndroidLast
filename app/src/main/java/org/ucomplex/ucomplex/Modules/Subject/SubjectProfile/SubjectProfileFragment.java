package org.ucomplex.ucomplex.Modules.Subject.SubjectProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseMvpFragment;
import org.ucomplex.ucomplex.Common.interfaces.OnPresenterInjectedListener;
import org.ucomplex.ucomplex.Domain.Users.role.RoleTeacher;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectItemFile;
import org.ucomplex.ucomplex.R;

import java.util.List;
import java.util.Map;

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

public class SubjectProfileFragment extends BaseMvpFragment<SubjectPresenter> {

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
    private OnPresenterInjectedListener<Pair<List<SubjectItemFile>, Map<Integer, RoleTeacher>>> onPresenterInjectedListener;

    public void setOnPresenterInjectedListener(OnPresenterInjectedListener<Pair<List<SubjectItemFile>, Map<Integer, RoleTeacher>>> onPresenterInjectedListener) {
        this.onPresenterInjectedListener = onPresenterInjectedListener;
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

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getData().getProfileItems());
        mAdapter.notifyDataSetChanged();
        onPresenterInjectedListener.presenterInjected(presenter.getModel().getFilesAndTeachers());
    }
}

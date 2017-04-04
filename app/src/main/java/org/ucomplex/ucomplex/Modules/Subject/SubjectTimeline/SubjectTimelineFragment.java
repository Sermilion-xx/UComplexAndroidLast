package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseMvpFragment;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineParams;
import org.ucomplex.ucomplex.R;

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

public class SubjectTimelineFragment extends BaseMvpFragment<SubjectTimelinePresenter> {

    public static SubjectTimelineFragment getInstance(int gcourse) {
        SubjectTimelineFragment fragment = new SubjectTimelineFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_GCOURSE, gcourse);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @Inject
    protected SubjectTimelineAdapter mAdapter;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mAdapter.getItemCount() == 0) {
                mAdapter.setItems(presenter.getData());
                mAdapter.notifyDataSetChanged();
            }
        }
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
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivityContext());
        mAdapter.setOnListItemClicked(params -> {
            params.setGcourse(getArguments().getInt(EXTRA_GCOURSE));
            presenter.loadData(params);
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        if (presenter.getData() == null) {
            SubjectTimelineParams params = new SubjectTimelineParams();
            params.setGcourse(getArguments().getInt(EXTRA_GCOURSE));
            presenter.loadData(params);
        }
        return view;
    }

    @Override
    public void dataLoaded() {
        mAdapter.getItems().addAll(presenter.getData());
        mAdapter.notifyDataSetChanged();
    }
}

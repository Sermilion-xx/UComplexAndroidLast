package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import android.content.pm.PackageManager;
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

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseMvpFragment;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Domain.Users.role.Role;
import org.ucomplex.ucomplex.Domain.Users.role.RoleTeacher;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectItemFile;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectMaterialsParams;
import org.ucomplex.ucomplex.R;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.ucomplex.ucomplex.Common.FacadeCommon.REQUEST_EXTERNAL_STORAGE;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 31/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectMaterialsFragment extends BaseMvpFragment<SubjectMaterialsPresenter> {

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
    @Inject
    protected SubjectMaterialsAdapter mAdapter;

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

    public void setMaterialsItems(Pair<List<SubjectItemFile>, Map<Integer, Role>> items) {
        presenter.setMaterialsItems(items.first);
        presenter.getModel().setTeachers(items.second);
        mAdapter.setItems(items.first);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mAdapter.getItemCount() == 0) {
                mAdapter.setItems(presenter.getCurrentHistory());
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
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivityContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter.setMyFiles(getArguments().getBoolean(MY_FILES));
        mAdapter.setOnListItemClicked((params, type) -> presenter.loadData(params));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getCurrentHistory());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(getActivityContext(), getString(R.string.storage_access_denied), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}

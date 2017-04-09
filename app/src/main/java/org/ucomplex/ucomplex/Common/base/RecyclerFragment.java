package org.ucomplex.ucomplex.Common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;

import javax.inject.Inject;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 08/03/2017.
 * Project: OneAccount
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RecyclerFragment extends MvpFragment<MVPView, MvpPresenter<MVPView>> implements MVPView {

    private BaseAdapter mAdapter;

    public BaseAdapter getAdapter() {
        return mAdapter;
    }

    public static RecyclerFragment getInstance() {
        return new RecyclerFragment();
    }

    @SuppressWarnings("unchecked")
    public void receiveNewData(Object items) {
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
    }

    @Inject
    public void setPresenter(@NonNull MvpPresenter<MVPView> presenter) {
        this.presenter = presenter;
    }

    @Inject
    public void setAdapter(BaseAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @NonNull
    @Override
    public MvpPresenter<MVPView> createPresenter() {
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
    public void dataLoaded() {

    }

}

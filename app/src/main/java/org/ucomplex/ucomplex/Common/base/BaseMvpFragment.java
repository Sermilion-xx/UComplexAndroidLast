package org.ucomplex.ucomplex.Common.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.R;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public abstract class BaseMvpFragment<P extends MvpPresenter<MVPView>> extends MvpFragment<MVPView, P> implements MVPView {

    @BindView(R.id.progressBar)
    protected ProgressBar mProgress;
    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;

    public abstract void dataLoaded();

    @NonNull
    @Override
    public P createPresenter() {
        return presenter;
    }

    @Inject
    @Override
    public void setPresenter(@NonNull P presenter) {
        super.setPresenter(presenter);
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
    public void showToast(int textId, int...length) {
        int toastLength = Toast.LENGTH_LONG;
        if (length.length > 0) {
            toastLength = length[0];
        }
        if (toastLength == Toast.LENGTH_LONG) {
            Toast.makeText(getActivityContext(), textId, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivityContext(), textId, Toast.LENGTH_SHORT).show();
        }
    }
}

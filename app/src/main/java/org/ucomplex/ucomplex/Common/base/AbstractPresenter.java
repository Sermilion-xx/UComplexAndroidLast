package org.ucomplex.ucomplex.Common.base;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPPresenter;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;

import javax.inject.Inject;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/03/2017.
 * Project: OneAccount
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public abstract class AbstractPresenter<
        M,
        T,
        Param,
        Model extends MVPModel<M, T, Param>>
        extends MvpBasePresenter<MVPView>
        implements MVPPresenter<Model, T, Param> {

    protected Model mModel;

    @Inject
    public void setModel(Model model) {
        mModel = model;
    }

    @Override
    public Context getAppContext() {
        return UCApplication.getInstance();
    }

    @Override
    public Context getActivityContext() {
        if (getView() != null) {
            return getView().getActivityContext();
        }
        return null;
    }

    @Override
    public void clear() {
        mModel.clear();
    }

    @Override
    public Model getModel() {
        return mModel;
    }

    @Override
    public T getData() {
        if (mModel == null) {
            return null;
        }else {
            return mModel.getData();
        }
    }

    public void showProgress() {
        if (getView() != null) {
            getView().showProgress();
        }
    }

    public void hideProgress() {
        if (getView() != null) {
            getView().hideProgress();
        }
    }
}

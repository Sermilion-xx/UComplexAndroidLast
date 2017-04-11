package org.ucomplex.ucomplex.Common;

import android.support.annotation.NonNull;

import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPPresenter;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 11/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface PresenterFactory<T extends MVPPresenter> {

    /**
     * Create a new instance of a Presenter
     * @return The Presenter instance
     */
    @NonNull
    T createPresenter();
}
package org.ucomplex.ucomplex.Common.interfaces.mvp;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 10/02/2017.
 * Project: Listening
 * ---------------------------------------------------
 * <a href="http://www.skyeng.ru">www.skyeng.ru</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface MVPPresenter<L, P, R> extends MvpPresenter<MVPView> {
    Context getAppContext();
    Context getActivityContext();
    void clear();
    L getModel();
    P getData();
    void loadData(R params);
}

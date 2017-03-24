package org.ucomplex.ucomplex.Common.interfaces.mvp;

import io.reactivex.Observable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 10/02/2017.
 * Project: Listening
 * ---------------------------------------------------
 * <a href="http://www.skyeng.ru">www.skyeng.ru</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface MVPModel<L, P, E> {
    Observable<L> loadData(E params);
    void setData(P data);
    void addData(P data);
    void clear();
    P getData();
    P processData(L data);
}

package org.ucomplex.ucomplex.Modules.Events;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 29/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface LoadMoreCallback<T> {
    void loadMoreData(T params);
}

package org.ucomplex.ucomplex.Common.utility;

import android.support.v4.util.SimpleArrayMap;

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

public class PresenterCache {

    private static PresenterCache instance = null;
    private SimpleArrayMap<String, MVPPresenter> presenters;

    private PresenterCache() {
        presenters = new SimpleArrayMap<>();
    }

    public static PresenterCache getInstance() {
        if (instance == null) {
            instance = new PresenterCache();
        }
        return instance;
    }

    public void addToChache(String who, MVPPresenter p) {
        presenters.put(who, p);
    }

    public MVPPresenter getFromChache(String who) {
        return presenters.get(who);
    }

    /**
     * Remove the presenter associated with the given tag
     * @param who A unique tag to identify the presenter
     */
    public final void removePresenter(String who) {
        if (presenters != null) {
            presenters.remove(who);
        }
    }
}

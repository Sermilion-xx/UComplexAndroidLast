package org.ucomplex.ucomplex.Common;

import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

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

    /**
     * Returns a presenter instance that will be stored and
     * survive configuration changes
     * @param who A unique tag to identify the presenter
     * @param presenterFactory A factory to create the presenter
     *        if it doesn't exist yet
     * @param <T> The presenter type
     * @return The presenter
     */
    @SuppressWarnings("unchecked") // Handled internally
    protected final <T extends MVPPresenter> T getPresenter(
            String who, PresenterFactory<T> presenterFactory) {
        if (presenters == null) {
            presenters = new SimpleArrayMap<>();
        }
        T p = null;
        try {
            p = (T) presenters.get(who);
        } catch (ClassCastException e) {
            Log.w("PresenterActivity", "Duplicate Presenter " +
                    "tag identified: " + who + ". This could " +
                    "cause issues with state.");
        }
        if (p == null) {
            p = presenterFactory.createPresenter();
            presenters.put(who, p);
        }
        return p;
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

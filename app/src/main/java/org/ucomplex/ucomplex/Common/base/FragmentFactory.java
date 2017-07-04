package org.ucomplex.ucomplex.Common.base;


import android.support.v4.app.Fragment;

import org.ucomplex.ucomplex.Modules.Settings.SettingsProfileFragment;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/07/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class FragmentFactory {

    public static Fragment getFragment(Class<?> fragmentClass) {
        if (fragmentClass == SettingsProfileFragment.class) {
            return SettingsProfileFragment.getInstance();
        } else {
            return null;
        }

    }
}

package org.ucomplex.ucomplex.Common;


import android.support.v4.app.Fragment;

import org.ucomplex.ucomplex.Common.base.RecyclerFragment;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 08/03/2017.
 * Project: OneAccount
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class FragmentFactory {

    public static final Fragment getFragmentForName(String name){
        if(name.equals(RecyclerFragment.class.getName())) {
            return RecyclerFragment.getInstance();
        }
        return null;
    }
}

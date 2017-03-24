package org.ucomplex.ucomplex.Common.navdrawer;

import android.content.Context;
import android.support.v4.util.Pair;

/**
 * Created by Sermilion on 03/11/2016.
 */

public class FacadeDrawer {

    private static FacadeDrawer instance;
    private Context mContext;

    public static FacadeDrawer getInstance(Context context) {
        if(instance == null){
            instance = new  FacadeDrawer(context);
        }
        return instance;
    }

    private FacadeDrawer(Context context){
        this.mContext = context;
    }

    public Pair<int[], String[]> getDrawerItemsUser0(){
        int[] icons = new int[]{};
        String[] titles = new String[] {};
        return new Pair<>(icons, titles);
    }

    protected String getString(int id) {
        return mContext.getString(id);
    }

}

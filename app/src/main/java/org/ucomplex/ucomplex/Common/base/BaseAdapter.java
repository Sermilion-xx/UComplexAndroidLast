package org.ucomplex.ucomplex.Common.base;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 09/03/2017.
 * Project: OneAccount
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, D> extends RecyclerView.Adapter<T>{

    protected D mItems;

    public void setItems(D data) {
        mItems = data;
    }

    public D getItems() {
        return mItems;
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : ((List)mItems).size() ;
    }

}

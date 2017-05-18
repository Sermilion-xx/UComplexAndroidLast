package org.ucomplex.ucomplex.Modules.RoleInfo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoItem;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleInfoAdapter extends BaseAdapter<RoleInfoAdapter.RoleInfoViewHolder, List<RoleInfoItem>> {

    static class RoleInfoViewHolder extends RecyclerView.ViewHolder {

        public RoleInfoViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RoleInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RoleInfoViewHolder holder, int position) {

    }
}

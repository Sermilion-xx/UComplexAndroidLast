package org.ucomplex.ucomplex.Modules.RoleInfoTeacher;

import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoteacherItem;
import org.ucomplex.ucomplex.R;

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

public class RoleInfoTeacherAdapter extends BaseAdapter<RoleInfoTeacherAdapter.RoleInfoTeacherViewHolder, List<RoleInfoteacherItem>> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_INFO = 1;

    static class RoleInfoTeacherViewHolder extends RecyclerView.ViewHolder {

        TextView key;
        TextView value;

        public RoleInfoTeacherViewHolder(View itemView) {
            super(itemView);
            key = (TextView) itemView.findViewById(R.id.key);
            value = (TextView) itemView.findViewById(R.id.value);
        }
    }

    @Override
    public RoleInfoTeacherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            if (viewType == TYPE_HEADER) {
                layout = R.layout.item_role_info_header;
            } else if (viewType == TYPE_INFO) {
                layout = R.layout.item_role_info;
            }
        }
        View view = inflater.inflate(layout, parent, false);
        return new RoleInfoTeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoleInfoTeacherViewHolder holder, int position) {
        RoleInfoteacherItem item = mItems.get(position);

    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_INFO;
    }

}

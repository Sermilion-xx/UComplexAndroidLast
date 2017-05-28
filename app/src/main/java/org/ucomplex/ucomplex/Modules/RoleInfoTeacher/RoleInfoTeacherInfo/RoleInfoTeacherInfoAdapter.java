package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherInfo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoTeacherInfoItem;
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

public class RoleInfoTeacherInfoAdapter extends BaseAdapter<RoleInfoTeacherInfoAdapter.RoleInfoTeacherInfoViewHolder, List<RoleInfoTeacherInfoItem>> {

    static class RoleInfoTeacherInfoViewHolder extends RecyclerView.ViewHolder {

        TextView key;
        TextView value;

        RoleInfoTeacherInfoViewHolder(View itemView) {
            super(itemView);
            key = (TextView) itemView.findViewById(R.id.key);
            value = (TextView) itemView.findViewById(R.id.value);
        }
    }

    @Override
    public RoleInfoTeacherInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            layout = R.layout.item_teacher_info;
        }
        View view = inflater.inflate(layout, parent, false);
        return new RoleInfoTeacherInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoleInfoTeacherInfoViewHolder holder, int position) {
        RoleInfoTeacherInfoItem item = mItems.get(position);
        if (mItems.size() > 0) {
            holder.key.setText(item.getKey());
            holder.value.setText(item.getValue());
        }
    }

}

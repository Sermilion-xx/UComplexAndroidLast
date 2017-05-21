package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherProfile;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoTeacherItem;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
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

public class RoleInfoTeacherProfileAdapter extends BaseAdapter<RoleInfoTeacherProfileAdapter.RoleInfoTeacherViewHolder, List<RoleInfoTeacherItem>> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_INFO = 1;

    static class RoleInfoTeacherViewHolder extends RecyclerView.ViewHolder {

        TextView key;
        TextView value;
        View above;
        View below;

        RoleInfoTeacherViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == TYPE_HEADER) {
                above = itemView.findViewById(R.id.above);
                below = itemView.findViewById(R.id.below);
            }
            key = (TextView) itemView.findViewById(R.id.key);
            value = (TextView) itemView.findViewById(R.id.value);
        }
    }

    private ArrayList<String> colors = new ArrayList<>();

    RoleInfoTeacherProfileAdapter() {
        colors.add("#E77272");
        colors.add("#E77D72");
        colors.add("#E78D72");
        colors.add("#E7A472");
        colors.add("#E8B472");
        colors.add("#E8C272");
        colors.add("#E8C272");
        colors.add("#E6E773");
        colors.add("#c3e874");
        colors.add("#89e874");
    }

    @Override
    public RoleInfoTeacherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            if (viewType == TYPE_HEADER) {
                layout = R.layout.item_role_info_profile_activity;
            } else if (viewType == TYPE_INFO) {
                layout = R.layout.item_role_info_profile_info;
            }
        }
        View view = inflater.inflate(layout, parent, false);
        return new RoleInfoTeacherViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(RoleInfoTeacherViewHolder holder, int position) {
        RoleInfoTeacherItem item = mItems.get(position);
        if (mItems.size() > 0) {
            if (getItemViewType(position) == TYPE_HEADER) {
                int fullWidth = holder.below.getLayoutParams().width;
                long percent = Math.round(item.getValueInt());
                long width = (Math.round(item.getValueInt()) * fullWidth / 100);
                fillProgress(holder.above, percent);
                holder.above.getLayoutParams().width = (int) width;
                holder.value.setText(Math.round(item.getValueInt())+" %");
            } else {
                holder.key.setText(item.getKey());
                holder.value.setText(item.getValue());
            }
        }
    }

    private void fillProgress(View view, long percent) {
        if(percent <= 10){
            view.setBackgroundColor(Color.parseColor(colors.get(0)));
        }else if(percent<=20 && percent>10){
            view.setBackgroundColor(Color.parseColor(colors.get(1)));
        }else if(percent<=30 && percent>20){
            view.setBackgroundColor(Color.parseColor(colors.get(2)));
        }else if(percent<=40 && percent>30){
            view.setBackgroundColor(Color.parseColor(colors.get(3)));
        }else if(percent<=50 && percent>40){
            view.setBackgroundColor(Color.parseColor(colors.get(4)));
        }else if(percent<=60 && percent>50){
            view.setBackgroundColor(Color.parseColor(colors.get(5)));
        }else if(percent<=70 && percent>60){
            view.setBackgroundColor(Color.parseColor(colors.get(6)));
        }else if(percent<=80 && percent>70){
            view.setBackgroundColor(Color.parseColor(colors.get(7)));
        }else if(percent<=90 && percent>80){
            view.setBackgroundColor(Color.parseColor(colors.get(8)));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_INFO;
    }

}

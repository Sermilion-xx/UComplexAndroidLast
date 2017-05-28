package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model.RoleInfoTeacherRankItem;

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

public class RoleInfoTeacherRankAdapter extends BaseAdapter<RoleInfoTeacherRankAdapter.RoleInfoTeacherRankViewHolder, List<RoleInfoTeacherRankItem>> {

    static class RoleInfoTeacherRankViewHolder extends RecyclerView.ViewHolder {


        RoleInfoTeacherRankViewHolder(View itemView, int viewType) {
            super(itemView);

        }
    }


    @Override
    public RoleInfoTeacherRankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {

        }
        View view = inflater.inflate(layout, parent, false);
        return new RoleInfoTeacherRankViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(RoleInfoTeacherRankViewHolder holder, int position) {

    }


}

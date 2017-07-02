package org.ucomplex.ucomplex.Modules.Settings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Settings.model.SettingsItem;

import java.util.ArrayList;
import java.util.List;

class SettingsAdapter extends BaseAdapter<SettingsAdapter.SettingsViewHolder, List<SettingsItem>> {

    static class SettingsViewHolder extends RecyclerView.ViewHolder {

        SettingsViewHolder(View itemView) {
            super(itemView);

        }
    }

    public SettingsAdapter() {
        mItems = new ArrayList<>();
    }


    @Override
    public SettingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {

        }
        View view = inflater.inflate(layout, parent, false);
        return new SettingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SettingsViewHolder holder, int position) {
        if (mItems.size() > 0) {
            SettingsItem item = mItems.get(position);
        }
    }


    @Override
    public int getItemCount() {
        return (mItems == null || mItems.size() == 0) ? 1 : mItems.size();
    }

}
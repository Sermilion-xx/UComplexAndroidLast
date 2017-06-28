package org.ucomplex.ucomplex.Modules.CalendarDay.CalendarDayPerformance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.CalendarDay.CalendarDayPerformance.model.CalendarDayPerformanceItem;

import java.util.List;

class CalendarDayPerformanceAdapter extends BaseAdapter<CalendarDayPerformanceAdapter.CalendarDayViewHolder, List<CalendarDayPerformanceItem>> {

    static class CalendarDayViewHolder extends RecyclerView.ViewHolder {

        CalendarDayViewHolder(View itemView) {
            super(itemView);

        }
    }


    @Override
    public CalendarDayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {

        }
        View view = inflater.inflate(layout, parent, false);
        return new CalendarDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalendarDayViewHolder holder, int position) {
        if (mItems.size() > 0) {
            CalendarDayPerformanceItem item = mItems.get(position);
        }
    }


    @Override
    public int getItemCount() {
        return (mItems == null || mItems.size() == 0) ? 1 : mItems.size();
    }

}
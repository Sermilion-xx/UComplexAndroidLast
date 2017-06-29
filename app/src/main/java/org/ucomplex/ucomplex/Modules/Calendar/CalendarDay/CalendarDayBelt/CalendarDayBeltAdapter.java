package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt.model.CalendarDayBeltItem;

import java.util.ArrayList;
import java.util.List;

public class CalendarDayBeltAdapter extends BaseAdapter<CalendarDayBeltAdapter.CalendarDayViewHolder, List<CalendarDayBeltItem>> {

    static class CalendarDayViewHolder extends RecyclerView.ViewHolder {

        CalendarDayViewHolder(View itemView) {
            super(itemView);

        }
    }

    public CalendarDayBeltAdapter() {
        mItems = new ArrayList<>();
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
            CalendarDayBeltItem item = mItems.get(position);
        }
    }


    @Override
    public int getItemCount() {
        return (mItems == null || mItems.size() == 0) ? 1 : mItems.size();
    }

}
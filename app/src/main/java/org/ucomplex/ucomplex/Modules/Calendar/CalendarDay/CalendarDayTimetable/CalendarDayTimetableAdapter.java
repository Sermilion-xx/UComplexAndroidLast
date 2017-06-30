package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable.model.CalendarDayTimetableItem;

import java.util.ArrayList;
import java.util.List;

public class CalendarDayTimetableAdapter extends BaseAdapter<CalendarDayTimetableAdapter.CalendarDayTimetableViewHolder, List<CalendarDayTimetableItem>> {

    static class CalendarDayTimetableViewHolder extends RecyclerView.ViewHolder {

        CalendarDayTimetableViewHolder(View itemView) {
            super(itemView);

        }
    }

    public CalendarDayTimetableAdapter() {
        mItems = new ArrayList<>();
    }

    @Override
    public CalendarDayTimetableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {

        }
        View view = inflater.inflate(layout, parent, false);
        return new CalendarDayTimetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalendarDayTimetableViewHolder holder, int position) {
        if (mItems.size() > 0) {
            CalendarDayTimetableItem item = mItems.get(position);
        }
    }


    @Override
    public int getItemCount() {
        return (mItems == null || mItems.size() == 0) ? 1 : mItems.size();
    }

}
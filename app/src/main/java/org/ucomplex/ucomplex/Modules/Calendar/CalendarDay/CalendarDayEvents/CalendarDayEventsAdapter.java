package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayEvents;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayEvents.model.CalendarDayEventItem;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

public class CalendarDayEventsAdapter extends BaseAdapter<CalendarDayEventsAdapter.CalendarDayEventViewHolder, List<CalendarDayEventItem>> {

    static class CalendarDayEventViewHolder extends RecyclerView.ViewHolder {

        TextView image;
        TextView name;

        CalendarDayEventViewHolder(View itemView) {
            super(itemView);
            image = (TextView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    public CalendarDayEventsAdapter() {
        mItems = new ArrayList<>();
    }


    @Override
    public CalendarDayEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            layout = R.layout.item_calendar_day_event;
        }
        View view = inflater.inflate(layout, parent, false);
        return new CalendarDayEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalendarDayEventViewHolder holder, int position) {
        if (mItems.size() > 0) {
            CalendarDayEventItem item = mItems.get(position);
            holder.image.setBackgroundResource(R.drawable.round_button_red);
            if (item.getName() != null) {
                holder.image.setText(Character.toString(item.getName().charAt(0)));
            }
            holder.name.setText(item.getName());
        }
    }

    @Override
    public int getItemCount() {
        return (mItems == null || mItems.size() == 0) ? 1 : mItems.size();
    }

}
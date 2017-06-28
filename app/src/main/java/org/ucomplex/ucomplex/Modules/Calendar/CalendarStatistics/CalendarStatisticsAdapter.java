package org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.model.CalendarStatisticsItem;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

public class CalendarStatisticsAdapter extends BaseAdapter<CalendarStatisticsAdapter.CalendarStatisticsViewHolder, List<CalendarStatisticsItem>> {

    private static int TYPE_TITLE_1 = 0;
    private static int TYPE_TITLE_2 = 1;
    private static int TYPE_CURRENT = 2;
    private static int TYPE_PAST = 3;

    static class CalendarStatisticsViewHolder extends RecyclerView.ViewHolder {

        TextView header;
        TextView name;
        TextView mark;
        TextView attendance;

        CalendarStatisticsViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == TYPE_TITLE_1 || viewType == TYPE_TITLE_2) {
                header = (TextView) itemView.findViewById(R.id.header);
            } else {
                name = (TextView) itemView.findViewById(R.id.subject_name);
                mark = (TextView) itemView.findViewById(R.id.mark_value);
                attendance = (TextView) itemView.findViewById(R.id.attendance_value);
            }

        }
    }

    public CalendarStatisticsAdapter() {
        mItems = new ArrayList<>();
    }

    @Override
    public void setItems(List<CalendarStatisticsItem> data) {
        super.setItems(data);

    }

    @Override
    public CalendarStatisticsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            if (viewType == TYPE_TITLE_1 || viewType == TYPE_TITLE_2) {
                layout = R.layout.item_calendar_statistics_header;
            } else {
                layout = R.layout.item_calendar_statistics;
            }
        }
        View view = inflater.inflate(layout, parent, false);
        return new CalendarStatisticsViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(CalendarStatisticsViewHolder holder, int position) {
        if (mItems.size() > 0) {
            CalendarStatisticsItem item = mItems.get(position);
            int viewType = getItemViewType(position);
            if (viewType == TYPE_TITLE_1) {
                holder.header.setText(R.string.current_semester);
            } else if (viewType == TYPE_TITLE_2) {
                holder.header.setText(R.string.previous_semesters);
            } else {
                holder.name.setText(item.getName());
                holder.mark.setText(String.valueOf(item.getMark()));
                holder.attendance.setText(String.valueOf(item.getAttendance()) + "%");
            }
        }
    }

    @Override
    public int getItemCount() {
        return (mItems == null || mItems.size() == 0) ? 1 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.size() > 0) {
            CalendarStatisticsItem item = mItems.get(position);
            if (item.isCurrent() && item.getHeaderNumber() == -1) {
                return TYPE_CURRENT;
            } else if (!item.isCurrent() && item.getHeaderNumber() == -1) {
                return TYPE_PAST;
            } else if (mItems.get(position).getHeaderNumber() == 1) {
                return TYPE_TITLE_2;
            } else {
                return TYPE_TITLE_1;
            }
        } else {
            return super.getItemViewType(position);
        }
    }
}
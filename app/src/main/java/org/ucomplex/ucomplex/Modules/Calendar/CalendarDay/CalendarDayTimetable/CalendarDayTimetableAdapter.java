package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable.model.CalendarDayTimetableItem;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

public class CalendarDayTimetableAdapter extends BaseAdapter<CalendarDayTimetableAdapter.CalendarDayTimetableViewHolder, List<CalendarDayTimetableItem>> {

    static class CalendarDayTimetableViewHolder extends RecyclerView.ViewHolder {

        TextView disciplineName;
        TextView teacherName;
        TextView room;
        TextView time;
        TextView lessonType;

        CalendarDayTimetableViewHolder(View itemView) {
            super(itemView);
            disciplineName = (TextView) itemView.findViewById(R.id.discipline);
            teacherName = (TextView) itemView.findViewById(R.id.teacher);
            room = (TextView) itemView.findViewById(R.id.room);
            time = (TextView) itemView.findViewById(R.id.time);
            lessonType = (TextView) itemView.findViewById(R.id.type);

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
            layout = R.layout.item_calendar_day_timetable;
        }
        View view = inflater.inflate(layout, parent, false);
        return new CalendarDayTimetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalendarDayTimetableViewHolder holder, int position) {
        if (mItems.size() > 0) {
            CalendarDayTimetableItem item = mItems.get(position);
            holder.disciplineName.setText(item.getDisciplineName());
            holder.teacherName.setText(item.getTeacherName());
            holder.room.setText(item.getRoom());
            holder.time.setText(item.getTime());
            holder.lessonType.setText(item.getLessonType());
        }
    }


    @Override
    public int getItemCount() {
        return (mItems == null || mItems.size() == 0) ? 1 : mItems.size();
    }

}
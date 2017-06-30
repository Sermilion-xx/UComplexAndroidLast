package org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.model.CalendarBeltItem;
import org.ucomplex.ucomplex.Modules.Events.LoadMoreCallback;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CalendarBeltAdapter extends BaseAdapter<CalendarBeltAdapter.CalendarBeltViewHolder, List<CalendarBeltItem>> {

    public static final String MARK_ABCENT = "н";
    public static final String MARK_SICK = "б";

    private static final int TYPE_COMMON = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_NO_CONTENT = 2;

    static class CalendarBeltViewHolder extends RecyclerView.ViewHolder {

        TextView discilineName;
        TextView markImage;
        TextView time;
        TextView teacherName;
        Button loadMoreButton;

        CalendarBeltViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == TYPE_COMMON) {
                discilineName = (TextView) itemView.findViewById(R.id.name);
                markImage = (TextView) itemView.findViewById(R.id.image);
                time = (TextView) itemView.findViewById(R.id.time);
                teacherName = (TextView) itemView.findViewById(R.id.teacherName);
            } else if (viewType == TYPE_FOOTER){
                loadMoreButton = (Button) itemView.findViewById(R.id.loadMoreButton);
            }
        }
    }

    private LoadMoreCallback<Integer> mMoreCallback;
    private boolean hasMoreItems = false;

    public void setHasMoreItems(boolean hasMoreItems) {
        this.hasMoreItems = hasMoreItems;
    }

    @Inject
    public CalendarBeltAdapter() {
        mItems = new ArrayList<>();
    }




    public void setmMoreCallback(LoadMoreCallback<Integer> mMoreCallback) {
        this.mMoreCallback = mMoreCallback;
    }

    @Override
    public CalendarBeltViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            layout = viewType == TYPE_COMMON ? R.layout.item_calendar_belt : R.layout.item_footer;
        }
        View view = inflater.inflate(layout, parent, false);
        return new CalendarBeltViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(CalendarBeltViewHolder holder, int position) {
        if (mItems.size() > 0) {
            if (getItemViewType(position) == TYPE_COMMON) {
                CalendarBeltItem item = mItems.get(position);
                holder.discilineName.setText(item.getDisciplineName());
                if (item.getTime() != null) {
                    holder.time.setVisibility(View.VISIBLE);
                    holder.teacherName.setVisibility(View.VISIBLE);
                    holder.time.setText(item.getTime());
                    holder.teacherName.setText(item.getTeacherName());
                }
                Context context = holder.discilineName.getContext();
                int type = item.getType();
                if (type == 0) {
                    holder.markImage.setBackgroundResource(R.drawable.round_button_blue);
                } else if (type == 1) {
                    holder.markImage.setBackgroundResource(R.drawable.round_button_orange);
                } else if (type == 2) {
                    holder.markImage.setBackgroundResource(R.drawable.round_button_green);
                } else if (type == 3) {
                    holder.markImage.setBackgroundResource(R.drawable.round_button_purple);
                }
                if (item.getMark() == 0) {
                    Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");
                    holder.markImage.setTypeface(custom_font);
                }
                holder.markImage.setText(getLetter(item.getMark()));
            } else {
                if (mItems.size() < 20) {
                    holder.loadMoreButton.setVisibility(View.GONE);
                } else {
                    holder.loadMoreButton.setOnClickListener(v -> mMoreCallback.loadMoreData(getItemCount() + 1));
                }
            }
        }
    }

    private static String getLetter(int mark) {
        if (mark == -1) {
            return MARK_ABCENT;
        } else if (mark == 0) {
            return "\uF00C";
        } else if (mark == -3) {
            return MARK_SICK;
        } else {
            return String.valueOf(mark);
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null || mItems.size() == 0 ? 1 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.size() > 0 && position == getItemCount() - 1 && mItems.size() == 20) {
            return TYPE_FOOTER;
        } else if(mItems.size() == 0){
            return TYPE_NO_CONTENT;
        }  else {
            return TYPE_COMMON;
        }
    }

    void updateAdapterItems(List<CalendarBeltItem> items, boolean internetConnected) {
        setHasMoreItems(items.size() > 20 && internetConnected);
        if (mItems.size() == 0) {
            populateRecyclerView(items);
        } else {
            addMoreToRecyclerView(items);
        }
    }
}
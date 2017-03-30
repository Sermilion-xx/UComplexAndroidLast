package org.ucomplex.ucomplex.Modules.Events;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Events.model.EventItem;
import org.ucomplex.ucomplex.Modules.Subject.SubjectActivity;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import static org.ucomplex.ucomplex.Common.UCApplication.BASE_URL;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class EventsAdapter extends BaseAdapter<EventsAdapter.EventViewHolder, List<EventItem>> {

    private static final String PHOTOS_PATH = "files/photos/";
    private static final String FORMAT_JPG = ".jpg";

    static class EventViewHolder extends RecyclerView.ViewHolder {

        ImageView eventsImageView;
        TextView eventTextView;
        TextView eventTime;
        TextView eventPersonName;
        LinearLayout eventDetailsLayout;
        Button loadMoreEventsButton;

        public EventViewHolder(View view, int viewType) {
            super(view);
            if (viewType == TYPE_COMMON) {
                eventsImageView = (ImageView) view.findViewById(R.id.list_events_item_image);
                eventTextView = (TextView) view.findViewById(R.id.list_events_item_text);
                eventTime = (TextView) view.findViewById(R.id.list_events_item_date);
                eventPersonName = (TextView) view.findViewById(R.id.list_events_item_name);
                eventDetailsLayout = (LinearLayout) view.findViewById(R.id.event_details_layout);
            } else {
                loadMoreEventsButton = (Button) view.findViewById(R.id.loadMoreButton);
            }
        }
    }

    private boolean hasMoreEvents = false;
    private static final int TYPE_COMMON = 0;
    private static final int TYPE_FOOTER = 1;

    private LoadMoreCallback<Integer> mMoreCallback;

    public EventsAdapter(LoadMoreCallback<Integer> callback) {
        this.mMoreCallback = callback;
        mItems = new ArrayList<>();
    }

    public void setHasMoreEvents(boolean hasMoreEvents) {
        this.hasMoreEvents = hasMoreEvents;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size(), parent.getContext());
        if (layout == 0) {
            layout = viewType == TYPE_COMMON ? R.layout.item_event : R.layout.item_footer;
        }
        View view = inflater.inflate(layout, parent, false);
        return new EventViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_COMMON && mItems.size() > 0) {
            Context context = holder.eventPersonName.getContext();
            EventItem item = mItems.get(position);
            String personName = item.getParamsObj().getName();
            if (personName == null || personName.equals("")) {
                item.getParamsObj().setName(context.getResources().getString(R.string.app_name));
            }
            holder.eventPersonName.setText(item.getParamsObj().getName());
            holder.eventTextView.setText(item.getParamsObj().getMessage());
            holder.eventTime.setText(FacadeCommon.makeDate(item.getTime()));
            Drawable textDrawable = FacadeMedia.getTextDrawable(item.getParamsObj().getId(),
                    item.getParamsObj().getName(), context);
            String url = BASE_URL + PHOTOS_PATH + item.getParamsObj().getCode() + FORMAT_JPG;
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .placeholder(textDrawable)
                    .priority(Priority.HIGH)
                    .into(holder.eventsImageView);
            holder.eventDetailsLayout.setOnClickListener(v -> {
                if (!item.getParamsObj().getName().equals(context.getString(R.string.app_name))) {
                    SubjectActivity.creteIntent(context, item.getParamsObj().getGcourse(), item.getParamsObj().getCourseName());
                }
            });
        } else if (getItemViewType(position) == TYPE_FOOTER && holder.loadMoreEventsButton != null) {
            if (hasMoreEvents) {
                holder.loadMoreEventsButton.setVisibility(View.VISIBLE);
                holder.loadMoreEventsButton.setOnClickListener(v -> mMoreCallback.loadMore(getItemCount()));
            } else {
                holder.loadMoreEventsButton.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() - 1 ? TYPE_FOOTER : TYPE_COMMON;
    }

    void updateAdapterItems(List<EventItem> items) {
        setHasMoreEvents(items.size() > 9);
       if (mItems.size() == 0) {
           populateRecyclerView(items);
       } else {
           addMoreToRecyclerView(items);
       }
    }

    private void addMoreToRecyclerView(List<EventItem> newItems) {
        int end = mItems.size();
        mItems.addAll(end, newItems);
        int itemCount = getItemCount();
        notifyItemRangeInserted(end, itemCount - 1);
    }

    private void populateRecyclerView(List<EventItem> newItems) {
        int end = getItemCount();
        mItems.clear();
        notifyItemRangeRemoved(0, end);
        mItems.addAll(newItems);
        notifyItemRangeChanged(0, newItems.size() - 1);
    }
}

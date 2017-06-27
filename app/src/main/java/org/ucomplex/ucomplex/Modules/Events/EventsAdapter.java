package org.ucomplex.ucomplex.Modules.Events;

import android.content.Context;
import android.content.Intent;
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

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Events.model.EventItem;
import org.ucomplex.ucomplex.Modules.Subject.SubjectActivity;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import static org.ucomplex.ucomplex.Common.base.UCApplication.BASE_URL;
import static org.ucomplex.ucomplex.Common.base.UCApplication.FORMAT_JPG;
import static org.ucomplex.ucomplex.Common.base.UCApplication.PHOTOS_URL;

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

    private static final int TYPE_COMMON = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_NO_CONTENT = 2;

    static class EventViewHolder extends RecyclerView.ViewHolder {

        ImageView eventsImageView;
        TextView eventTextView;
        TextView eventTime;
        TextView eventPersonName;
        LinearLayout eventDetailsLayout;
        Button loadMoreButton;

        EventViewHolder(View view, int viewType) {
            super(view);
            if (viewType == TYPE_COMMON) {
                eventsImageView = (ImageView) view.findViewById(R.id.list_events_item_image);
                eventTextView = (TextView) view.findViewById(R.id.list_events_item_text);
                eventTime = (TextView) view.findViewById(R.id.list_events_item_date);
                eventPersonName = (TextView) view.findViewById(R.id.list_events_item_name);
                eventDetailsLayout = (LinearLayout) view.findViewById(R.id.event_details_layout);
            } else {
                loadMoreButton = (Button) view.findViewById(R.id.loadMoreButton);
            }
        }
    }

    private boolean hasMoreItems = false;
    private LoadMoreCallback<Integer> mMoreCallback;

    EventsAdapter(LoadMoreCallback<Integer> callback) {
        this.mMoreCallback = callback;
        mItems = new ArrayList<>();
    }

    private void setHasMoreItems(boolean hasMoreItems) {
        this.hasMoreItems = hasMoreItems;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            layout = viewType == TYPE_COMMON ? R.layout.item_event : R.layout.item_footer;
        }
        View view = inflater.inflate(layout, parent, false);
        return new EventViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        if (mItems.size() > 0) {
            if (getItemViewType(position) == TYPE_COMMON && mItems.size() > 0) {
                Context context = holder.eventPersonName.getContext();
                EventItem item = mItems.get(position);
                String personName = item.getName();
                if (personName == null || personName.equals("")) {
                    item.setName(context.getResources().getString(R.string.default_event_name));
                }
                holder.eventPersonName.setText(item.getName());
                holder.eventTextView.setText(item.getEventText());
                holder.eventTime.setText(FacadeCommon.makeHumanReadableDate(item.getTime()));
                Drawable textDrawable = FacadeMedia.getTextDrawable(
                        item.getParamId(),
                        item.getName(),
                        context);
                String url = BASE_URL + PHOTOS_URL + item.getCode() + FORMAT_JPG;
                Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .placeholder(textDrawable)
                        .priority(Priority.HIGH)
                        .into(holder.eventsImageView);
                holder.eventDetailsLayout.setOnClickListener(v -> {
                    if (!item.getName().equals(context.getString(R.string.app_name))) {
                        Intent intent = SubjectActivity.creteIntent(context, item.getGcourse(), item.getCourseName());
                        context.startActivity(intent);
                    }
                });
            } else if (getItemViewType(position) == TYPE_FOOTER && holder.loadMoreButton != null) {
                if (hasMoreItems) {
                    holder.loadMoreButton.setVisibility(View.VISIBLE);
                    holder.loadMoreButton.setOnClickListener(v -> mMoreCallback.loadMoreData(getItemCount() + 1));
                } else {
                    holder.loadMoreButton.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return (mItems == null || mItems.size() == 0) ? 1 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.size() > 0 && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else if(mItems.size() == 0){
            return TYPE_NO_CONTENT;
        } else {
            return TYPE_COMMON;
        }
    }

    void updateAdapterItems(List<EventItem> items, boolean internetConnected) {
        setHasMoreItems(items.size() > 9 && internetConnected);
       if (mItems.size() == 0) {
           populateRecyclerView(items);
       } else {
           addMoreToRecyclerView(items);
       }
    }
}

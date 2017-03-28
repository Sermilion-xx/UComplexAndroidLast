package org.ucomplex.ucomplex.Modules.Events;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ucomplex.ucomplex.R;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    class EventsViewHolder extends RecyclerView.ViewHolder {

        ImageView eventsImageView;
        TextView eventTextView;
        TextView eventTime;
        TextView eventPersonName;
        LinearLayout eventDetailsLayout;
        Button loadMoreEventsButton;

        public EventsViewHolder(View view) {
            super(view);
            eventsImageView = (ImageView) view.findViewById(R.id.list_events_item_image);
            eventTextView = (TextView) view.findViewById(R.id.list_events_item_text);
            eventTime = (TextView) view.findViewById(R.id.list_events_item_date);
            eventPersonName = (TextView) view.findViewById(R.id.list_events_item_name);
            eventDetailsLayout = (LinearLayout) view.findViewById(R.id.event_details_layout);
            loadMoreEventsButton = (Button) view.findViewById(R.id.loadMoreButton);
        }
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}

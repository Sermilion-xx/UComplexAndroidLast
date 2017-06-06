package org.ucomplex.ucomplex.Modules.Messenger;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.IntentCallback;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Modules.Messenger.FullscreenView.FullscreenViewActivity;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessageFileType;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerItem;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

class MessengerAdapter extends BaseAdapter<MessengerAdapter.MessengerViewHolder, List<MessengerItem>> {

    private final int TYPE_IN = 0;
    private final int TYPE_OUT = 1;

    static class MessengerViewHolder extends RecyclerView.ViewHolder {

        TextView message;
        RecyclerView filesRecyclerView;
        TextView time;

        MessengerViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.message);
            filesRecyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
            if (message != null) {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(message.getContext());
                layoutManager.setAutoMeasureEnabled(true);
                filesRecyclerView.setLayoutManager(layoutManager);
            }
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }

    private int myId;
    private String companionName;
    private OnListItemClicked<String, MessageFileType> onListItemClicked;

    MessengerAdapter(int myId, String companionName, OnListItemClicked<String, MessageFileType> onListItemClicked) {
        this.myId = myId;
        this.onListItemClicked = onListItemClicked;
        this.mItems = new ArrayList<>();
        this.companionName = companionName;
    }

    @Override
    public MessengerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            if (viewType == TYPE_IN) {
                layout = R.layout.item_messenger_in;
            } else {
                layout = R.layout.item_messenger_out;
            }
        }
        View view = inflater.inflate(layout, parent, false);
        return new MessengerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessengerViewHolder holder, int position) {
        if (mItems.size() > 0) {
            MessengerItem item = mItems.get(position);
            if (item.getMessage().length() == 0) {
                holder.message.setVisibility(View.GONE);
            } else {
                holder.message.setVisibility(View.VISIBLE);
                holder.message.setText(item.getMessage());
            }
            Context context = holder.time.getContext();
            holder.time.setText(FacadeCommon.makeHumanReadableDate(item.getTime(), true));
            MessengerMessageFilesAdapter messageFilesAdapter = new MessengerMessageFilesAdapter(
                    (params, type) -> onListItemClicked.onClick(params, type),
                    uri -> context.startActivity(FullscreenViewActivity.creteIntent(context, this.companionName, item.getTime(), uri)),
                    UCApplication.getInstance().getLoggedUser().getId());
            messageFilesAdapter.setItems(item.getFiles());
            messageFilesAdapter.notifyDataSetChanged();
            holder.filesRecyclerView.setAdapter(messageFilesAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return (mItems == null || mItems.size() == 0) ? 1 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.size() == 0) {
            return TYPE_EMPTY;
        }
        return mItems.get(position).getFrom() == myId ? TYPE_OUT : TYPE_IN;
    }
}
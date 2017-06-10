package org.ucomplex.ucomplex.Modules.Messenger;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.R;

import java.io.IOException;
import java.util.List;

class MessengerAddFileAdapterAdapter extends BaseAdapter<MessengerAddFileAdapterAdapter.MessengerAddFileAdapterViewHolder, List<Uri>> {

    static class MessengerAddFileAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        ImageView mRemove;

        MessengerAddFileAdapterViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.image);
            mRemove = (ImageView) itemView.findViewById(R.id.remove);
        }
    }

    private OnListItemClicked<Integer, Void> onListItemClicked;

    public MessengerAddFileAdapterAdapter(OnListItemClicked<Integer, Void> onListItemClicked) {
        this.onListItemClicked = onListItemClicked;
    }

    @Override
    public MessengerAddFileAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            layout = R.layout.item_messenger_add_files;
        }
        View view = inflater.inflate(layout, parent, false);
        return new MessengerAddFileAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessengerAddFileAdapterViewHolder holder, int position) {
        if (mItems.size() > 0) {
            Bitmap bitmap;
            try {
                bitmap = FacadeMedia.getBitmapFromStorage(mItems.get(position), UCApplication.getInstance(), 150);
                holder.mImage.setImageBitmap(bitmap);
                int pos = holder.getAdapterPosition();
                holder.mRemove.setOnClickListener(v -> {
                    onListItemClicked.onClick(pos, null);
                    mItems.remove(pos);
                    notifyItemRemoved(pos);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
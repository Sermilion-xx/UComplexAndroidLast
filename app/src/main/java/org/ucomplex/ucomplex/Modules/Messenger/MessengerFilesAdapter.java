package org.ucomplex.ucomplex.Modules.Messenger;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Common.interfaces.IntentCallback;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessageFile;
import org.ucomplex.ucomplex.R;

import java.io.IOException;
import java.util.List;

import static org.ucomplex.ucomplex.Common.Constants.imageFormats;
import static org.ucomplex.ucomplex.Common.base.UCApplication.BASE_FILES_URL;
import static org.ucomplex.ucomplex.Common.base.UCApplication.MESSAGE_FILES_URL;
import static org.ucomplex.ucomplex.Common.base.UCApplication.SCHEMA;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 05/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class MessengerFilesAdapter extends BaseAdapter<MessengerFilesAdapter.MessengerMessageFilesViewHolder, List<MessageFile>> {

    private final static int TYPE_FILE = 0;
    private final static int TYPE_IMAGE = 1;

    static class MessengerMessageFilesViewHolder extends RecyclerView.ViewHolder {

        TextView fileName;
        ImageView attachment;
        View darkLayer;
        ImageView download;
        ProgressBar progressBar;

        public MessengerMessageFilesViewHolder(View itemView, int viewType) {
            super(itemView);
            fileName = (TextView) itemView.findViewById(R.id.file_name);
            attachment = (ImageView) itemView.findViewById(R.id.attachment);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            if (viewType == TYPE_IMAGE) {
                darkLayer = itemView.findViewById(R.id.dark_image_layer);
                download = (ImageView) itemView.findViewById(R.id.download);
            }
        }
    }

    private OnListItemClicked<String, String> onListItemClicked;
    private IntentCallback<String> intentCallback;
    private int myId;

    public MessengerFilesAdapter(OnListItemClicked<String, String> onListItemClicked,
                                 IntentCallback<String> intentCallback,
                                 int myId) {
        this.onListItemClicked = onListItemClicked;
        this.intentCallback = intentCallback;
        this.myId = myId;
    }

    @Override
    public MessengerMessageFilesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = -1;
        if (viewType == TYPE_FILE) {
            layout = R.layout.item_message_file;
        } else if (viewType == TYPE_IMAGE) {
            layout = R.layout.item_message_image;
        }
        View view = inflater.inflate(layout, parent, false);
        return new MessengerMessageFilesViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(MessengerMessageFilesViewHolder holder, int position) {
        MessageFile item = mItems.get(position);
        if (getItemViewType(position) == TYPE_FILE) {
            holder.fileName.setText(item.getName());
            holder.attachment.setImageResource(R.drawable.ic_file);
            holder.fileName.setOnClickListener(v -> onListItemClicked.onClick(item.getAddress(), item.getName()));
        } else if (getItemViewType(position) == TYPE_IMAGE) {
            Context context = holder.attachment.getContext();
            if (item.getFileUri() != null) {
                try {
                    holder.attachment.setImageBitmap(FacadeMedia.getBitmapFromStorage(item.getFileUri(), context));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                holder.attachment.setImageResource(R.drawable.ic_image);
            }
            if (item.isDownloaded()) {
                downloadImage(holder, holder.progressBar, item, holder.attachment.getContext());
            }
            holder.attachment.setEnabled(false);
            holder.attachment.setOnClickListener(v -> {
                Bitmap bitmap = FacadeMedia.drawableToBitmap(holder.attachment.getDrawable());
                String bitmapUri = FacadeMedia.saveBitmapToStorage(
                        context.getContentResolver(),
                        bitmap,
                        item.getAddress(),
                        "");
                intentCallback.startIntent(bitmapUri);
            });
            holder.download.setOnClickListener(v -> downloadImage(holder, holder.progressBar, item, holder.attachment.getContext()));
        }
    }

    private String extractExtension(String address) {
        String[] nameComponents = address.split("\\.");
        if (nameComponents.length > 1) {
            return nameComponents[1];
        }
        return "";
    }

    private void downloadImage(MessengerMessageFilesViewHolder holder, ProgressBar progressBar, MessageFile item, Context context) {
        progressBar.setVisibility(View.VISIBLE);
        String url = SCHEMA + BASE_FILES_URL + MESSAGE_FILES_URL + item.getFrom() + "/" + item.getAddress();
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.ic_image)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .priority(Priority.HIGH)
                .listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                holder.darkLayer.setVisibility(View.GONE);
                holder.download.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                holder.darkLayer.setVisibility(View.GONE);
                holder.download.setVisibility(View.GONE);
                holder.attachment.setEnabled(true);
                item.setDownloaded(true);
                return false;
            }
        }).into(holder.attachment);
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.size() == 0) {
            return TYPE_EMPTY;
        }
        String extension = extractExtension(mItems.get(position).getAddress());
        if (imageFormats.contains(extension)) {
            return TYPE_IMAGE;
        }
        return TYPE_FILE;
    }
}

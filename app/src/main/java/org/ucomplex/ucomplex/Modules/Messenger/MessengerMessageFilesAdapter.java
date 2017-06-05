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

import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessageFile;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessageFileType;
import org.ucomplex.ucomplex.R;

import java.util.List;

import static org.ucomplex.ucomplex.Common.Constants.imageFormats;
import static org.ucomplex.ucomplex.Common.base.UCApplication.BASE_URL;
import static org.ucomplex.ucomplex.Common.base.UCApplication.FORMAT_JPG;
import static org.ucomplex.ucomplex.Common.base.UCApplication.MESSAGE_FILES_URL;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 05/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class MessengerMessageFilesAdapter extends BaseAdapter<MessengerMessageFilesAdapter.MessengerMessageFilesViewHolder, List<MessageFile>> {

    static class MessengerMessageFilesViewHolder extends RecyclerView.ViewHolder {

        TextView fileName;
        ImageView attachment;
        ProgressBar progressBar;

        public MessengerMessageFilesViewHolder(View itemView) {
            super(itemView);
            fileName = (TextView) itemView.findViewById(R.id.file_name);
            attachment = (ImageView) itemView.findViewById(R.id.attachment);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    private OnListItemClicked<String, MessageFileType> onListItemClicked;

    public MessengerMessageFilesAdapter(OnListItemClicked<String, MessageFileType> onListItemClicked) {
        this.onListItemClicked = onListItemClicked;
    }

    @Override
    public MessengerMessageFilesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_message_file, parent, false);
        return new MessengerMessageFilesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessengerMessageFilesViewHolder holder, int position) {
        MessageFile item = mItems.get(position);
        String[] nameComponents = item.getAddress().split("\\.");
        String address = item.getFrom() + "/" + item.getAddress();
        if (nameComponents.length > 1) {
            if (imageFormats.contains(nameComponents[1])) {
                downloadImage(holder.attachment, holder.progressBar, item, holder.attachment.getContext());
                onListItemClicked.onClick(address, MessageFileType.IMAGE);
            } else {
                holder.fileName.setText(item.getName());
                holder.attachment.setImageResource(R.drawable.ic_file);
                onListItemClicked.onClick(address, MessageFileType.FILE);
            }
        }
    }

    private void downloadImage(ImageView attachment, ProgressBar progressBar, MessageFile item, Context context) {
        progressBar.setVisibility(View.VISIBLE);
        String url =  MESSAGE_FILES_URL + item.getFrom() +"/" + item.getAddress();
        Glide.with(context)
                .load(url)
                .asBitmap()
                .priority(Priority.HIGH).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(attachment);
    }
}

package org.ucomplex.ucomplex.Modules.MessagesList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.IntentCallback;
import org.ucomplex.ucomplex.Domain.users.UserInterface;
import org.ucomplex.ucomplex.Modules.MessagesList.model.MessagesListItem;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

class MessagesListAdapter extends BaseAdapter<MessagesListAdapter.MessagesListViewHolder, List<MessagesListItem>> {

    static class MessagesListViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profileImage;
        TextView name;
        TextView time;
        CircleImageView lastProfile;
        TextView lastMessage;
        ViewGroup clickArea;

        MessagesListViewHolder(View itemView) {
            super(itemView);
            profileImage = (CircleImageView) itemView.findViewById(R.id.profile_image);
            name = (TextView) itemView.findViewById(R.id.user_name);
            time = (TextView) itemView.findViewById(R.id.time);
            lastProfile = (CircleImageView) itemView.findViewById(R.id.list_profile);
            lastMessage = (TextView) itemView.findViewById(R.id.last_message);
            clickArea =   (ViewGroup) itemView.findViewById(R.id.clickArea);
        }
    }

    private IntentCallback<Integer> intentCallback;

    MessagesListAdapter(IntentCallback<Integer> intentCallback) {
        this.intentCallback = intentCallback;
        this.mItems = new ArrayList<>();
    }


    @Override
    public MessagesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size(), FacadeCommon.NoContentLayoutType.MESSAGES);
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            layout = R.layout.item_messages_list;
        }
        View view = inflater.inflate(layout, parent, false);
        return new MessagesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessagesListViewHolder holder, int position) {
        if (mItems.size() > 0) {
            MessagesListItem item = mItems.get(position);
            Context context = holder.profileImage.getContext();
            UserInterface me = UCApplication.getInstance().getLoggedUser();
            setImage(holder, context, item, me);
            holder.name.setText(item.getName());
            holder.lastMessage.setText(item.getMessage());
            holder.time.setText(FacadeCommon.makeHumanReadableDate(item.getTime(), true));
            if (item.getStatus() == 0 && item.getFrom() != me.getId()) {
                holder.clickArea.setBackgroundResource(R.color.colorNotSeen);
            }
            holder.clickArea.setOnClickListener(v -> intentCallback.startIntent(item.getCompanion()));
        }
    }

    private void setImage(MessagesListViewHolder holder,
                          Context context,
                          MessagesListItem item, UserInterface user) {
        if (item.getPhoto() == 1) {
            String url = UCApplication.PHOTOS_URL + item.getCode() + ".jpg";
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .priority(Priority.HIGH).listener(new RequestListener<String, Bitmap>() {
                @Override
                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    if (item.getFrom() != user.getId()) {
                        holder.lastProfile.setImageBitmap(resource);
                    } else {
                        if (user.getPhoto() == 1) {
                            Glide.with(context)
                                    .load(url)
                                    .asBitmap()
                                    .priority(Priority.HIGH)
                                    .into(holder.lastProfile);
                        } else {
                            holder.lastProfile.setImageDrawable(FacadeMedia.getTextDrawable(user.getId(), user.getName(), context));
                        }
                    }
                    return false;
                }
            }).into(holder.profileImage);
        } else {
            holder.profileImage.setImageDrawable(FacadeMedia.getTextDrawable(item.getFrom(), item.getName(), context));
        }
    }

    @Override
    public int getItemCount() {
        return (mItems == null || mItems.size() == 0) ? 1 : mItems.size();
    }


}

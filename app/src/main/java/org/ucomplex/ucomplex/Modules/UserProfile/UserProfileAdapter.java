package org.ucomplex.ucomplex.Modules.UserProfile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Domain.Users.BlackList;
import org.ucomplex.ucomplex.Modules.UserProfile.model.ProfileRequestType;
import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileItem;
import org.ucomplex.ucomplex.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.ucomplex.ucomplex.Common.base.UCApplication.FORMAT_JPG;
import static org.ucomplex.ucomplex.Common.base.UCApplication.PHOTOS_ORIGINAL_URL;
import static org.ucomplex.ucomplex.Common.base.UCApplication.PHOTOS_URL;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 03/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UserProfileAdapter extends BaseAdapter<UserProfileAdapter.UserProfileViewHolder, List<UserProfileItem>> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_INFO = 1;

    static class UserProfileViewHolder extends RecyclerView.ViewHolder {

        CircleImageView mProfileImage;
        ImageView mBlockButton;
        TextView mName;
        CircleImageView mRole;
        Button mMessageButton;
        Button mFriendButton;

        TextView mInfoKey;
        TextView mInfoValue;

        public UserProfileViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == TYPE_HEADER) {
                mFriendButton =  (Button) itemView.findViewById(R.id.add_friend_button);
                mMessageButton = (Button) itemView.findViewById(R.id.message_button);
                mProfileImage =  (CircleImageView) itemView.findViewById(R.id.profile_picture);
                mName =          (TextView) itemView.findViewById(R.id.name);
                mBlockButton =   (ImageView) itemView.findViewById(R.id.block);
            } else if (viewType == TYPE_INFO) {
                mInfoKey =       (TextView) itemView.findViewById(R.id.profile_role_key);
                mInfoValue =     (TextView) itemView.findViewById(R.id.profile_role_value);
                mRole =          (CircleImageView) itemView.findViewById(R.id.profile_role_icon);
            }
        }
    }

    private OnListItemClicked<Object, ProfileRequestType> onListItemClicked;

    public UserProfileAdapter(OnListItemClicked<Object, ProfileRequestType> onListItemClicked) {
        this.onListItemClicked = onListItemClicked;
    }

    @Override
    public UserProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            if (viewType == TYPE_HEADER) {
                layout = R.layout.item_profile_header;
            } else if (viewType == TYPE_INFO) {
                layout = R.layout.item_profile_role;
            }
        }
        View view = inflater.inflate(layout, parent, false);
        return new UserProfileViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(UserProfileViewHolder holder, int position) {
        if (mItems.size() > 0) {
            UserProfileItem item = mItems.get(position);
            if (getItemViewType(position) == TYPE_HEADER) {
                Context context = holder.mName.getContext();
                holder.mName.setText(item.getPersonName());
                BlackList blackList = item.getBlocked();

                updateBlackListButton(holder.mBlockButton, blackList.is_black());
                updateFriendButton(holder.mFriendButton, item.getFriend().is_friend(), context);

                Drawable textDrawable = FacadeMedia.getTextDrawable(
                        item.getId(), item.getPersonName(), context);
                String url = PHOTOS_URL + item.getCode() + FORMAT_JPG;
                Glide.with(context).load(url).asBitmap().placeholder(textDrawable)
                        .priority(Priority.HIGH).into(holder.mProfileImage);

                holder.mBlockButton.setOnClickListener(v -> {
                    item.setBlocked(!item.getBlocked().is_black());
                    boolean blocked = item.getBlocked().is_black();
                    if (blocked) {
                        onListItemClicked.onClick(item.getId(), ProfileRequestType.BLOCK);
                    } else {
                        onListItemClicked.onClick(item.getId(), ProfileRequestType.UNBLOCK);
                    }
                    updateBlackListButton(holder.mBlockButton, blackList.is_black());
                });
                holder.mMessageButton.setOnClickListener(v -> {

                });
                holder.mFriendButton.setOnClickListener(v -> {
                    item.setFriend(!item.getFriend().is_friend());
                    updateFriendButton(holder.mFriendButton, item.getFriend().is_friend(), context);
                    onListItemClicked.onClick(item.getId(), ProfileRequestType.FRIEND);
                });
                holder.mProfileImage.setOnClickListener(v -> {
                    String originalUrl = PHOTOS_ORIGINAL_URL + item.getCode() + FORMAT_JPG;
                    onListItemClicked.onClick(originalUrl, ProfileRequestType.PHOTO);
                });
            } else if (getItemViewType(position) == TYPE_INFO) {
                holder.mInfoKey.setText(item.getPositionName());
                holder.mInfoValue.setText(item.getDisciplineName());
            }
        }
    }

    void revertChanges(ProfileRequestType requestType) {
        UserProfileItem item = mItems.get(0);
        switch (requestType) {
            case FRIEND:
                item.setFriend(!item.getFriend().is_friend());
                break;
            case BLOCK:
                item.setBlocked(!item.getBlocked().is_black());
                break;
            case UNBLOCK:
                item.setBlocked(!item.getBlocked().is_black());
                break;
        }
        notifyItemChanged(0);
    }

    private void updateBlackListButton(ImageView view, boolean blocked) {
        if (blocked) {
            view.setImageResource(R.drawable.ic_lock);
        } else {
            view.setImageResource(R.drawable.ic_unlock);
        }
    }

    private void updateFriendButton(Button view, boolean friend, Context context) {
        if (!friend) {
            view.setText(context.getText(R.string.add_to_friends));
            view.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_as_friend, 0, 0, 0);
        } else {
            view.setText(context.getText(R.string.remove_friend));
            view.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_remove_friend, 0, 0, 0);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_INFO;
    }
}

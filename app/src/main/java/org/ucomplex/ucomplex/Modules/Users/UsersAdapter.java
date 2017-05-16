package org.ucomplex.ucomplex.Modules.Users;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.utility.CircleTransform;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import static org.ucomplex.ucomplex.Common.base.UCApplication.PHOTOS_URL;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 13/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UsersAdapter extends BaseAdapter<UsersAdapter.UsersViewHolder, List<User>> {

    public static final int TYPE_USER = 0;
    public static final int TYPE_FOOTER = 1;
    private static final int TYPE_EMPTY = 2;
    private static final int TYPE_REQUESTED = 3;

    static class UsersViewHolder extends RecyclerView.ViewHolder {

        private ImageView mProfileImage;
        private TextView mName;
        private TextView mType;
        private Button mLoadMore;
        private RelativeLayout mClickArea;

        UsersViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == TYPE_USER || viewType == TYPE_REQUESTED) {
                mProfileImage = (ImageView) itemView.findViewById(R.id.profileImage);
                mName = (TextView) itemView.findViewById(R.id.name);
                mType = (TextView) itemView.findViewById(R.id.type);
                mClickArea = (RelativeLayout) itemView.findViewById(R.id.clickArea);
            } else if (viewType == TYPE_FOOTER) {
                mLoadMore = (Button) itemView.findViewById(R.id.loadMoreButton);
                System.out.println();
            }
        }
    }

    private boolean hasMoreItems;
    private OnListItemClicked<Integer, Integer> onListItemClicked;
    private boolean[] friendRequested;

    public void setOnListItemClicked(OnListItemClicked<Integer, Integer> onListItemClicked) {
        this.onListItemClicked = onListItemClicked;
    }

    public UsersAdapter() {
        mItems = new ArrayList<>();
    }

    public void addItems(List<User> users) {
        if (users !=null) {
            if (mItems.size() == 0) {
                mItems = users;
                hasMoreItems = users.size() == 21;
                notifyDataSetChanged();
            } else {
                int oldEnd = mItems.size();
                mItems.addAll(users);
                hasMoreItems = users.size() == 21;
                notifyItemRangeInserted(oldEnd, mItems.size() - 1);
            }
            friendRequested = new boolean[mItems.size()];
            for (int i = 0; i < mItems.size(); i++) {
                if (mItems.get(i).isFriend()) {
                    friendRequested[i] = true;
                }
            }
        }
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            layout = viewType == TYPE_USER || viewType == TYPE_REQUESTED ? R.layout.item_users : R.layout.item_footer;
        }
        View view = inflater.inflate(layout, parent, false);
        return new UsersViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        if (mItems.size() > 0) {
            if (getItemViewType(position) == TYPE_USER || getItemViewType(position) == TYPE_REQUESTED) {
                Context context = holder.mName.getContext();
                User user = mItems.get(position);
                processImageView(holder, user, context);
                holder.mName.setText(user.getName());
                holder.mType.setText(FacadeCommon.getStringUserType(context, user.getType()));
                if (getItemViewType(position) == TYPE_REQUESTED) {
                    holder.mClickArea.setBackgroundResource(R.color.colorFriendRequested);
                }
                holder.mClickArea.setOnClickListener(v -> onListItemClicked.onClick(user.getId(), TYPE_USER));
            } else if (getItemViewType(position) == TYPE_FOOTER) {
                holder.mLoadMore.setOnClickListener(v -> onListItemClicked.onClick(mItems.size(), TYPE_FOOTER));
            }
        }
    }

    private void processImageView(UsersViewHolder aHolder, User item, Context context) {
        if (item.getPhoto() == 0) {
            Drawable textDrawable = FacadeMedia.getTextDrawable(item.getId(),
                    item.getName(), context);
            aHolder.mProfileImage.setImageDrawable(textDrawable);
        } else {
            Glide.with(context)
                    .load(PHOTOS_URL + item.getCode() + ".jpg")
                    .priority(Priority.HIGH)
                    .centerCrop()
                    .placeholder(R.drawable.ic_ring)
                    .transform(new CircleTransform(context))
                    .into(aHolder.mProfileImage);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems == null || mItems.size() == 0) {
            return TYPE_EMPTY;
        } else if (position == getItemCount() - 1 && hasMoreItems) {
            return TYPE_FOOTER;
        } else if (friendRequested[position]) {
            return TYPE_REQUESTED;
        }
        return TYPE_USER;
    }

    @Override
    public int getItemCount() {
        return mItems == null || mItems.size() == 0 ? 1 : mItems.size();
    }

}

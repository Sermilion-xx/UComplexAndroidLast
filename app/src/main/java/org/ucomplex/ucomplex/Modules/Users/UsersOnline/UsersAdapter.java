package org.ucomplex.ucomplex.Modules.Users.UsersOnline;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.ucomplex.ucomplex.Common.UCApplication.PHOTOS_URL;

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

    private static final int TYPE_USER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_EMPTY = 2;

    static class UsersViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView mProfileImage;
        private TextView mName;
        private TextView mType;
        private Button mMenuButton;
        private Button mLoadMore;
        private RelativeLayout mClickArea;

        UsersViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == TYPE_USER) {
                mProfileImage = (CircleImageView) itemView.findViewById(R.id.profileImage);
                mName = (TextView) itemView.findViewById(R.id.name);
                mType = (TextView) itemView.findViewById(R.id.type);
                mMenuButton = (Button) itemView.findViewById(R.id.menu_button);
                mClickArea = (RelativeLayout) itemView.findViewById(R.id.clickArea);
            } else if (viewType == TYPE_FOOTER) {
                mLoadMore = (Button) itemView.findViewById(R.id.loadMoreButton);
            }
        }
    }

    public UsersAdapter() {
        mItems = new ArrayList<>();
    }

    private boolean hasMoreItems;

    public void addItems(List<User> users) {
        int oldEnd = mItems.size();
        mItems.addAll(users);
        if (users.size() < 21) {
            hasMoreItems = false;
        }
        notifyItemRangeInserted(oldEnd, mItems.size());
        notifyItemRangeChanged(oldEnd, mItems.size());

    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size(), parent.getContext());
        if (layout == 0) {
            layout = viewType == TYPE_USER ? R.layout.item_users : R.layout.item_footer;
        }
        View view = inflater.inflate(layout, parent, false);
        return new UsersViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        if (mItems.size() > 0) {
            if (getItemViewType(position) != TYPE_EMPTY) {
                Context context = holder.mName.getContext();
                User user = mItems.get(position);
                processImageView(holder, user, context);
                holder.mName.setText(user.getName());
                holder.mType.setText(FacadeCommon.getStringUserType(context, user.getType()));
                holder.mMenuButton.setOnClickListener(v -> {

                });
            }
        }
    }

    private void processImageView(UsersViewHolder aHolder, User item, Context context) {
        Drawable textDrawable = FacadeMedia.getTextDrawable(item.getId(),
                item.getName(), context);
        if (item.getPhoto() == 0) {
            aHolder.mProfileImage.setImageDrawable(textDrawable);
        } else {
            Glide.with(context)
                    .load(PHOTOS_URL + item.getCode() + ".jpg")
                    .priority(Priority.HIGH)
                    .placeholder(R.drawable.ic_ring)
                    .into(aHolder.mProfileImage);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems == null || mItems.size() == 0) {
            return TYPE_EMPTY;
        } else if (position == getItemCount() - 1 && hasMoreItems) {
            return TYPE_FOOTER;
        }
        return TYPE_USER;
    }

    @Override
    public int getItemCount() {
        return mItems == null || mItems.size() == 0 ? 1 : mItems.size();
    }
}

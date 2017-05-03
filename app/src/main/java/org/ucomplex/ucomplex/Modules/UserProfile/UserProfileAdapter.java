package org.ucomplex.ucomplex.Modules.UserProfile;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileItem;
import org.ucomplex.ucomplex.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
        ImageView mBlock;
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
                mBlock =         (ImageView) itemView.findViewById(R.id.block);
            } else if (viewType == TYPE_INFO) {
                mInfoKey =       (TextView) itemView.findViewById(R.id.profile_role_key);
                mInfoValue =     (TextView) itemView.findViewById(R.id.profile_role_value);
                mRole =          (CircleImageView) itemView.findViewById(R.id.profile_role_icon);
            }
        }
    }

    @Override
    public UserProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(UserProfileViewHolder holder, int position) {

    }



}

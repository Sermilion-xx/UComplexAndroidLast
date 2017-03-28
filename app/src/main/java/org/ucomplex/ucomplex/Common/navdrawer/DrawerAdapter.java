package org.ucomplex.ucomplex.Common.navdrawer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.FacadePreferences;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Modules.Login.LoginActivity;
import org.ucomplex.ucomplex.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private static final int TYPE_0 = 0;
    private static final int TYPE_1 = 1;
    private static final String PROFILE_IMAGE_URL = "https://ucomplex.org/files/photos/";
    private List<DrawerListItem> mItems;
    private Activity mContext;

    public DrawerAdapter(List<DrawerListItem> items, Activity context) {
        mItems = items;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = viewType == 0 ? R.layout.item_menu_header : R.layout.item_menu;
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(inflatedView, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_0 : TYPE_1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DrawerListItem row = mItems.get(position);
        holder.bindListRow(row);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private ImageView mIconImageView;
        private TextView mTextView1;
        private TextView mTextView2;
        private CircleImageView mProfileImageView;
        private CircleImageView mRolesImageView;

        ViewHolder(View view, int viewType) {
            super(view);
            if (viewType == 0) {
                mTextView1 = (TextView) view.findViewById(R.id.name);
                mTextView2 = (TextView) view.findViewById(R.id.role);
                mProfileImageView = (CircleImageView) view.findViewById(R.id.profileImage);
                mRolesImageView = (CircleImageView) view.findViewById(R.id.roleImage);
            } else {
                mIconImageView = (ImageView) view.findViewById(R.id.icon);
                mTextView1 = (TextView) view.findViewById(R.id.title);
            }
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        void bindListRow(DrawerListItem row) {
            if (getItemViewType() == 0) {
                mTextView1.setText(row.getTitle1());
                mTextView2.setText(row.getTitle2());
                if (row.getProfileBitmapCode() == null) {
                    Drawable textDrawable = FacadeMedia.getTextDrawable(row.getId(), row.getTitle1(), mContext);
                    mProfileImageView.setImageBitmap(FacadeMedia.drawableToBitmap(textDrawable));
                } else {
                    if (row.getProfileBitmapCode() != null) {
                        String url = PROFILE_IMAGE_URL + row.getProfileBitmapCode() + ".jpg";
                        Glide.with(mContext)
                                .load(url)
                                .asBitmap()
                                .priority(Priority.HIGH)
                                .into(mProfileImageView);
                    } else {
                        Drawable textDrawable = FacadeMedia.getTextDrawable(row.getId(), row.getTitle1(), mContext);
                        mProfileImageView.setImageBitmap(FacadeMedia.drawableToBitmap(textDrawable));
                    }
                }
            } else {
                mTextView1.setText(row.getTitle1());
                mIconImageView.setImageResource(row.getIcon());
            }
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position == getItemCount() - 1) {
                logout();
            } else if (position == 0) {
                //TODO: go to profile
            } else if (position == 1) {
//                onDrawerItemPressed(EventsActivity.class);
//                Intent intent = new Intent(Constants.EVENTS_REFRESH_BROADCAST);
//                intent.putExtra(EventsActivity.ACTION_RELOAD_EVENTS, true);
//                mContext.sendBroadcast(intent);
            } else if (position == 2) {
//                onDrawerItemPressed(SubjectsListActivity.class);
            } else if (position == 3) {
//                onDrawerItemPressed(MaterialsActivity.class);
            } else if (position == 4) {
//                onDrawerItemPressed(UsersActivity.class);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

    public void logout() {
        FacadePreferences.clearPref(mContext);
        UCApplication.getInstance().setLoggedUser(null);
        UCApplication.getInstance().setAuthString(null);
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
        mContext.finish();
    }

    private void onDrawerItemPressed(Class<? extends Activity> activity) {
        if (!(mContext.getClass() == activity)) {
            mContext.startActivity(new Intent(mContext, activity));
        } else {
            mContext.onBackPressed();
        }
    }
}

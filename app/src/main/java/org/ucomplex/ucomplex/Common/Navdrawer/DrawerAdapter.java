package org.ucomplex.ucomplex.Common.Navdrawer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.FacadePreferences;
import org.ucomplex.ucomplex.Common.NewMessageBroadcastReceiver;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarActivity;
import org.ucomplex.ucomplex.Modules.Events.EventsActivity;
import org.ucomplex.ucomplex.Modules.Login.LoginActivity;
import org.ucomplex.ucomplex.Modules.MessagesList.MessagesListActivity;
import org.ucomplex.ucomplex.Modules.Portfolio.PortfolioActivity;
import org.ucomplex.ucomplex.Modules.SubjectsList.SubjectsListActivity;
import org.ucomplex.ucomplex.Modules.UserProfile.UserProfileActivity;
import org.ucomplex.ucomplex.Modules.Users.UsersActivity;
import org.ucomplex.ucomplex.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.ucomplex.ucomplex.Common.Constants.UC_ACTION_NEW_MESSAGE;
import static org.ucomplex.ucomplex.Modules.Updates.UpdatesService.MESSAGE_COUNT;


public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_MESSAGE = 2;
    private static final String PROFILE_IMAGE_URL = UCApplication.PHOTOS_URL;
    private static final int MESSAGES_ADAPTER_POSITION = 5;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIconImageView;
        private TextView mTextView1;
        private TextView mTextView2;
        private CircleImageView mProfileImageView;
        private CircleImageView mMessageCount;

        ViewHolder(View view, int viewType) {
            super(view);
            if (viewType == TYPE_HEADER) {
                mTextView1 = (TextView) view.findViewById(R.id.name);
                mTextView2 = (TextView) view.findViewById(R.id.role);
                mProfileImageView = (CircleImageView) view.findViewById(R.id.profileImage);
            } else if (viewType == TYPE_MESSAGE) {
                mIconImageView = (ImageView) view.findViewById(R.id.icon);
                mTextView1 = (TextView) view.findViewById(R.id.title);
                mMessageCount = (CircleImageView) view.findViewById(R.id.messageCount);
            } else {
                mTextView1 = (TextView) view.findViewById(R.id.title);
                mIconImageView = (ImageView) view.findViewById(R.id.icon);
            }
        }
    }

    private List<DrawerListItem> mItems;
    private Activity mContext;
    private NewMessageBroadcastReceiver receiver = new NewMessageBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(UC_ACTION_NEW_MESSAGE)) {
                int count = intent.getIntExtra(MESSAGE_COUNT, 0);
                if (count > 0 && mItems.get(MESSAGES_ADAPTER_POSITION).getNotificationCount() != count) {
                    mItems.get(MESSAGES_ADAPTER_POSITION).setNotificationCount(count);
                    notifyItemChanged(MESSAGES_ADAPTER_POSITION);
                }
            }
        }
    };


    public DrawerAdapter(List<DrawerListItem> items, Activity context) {
        mItems = items;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = viewType == TYPE_HEADER ? R.layout.item_menu_header : R.layout.item_menu;
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(inflatedView, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == MESSAGES_ADAPTER_POSITION) {
            return TYPE_MESSAGE;
        }
        return position == 0 ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DrawerListItem row = mItems.get(position);
        bindListRow(row, holder, position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private void bindListRow(DrawerListItem item, ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_ITEM) {
            setupMenuItem(item, holder, position);
        } else if (viewType == TYPE_MESSAGE) {
            setupMenuItem(item, holder, position);
            if (item.getNotificationCount() > 0) {
                TextDrawable drawable = TextDrawable.builder().beginConfig()
                        .height(20)
                        .width(20)
                        .endConfig()
                        .buildRect(String.valueOf(item.getNotificationCount()), ContextCompat.getColor(mContext, R.color.colorPrimary));
                holder.mMessageCount.setVisibility(View.VISIBLE);
                holder.mMessageCount.setImageDrawable(drawable);
            } else {
                holder.mMessageCount.setVisibility(View.GONE);
            }
        } else {
            setupItem(item, holder);
        }
    }

    private void setupMenuItem(DrawerListItem item, ViewHolder holder, int position) {
        holder.mTextView1.setText(item.getTitle1());
        holder.mIconImageView.setImageResource(item.getIcon());
        holder.mTextView1.setOnClickListener(v ->
                setupOnClick(position)
        );
    }

    private void setupOnClick(int position) {
        if (position == getItemCount() - 1) {
            logout();
        } else if (position == 0) {
            mContext.startActivity(UserProfileActivity.creteIntent(mContext, UCApplication.getInstance().getLoggedUser().getPerson()));
        } else if (position == 1) {
            mContext.startActivity(EventsActivity.creteRefreshIntent(mContext));
        } else if (position == 2) {
            mContext.startActivity(SubjectsListActivity.creteIntent(mContext));
        } else if (position == 3) {
            mContext.startActivity(PortfolioActivity.creteIntent(mContext));
        } else if (position == 4) {
            mContext.startActivity(UsersActivity.creteIntent(mContext));
        } else if (position == 5) {
            mContext.startActivity(MessagesListActivity.creteIntent(mContext));
        } else if (position == 6) {
            mContext.startActivity(CalendarActivity.creteIntent(mContext));
        }
    }

    public void resetNotification() {
        mItems.get(5).setNotificationCount(NewMessageBroadcastReceiver.getMessageCount());
        notifyItemChanged(5);
        NewMessageBroadcastReceiver.setMessageSeen(true);
    }

    private void setupItem(DrawerListItem row, ViewHolder holder) {
        holder.mTextView1.setText(row.getTitle1());
        holder.mTextView2.setText(row.getTitle2());
        if (row.getProfileBitmapCode() == null) {
            Drawable textDrawable = FacadeMedia.getTextDrawable(row.getId(), row.getTitle1(), mContext);
            holder.mProfileImageView.setImageBitmap(FacadeMedia.drawableToBitmap(textDrawable));
        } else {
            if (row.getProfileBitmapCode() != null) {
                String url = PROFILE_IMAGE_URL + row.getProfileBitmapCode() + ".jpg";
                Glide.with(mContext)
                        .load(url)
                        .asBitmap()
                        .priority(Priority.HIGH)
                        .into(holder.mProfileImageView);
            } else {
                Drawable textDrawable = FacadeMedia.getTextDrawable(row.getId(), row.getTitle1(), mContext);
                holder.mProfileImageView.setImageDrawable(textDrawable);
            }
        }
    }

    private void logout() {
        FacadePreferences.clearPref(mContext);
        UCApplication.getInstance().setLoggedUser(null);
        UCApplication.getInstance().setAuthString(null);
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
        mContext.finish();
    }


    public void onStart() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UC_ACTION_NEW_MESSAGE);
        mContext.registerReceiver(receiver, filter);
    }

    public void onStop() {
        mContext.unregisterReceiver(receiver);
    }
}

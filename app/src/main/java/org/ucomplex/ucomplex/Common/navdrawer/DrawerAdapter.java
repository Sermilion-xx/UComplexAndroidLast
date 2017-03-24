package org.ucomplex.ucomplex.Common.navdrawer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;


public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_ITEM_SEPARATOR = 2;
    private ArrayList<DrawerListItem> mItems;
    private Activity mContext;

    public DrawerAdapter(ArrayList<DrawerListItem> items, Activity context) {
        mItems = items;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = -1; //R.layout.item_separator;
//        if (viewType == TYPE_HEADER) {
//            layout = R.layout.item_menu_header;
//        } else if (viewType == TYPE_ITEM) {
//            layout = R.layout.item_menu;
//        }
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(inflatedView, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == 5) {
            return TYPE_ITEM_SEPARATOR;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DrawerListItem row = mItems.get(position);
        if (getItemViewType(holder.getAdapterPosition()) == TYPE_HEADER) {
//            List<ProfileItem> userItem = OneApplication.getSharedData();
//            ProfileItem firstName = userItem.get(POSITION_NAME);
//            ProfileItem photo = userItem.get(POSITION_PHOTO);
//            holder.mTitle.setText(firstName.getValue());
//            if(!photo.getValue().equals("")) {
//                Glide.with(mContext)
//                        .load(row.getIcon())
//                        .asBitmap()
//                        .priority(Priority.HIGH)
//                        .into(holder.mIconImageView);
//            }
        } else if(getItemViewType(holder.getAdapterPosition()) == TYPE_ITEM) {
            holder.mTitle.setText(row.getTitle1());
            holder.mItemImageView.setImageResource(mItems.get(position).getIcon());
        }
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private CircleImageView mIconImageView;
        private ImageView mItemImageView;
        private TextView mTitle;

        ViewHolder(View view, int viewType) {
            super(view);
            if (viewType == TYPE_HEADER) {
                mIconImageView = (CircleImageView) view.findViewById(R.id.icon);
            } else if (viewType == TYPE_ITEM) {
                mItemImageView = (ImageView) view.findViewById(R.id.icon);
            }
            mTitle = (TextView) view.findViewById(R.id.title);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
//            if (position == getItemCount() - 1) {
//
//            } else if (position == 0) {
//                mContext.startActivity(new Intent(mContext, ProfileActivity.class));
//            } else if (position == 1) {
//                mContext.startActivity(new Intent(mContext, HistoryActivity.class));
//            } else if (position == 2) {
//                mContext.startActivity(new Intent(mContext, RequestsActivity.class));
//            } else if (position == 3) {
//                mContext.startActivity(new Intent(mContext, ThirdPartyActivity.class));
//            } else if (position == 4) {
//
//            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

    private void onDrawerItemPressed(Class<? extends Activity> activity) {
        if (!(mContext.getClass() == activity)) {
            mContext.startActivity(new Intent(mContext, activity));
        } else {
            mContext.onBackPressed();
        }
    }
}

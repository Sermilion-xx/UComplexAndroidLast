package org.ucomplex.ucomplex.Modules.RoleSelect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.interfaces.IntentCallback;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 26/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleSelectAdapter extends RecyclerView.Adapter<RoleSelectAdapter.RoleSelectViewHolder> {

    static class RoleSelectViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView title;

        public RoleSelectViewHolder(View itemView) {
            super(itemView);
            image = (CircleImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    private List<RoleItem> mItems;
    private IntentCallback<Integer> mCallback;

    public void setItems(List<RoleItem> mItems) {
        this.mItems = mItems;
    }

    public RoleSelectAdapter (IntentCallback<Integer> callback) {
        this.mCallback = callback;
        mItems = new ArrayList<>();
    }

    @Override
    public RoleSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewRow = inflater.inflate(R.layout.item_role, parent, false);
        return new RoleSelectViewHolder(viewRow);
    }

    @Override
    public void onBindViewHolder(RoleSelectViewHolder holder, int position) {
        RoleItem item = mItems.get(position);
        holder.image.setImageResource(item.getRoleIcon());
        holder.title.setText(item.getRoleName());
        holder.image.setOnClickListener(v -> mCallback.startIntent(position));
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }
}

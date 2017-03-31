package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectItemFile;
import org.ucomplex.ucomplex.R;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 31/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectMaterialsAdapter extends BaseAdapter<SubjectMaterialsAdapter.SubjectMaterialsViewHolder, List<SubjectItemFile>> {

    private static final int TYPE_FILE = 0;
    private static final int TYPE_FOLDER = 1;

    static class SubjectMaterialsViewHolder extends RecyclerView.ViewHolder {

        TextView mFileName;
        TextView mSize;
        TextView mFileTime;
        TextView mOwnersName;
        Button mMenuButton;
        RelativeLayout mClickArea;

        SubjectMaterialsViewHolder(View itemView, int viewType) {
            super(itemView);
            mClickArea = (RelativeLayout) itemView.findViewById(R.id.clickArea);
            mFileName = (TextView) itemView.findViewById(R.id.file_name);
            mMenuButton = (Button) itemView.findViewById(R.id.file_menu_button);
            mFileTime = (TextView) itemView.findViewById(R.id.file_time);

            switch (viewType) {
                case TYPE_FILE:
                    mSize = (TextView) itemView.findViewById(R.id.file_size);
                    break;
                case TYPE_FOLDER:
                    mOwnersName = (TextView) itemView.findViewById(R.id.file_owner);
                    break;
            }
        }
    }

    @Override
    public SubjectMaterialsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size(), parent.getContext());
        if (layout == 0) {
            switch (viewType) {
                case TYPE_FILE:
                    layout = R.layout.item_material_file;
                    break;
                case TYPE_FOLDER:
                    layout = R.layout.item_material_folder;
                    break;
            }
        }
        View view = inflater.inflate(layout, parent, false);
        return new SubjectMaterialsViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(SubjectMaterialsViewHolder holder, int position) {
        if (mItems.size() > 0) {
            SubjectItemFile item = mItems.get(position);
            holder.mFileName.setText(item.getName());
            if (item.getTime() != null) {
                holder.mFileTime.setText(FacadeCommon.makeDate(item.getTime()));
            } else {
                holder.mFileTime.setText(holder.mFileTime.getContext().getString(R.string.just_now));
            }
            switch (getItemViewType(position)) {
                case TYPE_FILE:
                    holder.mSize.setText(FacadeCommon.readableFileSize(item.getSize(), false));
                    break;
                case TYPE_FOLDER:
                    holder.mOwnersName.setText(item.getOwnersName());
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        SubjectItemFile item = mItems.get(position);
        if (item != null) {
            if (item.getType().equals("f")) {
                return TYPE_FOLDER;
            }
        }
        return TYPE_FILE;
    }
}

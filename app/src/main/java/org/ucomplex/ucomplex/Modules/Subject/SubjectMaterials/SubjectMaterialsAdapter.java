package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Common.interfaces.OnlIstItemClicked;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPPresenter;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectItemFile;
import org.ucomplex.ucomplex.R;

import java.util.List;

import static org.ucomplex.ucomplex.Common.UCApplication.BASE_URL;
import static org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.NotificationService.EXTRA_BODY;
import static org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.NotificationService.EXTRA_LARGE_ICON;
import static org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.NotificationService.EXTRA_TITLE;

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

    private boolean[] mItemTypes;
    private String filename;
    private boolean mMyFiles;
    private OnlIstItemClicked<SubjectMaterialsParams> onlIstItemClicked;

    public void setOnlIstItemClicked(OnlIstItemClicked<SubjectMaterialsParams> onlIstItemClicked) {
        this.onlIstItemClicked = onlIstItemClicked;
    }

    public void setMyFiles(boolean mMyFiles) {
        this.mMyFiles = mMyFiles;
    }


    @Override
    public void setItems(List<SubjectItemFile> data) {
        super.setItems(data);
        if (data != null) {
            mItemTypes = new boolean[data.size()];
            for (int i = 0; i < data.size(); i++) {
                SubjectItemFile file = data.get(i);
                if (file.getType().equals("f")) {
                    mItemTypes[i] = true;
                }
            }
        }
    }

    public int getAdapterSize(){
        return mItems == null ? 0 : mItems.size();
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
            if (getItemViewType(position) == TYPE_FILE) {
                Context context = holder.mSize.getContext();
                holder.mSize.setText(FacadeCommon.readableFileSize(item.getSize(), false));
                FacadeCommon.requireStoragePermission(context);
                Toast.makeText(context, context.getString(R.string.file_download_started), Toast.LENGTH_SHORT).show();
                filename = item.getAddress() + "." + item.getType();
                startNotificationService(filename, "Загрузка файла началась.", null, context);
                String mUrl = BASE_URL + "storage.ucomplex.org/files/users/";
                //TODO: download
            } else if (getItemViewType(position) == TYPE_FOLDER) {
                holder.mOwnersName.setText(item.getOwnersName());
                SubjectMaterialsParams params = new SubjectMaterialsParams();
                params.setFolder(item.getAddress());
                params.setFolder(true);
                params.setMyFolder(mMyFiles);
                onlIstItemClicked.onClick(params);
            }
        }
    }

    private void startNotificationService(String filename, String message, Uri largeIcon, Context context) {
        Intent notificationIntent = new Intent(context, NotificationService.class);
        notificationIntent.putExtra(EXTRA_TITLE, filename);
        notificationIntent.putExtra(EXTRA_BODY, message);
        if (largeIcon != null) {
            notificationIntent.putExtra(EXTRA_LARGE_ICON, largeIcon);
        }
        context.startService(notificationIntent);
    }


    @Override
    public int getItemViewType(int position) {
        if (mItemTypes[position]) {
            return TYPE_FOLDER;
        }
        return TYPE_FILE;
    }
}

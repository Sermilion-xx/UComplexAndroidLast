package org.ucomplex.ucomplex.Modules.Subject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectItemProfile;
import org.ucomplex.ucomplex.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectProfileAdapter extends BaseAdapter<SubjectProfileAdapter.SubjectProfileViewHolder, List<SubjectItemProfile>> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_TEACHERS = 1;
    private static final int TYPE_INFO = 2;

    static class SubjectProfileViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        CircleImageView mIcon;
        TextView mTeachersName;
        TextView mAttendance;
        TextView mAverageGrade;

        SubjectProfileViewHolder(View itemView, int viewType) {
            super(itemView);
            switch (viewType) {
                case TYPE_HEADER:
                    mTitle = (TextView) itemView.findViewById(R.id.subjects_subject_header);
                    break;
                case TYPE_TEACHERS:
                    mIcon = (CircleImageView) itemView.findViewById(R.id.subjects_subject_teacher);
                    mTeachersName = (TextView) itemView.findViewById(R.id.subjects_subject_teacher_name);
                    break;
                case TYPE_INFO:
                    mAttendance = (TextView) itemView.findViewById(R.id.subjects_subject_attendance);
                    mAverageGrade = (TextView) itemView.findViewById(R.id.subjects_subject_mark);
                    break;
            }
        }
    }

    @Override
    public SubjectProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size(), parent.getContext());
        if (layout == 0) {
            switch (viewType) {
                case TYPE_HEADER:   layout = R.layout.item_subject_header;  break;
                case TYPE_TEACHERS: layout = R.layout.item_subject_teacher; break;
                case TYPE_INFO:     layout = R.layout.item_subject_info;    break;
            }
        }
        View view = inflater.inflate(layout, parent, false);
        return new SubjectProfileViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(SubjectProfileViewHolder holder, int position) {
        SubjectItemProfile item = mItems.get(position);
        if (getItemViewType(position) == TYPE_HEADER) {
            holder.mTitle.setText(item.getName());
        } else if (getItemViewType(position) == TYPE_TEACHERS) {
            holder.mTeachersName.setText(item.getName());
            String url = UCApplication.PHOTOS_URL + item.getCode() + ".jpg";
            Glide.with(holder.mTeachersName.getContext())
                    .load(url)
                    .asBitmap()
                    .priority(Priority.HIGH)
                    .into(holder.mIcon);
        } else if (getItemViewType(position) == TYPE_INFO) {
            Context context = holder.mAttendance.getContext();
            String text = context.getString(R.string.absence, item.getAttendance());
            holder.mAttendance.setText(FacadeCommon.fromHtml(text));
            String average = context.getString(R.string.average_mark, item.getMark());
            holder.mAverageGrade.setText(average);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == getItemCount() - 1) {
            return TYPE_INFO;
        } else {
            return TYPE_TEACHERS;
        }
    }
}

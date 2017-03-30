package org.ucomplex.ucomplex.Modules.Subject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class SubjectProfileAdapter extends BaseAdapter<SubjectProfileAdapter.SubjectViewHolder, List<SubjectItemProfile>> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_TEACHERS = 1;
    private static final int TYPE_INFO = 2;

    static class SubjectViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        CircleImageView mIcon;
        TextView mTeachersName;
        TextView mAttendance;
        TextView mAverageGrade;

        public SubjectViewHolder(View itemView, int viewType) {
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
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}

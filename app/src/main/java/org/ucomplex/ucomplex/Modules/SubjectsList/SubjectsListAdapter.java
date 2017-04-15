package org.ucomplex.ucomplex.Modules.SubjectsList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectActivity;
import org.ucomplex.ucomplex.Modules.SubjectsList.model.SubjectsListItem;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectsListAdapter extends BaseAdapter<SubjectsListAdapter.SubjectsListViewHolder, List<SubjectsListItem>> {

    static class SubjectsListViewHolder extends RecyclerView.ViewHolder {

        TextView mSubjectName;
        TextView mAssessmentType;
        RelativeLayout subjectLayout;

        SubjectsListViewHolder(View itemView) {
            super(itemView);
            subjectLayout = (RelativeLayout) itemView.findViewById(R.id.subject_layout);
            mSubjectName = (TextView) itemView.findViewById(R.id.subject_name);
            mAssessmentType = (TextView) itemView.findViewById(R.id.subject_assessment_type);
        }

        boolean isEmpty() {
            return subjectLayout == null &&
                    mSubjectName == null &&
                    mAssessmentType == null;

        }
    }

    public SubjectsListAdapter() {
        mItems = new ArrayList<>();
    }

    @Override
    public SubjectsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == 0) {
            layout = R.layout.item_subject;
        }
        View view = inflater.inflate(layout, parent, false);
        return new SubjectsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubjectsListViewHolder holder, int position) {
        if (mItems.size() > 0) {
            Context context = holder.mSubjectName.getContext();
            SubjectsListItem item = mItems.get(position);
            holder.mSubjectName.setText(item.getCourseName());
            holder.mAssessmentType.setText(context.getString(item.getAssessmentType()));
            holder.subjectLayout.setOnClickListener(v -> {
                Intent intent = SubjectActivity.creteIntent(context, item.getCourseId(), item.getCourseName());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return (mItems == null || mItems.size() == 0) ? 1 : mItems.size();
    }
}

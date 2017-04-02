package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineItem;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectTimelineAdapter extends BaseAdapter<SubjectTimelineAdapter.SubjectTimelineViewHolder, List<SubjectTimelineItem>> {

    static class SubjectTimelineViewHolder extends RecyclerView.ViewHolder{

        public SubjectTimelineViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public SubjectTimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SubjectTimelineViewHolder holder, int position) {

    }
}

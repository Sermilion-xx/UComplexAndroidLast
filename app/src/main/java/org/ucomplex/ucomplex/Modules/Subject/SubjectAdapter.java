package org.ucomplex.ucomplex.Modules.Subject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubectViewHolder> {


    static class SubectViewHolder extends RecyclerView.ViewHolder {

        public SubectViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public SubectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SubectViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}

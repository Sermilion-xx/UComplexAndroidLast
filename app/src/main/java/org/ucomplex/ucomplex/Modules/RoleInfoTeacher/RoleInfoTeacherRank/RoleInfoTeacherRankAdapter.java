package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model.RoleInfoTeacherRankItem;
import org.ucomplex.ucomplex.R;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleInfoTeacherRankAdapter extends BaseAdapter<RoleInfoTeacherRankAdapter.RoleInfoTeacherRankViewHolder, List<RoleInfoTeacherRankItem>> {

    static class RoleInfoTeacherRankViewHolder extends RecyclerView.ViewHolder {

        TextView queqtion;
        TextView hint;
        View button1;
        View button2;
        View button3;
        View button4;
        View button5;
        View button6;
        View button7;
        View button8;
        View button9;
        View button10;

        RoleInfoTeacherRankViewHolder(View itemView) {
            super(itemView);
            button1 = itemView.findViewById(R.id.question_button_1);
            button2 = itemView.findViewById(R.id.question_button_2);
            button3 = itemView.findViewById(R.id.question_button_3);
            button4 = itemView.findViewById(R.id.question_button_4);
            button5 = itemView.findViewById(R.id.question_button_5);
            button6 = itemView.findViewById(R.id.question_button_6);
            button7 = itemView.findViewById(R.id.question_button_7);
            button8 = itemView.findViewById(R.id.question_button_8);
            button9 = itemView.findViewById(R.id.question_button_9);
            button10 = itemView.findViewById(R.id.question_button_10);
        }
    }


    @Override
    public RoleInfoTeacherRankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            layout = R.layout.item_teacher_rating;
        }
        View view = inflater.inflate(layout, parent, false);
        return new RoleInfoTeacherRankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoleInfoTeacherRankViewHolder holder, int position) {

    }


}

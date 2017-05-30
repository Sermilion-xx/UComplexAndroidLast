package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank;

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

import java.util.ArrayList;
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

        TextView question;
        TextView hint;
        View view1;
        View view2;
        View view3;
        View view4;
        View view5;
        View view6;
        View view7;
        View view8;
        View view9;
        View view10;
        final List<View> masks = new ArrayList<>();

        RoleInfoTeacherRankViewHolder(View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.question);
            hint = (TextView) itemView.findViewById(R.id.question_hint);
            view1 = itemView.findViewById(R.id.mask1);
            view2 = itemView.findViewById(R.id.mask2);
            view3 = itemView.findViewById(R.id.mask3);
            view4 = itemView.findViewById(R.id.mask4);
            view5 = itemView.findViewById(R.id.mask5);
            view6 = itemView.findViewById(R.id.mask6);
            view7 = itemView.findViewById(R.id.mask7);
            view8 = itemView.findViewById(R.id.mask8);
            view9 = itemView.findViewById(R.id.mask9);
            view10 = itemView.findViewById(R.id.mask10);
            rankCellWidth = view10.getLayoutParams().width;
            masks.add(view1);
            masks.add(view2);
            masks.add(view3);
            masks.add(view4);
            masks.add(view5);
            masks.add(view6);
            masks.add(view7);
            masks.add(view8);
            masks.add(view9);
            masks.add(view10);
        }
    }

    private static int rankCellWidth;

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
        if (mItems.size() > 0) {
            RoleInfoTeacherRankItem item = mItems.get(position);
            holder.hint.setText(item.getHint());
            holder.question.setText(item.getQuestion());
            int score = (int) item.getScore();
            double fraction = item.getScore() - score;
            for (int i = 0; i <= score; i++) {
                View mask = holder.masks.get(i);
                mask.getLayoutParams().width = rankCellWidth;
                mask.setVisibility(View.VISIBLE);
                if (i == score) {
                    int fullWidth = mask.getLayoutParams().width;
                    long width = (Math.round(fraction * 100) * fullWidth) / 100;
                    mask.getLayoutParams().width = (int) width;
                }
            }
            for (int k = score + 1; k < 10; k++) {
                holder.masks.get(k).setVisibility(View.INVISIBLE);
            }
        }
    }
}

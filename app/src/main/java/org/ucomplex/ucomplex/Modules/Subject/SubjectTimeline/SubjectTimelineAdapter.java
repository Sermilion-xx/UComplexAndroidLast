package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import org.ucomplex.ucomplex.Common.Constants;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseAdapter;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineItem;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineParams;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import static org.ucomplex.ucomplex.Common.FacadeMedia.getLetter;


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

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final String FONTS_FONTAWESOME_WEBFONT_TTF = "fonts/fontawesome-webfont.ttf";

    static class SubjectTimelineViewHolder extends RecyclerView.ViewHolder {
        ImageView mMarkImage;
        TextView mName;
        TextView mTime;
        TextView mTimeIcon;
        Button loadMoreButton;

        public SubjectTimelineViewHolder(View itemView, int itemType) {
            super(itemView);
            if (itemType == 0) {
                mMarkImage = (ImageView) itemView.findViewById(R.id.subject_mark_image);
                mName = (TextView) itemView.findViewById(R.id.subject_name);
                mTime = (TextView) itemView.findViewById(R.id.subject_time);
                mTimeIcon = (TextView) itemView.findViewById(R.id.subject_time_icon);
            } else if (itemType == 1) {
                loadMoreButton = (Button) itemView.findViewById(R.id.loadMoreButton);
            }
        }
    }

    private String[] colors = {"#51cde7", "#fecd71", "#9ece2b", "#d18ec0"};
    private OnListItemClicked<SubjectTimelineParams, Void> onListItemClicked;

    public SubjectTimelineAdapter() {
        mItems = new ArrayList<>();
    }

    public void setOnListItemClicked(OnListItemClicked<SubjectTimelineParams, Void> onListItemClicked) {
        this.onListItemClicked = onListItemClicked;
    }

    @Override
    public SubjectTimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout = FacadeCommon.getAvailableListLayout(mItems.size());
        if (layout == Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE) {
            switch (viewType) {
                case TYPE_ITEM:
                    layout = R.layout.item_subject_timeline;
                    break;
                case TYPE_FOOTER:
                    layout = R.layout.item_footer;
                    break;
            }
        }
        View view = inflater.inflate(layout, parent, false);
        return new SubjectTimelineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(SubjectTimelineViewHolder holder, int position) {
        if (mItems.size() > 0) {
            SubjectTimelineItem item = mItems.get(position);
            if (getItemViewType(position) == TYPE_ITEM) {
                Context context = holder.mName.getContext();
                holder.mName.setText(item.getCourseName());
                holder.mTime.setText(item.getTime());

                String hexColor = this.colors[item.getType()];
                long thisCol = Long.decode(hexColor) + 4278190080L;
                Drawable drawable;
                Typeface typeface = Typeface.createFromAsset(context.getAssets(), FONTS_FONTAWESOME_WEBFONT_TTF);
                if (item.getMark() == 0) {
                    drawable = TextDrawable.builder().beginConfig().useFont(typeface).textColor((int) thisCol).endConfig()
                            .buildRound(String.valueOf(getLetter(item.getMark())), Color.WHITE);
                } else {
                    drawable = TextDrawable.builder()
                            .buildRound(String.valueOf(getLetter(item.getMark())), (int) thisCol);
                }
                holder.mMarkImage.setImageDrawable(drawable);
                holder.mTimeIcon.setTypeface(typeface);
                holder.mTimeIcon.setText("\uF017");
            } else {
                holder.loadMoreButton.setOnClickListener(view -> {
                    SubjectTimelineParams params = new SubjectTimelineParams();
                    params.setStart(getItemCount());
                    onListItemClicked.onClick(params);
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).isEmpty() ? TYPE_FOOTER : TYPE_ITEM;
    }
}

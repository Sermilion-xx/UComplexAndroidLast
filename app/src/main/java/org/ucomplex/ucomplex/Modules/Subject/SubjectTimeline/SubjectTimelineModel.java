package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline;

import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineItem;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineRaw;

import java.util.List;

import io.reactivex.Observable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectTimelineModel implements MVPModel<SubjectTimelineRaw, List<SubjectTimelineItem>, Integer> {

    @Override
    public Observable<SubjectTimelineRaw> loadData(Integer params) {
        return null;
    }

    @Override
    public void setData(List<SubjectTimelineItem> data) {

    }

    @Override
    public void addData(List<SubjectTimelineItem> data) {

    }

    @Override
    public void clear() {

    }

    @Override
    public List<SubjectTimelineItem> getData() {
        return null;
    }

    @Override
    public void processData(SubjectTimelineRaw data) {

    }
}

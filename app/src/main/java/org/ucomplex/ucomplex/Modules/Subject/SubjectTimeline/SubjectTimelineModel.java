package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.Marks;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineItem;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineParams;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineRaw;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectTimelineModel implements MVPModel<SubjectTimelineRaw, List<SubjectTimelineItem>, SubjectTimelineParams> {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String LOCALE = "Ru";
    private List<SubjectTimelineItem> mData;
    private SubjectTimelineService mService;

    private SubjectTimelineModel() { }

    private void inject() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    public static SubjectTimelineModel getInstance(){
        SubjectTimelineModel model = new SubjectTimelineModel();
        model.inject();
        return model;
    }

    public static SubjectTimelineModel getTestInstance(){
        return new SubjectTimelineModel();
    }

    @Inject
    public void setSubjectTimelineService(SubjectTimelineService service) {
        this.mService = service;
    }

    @Override
    public Observable<SubjectTimelineRaw> loadData(SubjectTimelineParams params) {
        return mService.getTimeline(params.getGcourse(), params.getStart()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<SubjectTimelineItem> data) {
        mData = data;
    }

    @Override
    public void addData(List<SubjectTimelineItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<SubjectTimelineItem> getData() {
        return mData;
    }

    @Override
    public void processData(SubjectTimelineRaw data) {
        mData = new ArrayList<>();
        Map<Integer, String> teachers = data.getTeachers();
        Map<Integer, String> courses = data.getCourses();
        List<Marks> marks = data.getMarks();
        for (Marks mark: marks) {
            SubjectTimelineItem item = new SubjectTimelineItem();
            item.setMark(mark.getMark());
            item.setCourseName(courses.get(mark.getCourse()));
            Date date = new Date(mark.getTime()*1000);
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, new Locale(LOCALE));
            String time = sdf.format(date);
            String timeString = FacadeCommon.makeDate(time, true);
            item.setTime(timeString);
            item.setType(mark.getType());
            item.setTeacherName(teachers.get(mark.getTeacher()));
            mData.add(item);
        }
    }
}

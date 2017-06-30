package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.model.CalendarBeltItem;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.CalendarPageModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class CalendarDayBeltModel implements MVPModel<CalendarPageRaw, List<CalendarBeltItem>, String> {

    private List<CalendarBeltItem> mData;
    private String dayInt;

    @Override
    public Observable<CalendarPageRaw> loadData(String params) {
        this.dayInt = params;
        return new Observable<CalendarPageRaw>() {
            @Override
            protected void subscribeActual(Observer<? super CalendarPageRaw> observer) {
                observer.onNext(CalendarPageModel.getInstance().getData());
                observer.onComplete();
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<CalendarBeltItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<CalendarBeltItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<CalendarBeltItem> getData() {
        return mData;
    }

    @Override
    public List<CalendarBeltItem> processData(CalendarPageRaw data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        Map<Integer, CalendarPageRaw.ChangedDayLesson> changedDays = data.getChangedDays().get(dayInt);
        List<Integer> changeDaysKeys = FacadeCommon.getKeys(changedDays);
        if (changedDays != null) {
            for (int i = 0; i < changedDays.size(); i++) {
                CalendarPageRaw.ChangedDayLesson lesson = changedDays.get(changeDaysKeys.get(i));
                int mark = lesson.getMark();
                int type = lesson.getType();
                int course = lesson.getCourse();
                String subjectName = data.getCourses().get(course);
                CalendarBeltItem item = new CalendarBeltItem(subjectName, mark, type);
                mData.add(item);
            }
        }
        return mData;
    }
}

package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageParams;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CalendarPagePresenter extends AbstractPresenter<
        CalendarPageRaw, CalendarPageRaw,
        CalendarPageParams, CalendarPageModel> {

    public CalendarPagePresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(CalendarPageParams params) {
        Observable<CalendarPageRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<CalendarPageRaw>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(CalendarPageRaw value) {
                mModel.processData(value);
                if (getView() != null && getData() != null) {
                    getView().dataLoaded();
                }
            }

            @Override
            public void onError(Throwable e) {
                hideProgress();
            }

            @Override
            public void onComplete() {
                hideProgress();
            }
        });
    }

    @NonNull
    List<Pair<String, CalendarPageRaw.ChangedDayLesson>> filteredChangedDays(String courseValue, CalendarPageRaw calendar, Map<Integer, String> courses) {
        Object courseKeyObj = FacadeCommon.getKeyFromValue(courses, courseValue);
        int courseKey = -1;
        if (courseKeyObj != null) {
            courseKey = (int) courseKeyObj;
        }
        List<Pair<String, CalendarPageRaw.ChangedDayLesson>> filteredDays = new ArrayList<>();
        List<String> changeDaysKeys = FacadeCommon.getKeys(calendar.getChangedDays());
        for (int i = 0; i < calendar.getChangedDays().size(); i++) {
            String changedDayKey = changeDaysKeys.get(i);
            Map<Integer, CalendarPageRaw.ChangedDayLesson> lessonsForDayMap = calendar.getChangedDays().get(changedDayKey);
            List<Integer> lessonsKeys = FacadeCommon.getKeys(lessonsForDayMap);
            for (int j = 0; j < lessonsKeys.size(); j++) {
                CalendarPageRaw.ChangedDayLesson lesson = lessonsForDayMap.get(lessonsKeys.get(j));
                if (lesson.getCourse() == courseKey) {
                    filteredDays.add(new Pair<>(changedDayKey, lesson));
                }
            }
        }
        return filteredDays;
    }

}
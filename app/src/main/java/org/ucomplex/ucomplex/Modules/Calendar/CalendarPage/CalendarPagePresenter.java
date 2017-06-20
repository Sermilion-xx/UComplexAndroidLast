package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage;

import android.support.annotation.NonNull;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageParams;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;

import java.util.HashMap;
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
                if (getView() != null) {
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
    Map<Integer, CalendarPageRaw.ChangedDay> filtedChangedDays(String courseValue, CalendarPageRaw calendar, Map<Integer, String> courses) {
        Object courseKeyObj = FacadeCommon.getKeyFromValue(courses, courseValue);
        int courseKey = -1;
        if (courseKeyObj != null) {
            courseKey = (int) courseKeyObj;
        }

        Map<Integer, CalendarPageRaw.ChangedDay> filteredDays = new HashMap<>();
        List<String> changeDaysKeys = FacadeCommon.getKeys(calendar.getChangedDays());
        for (int i = 0; i < calendar.getChangedDays().size(); i++) {
            Map<Integer, CalendarPageRaw.ChangedDay> dayMap = calendar.getChangedDays().get(changeDaysKeys.get(i));
            List<Integer> lessonsKeys = FacadeCommon.getKeys(dayMap);
            for (int j = 0; j < lessonsKeys.size(); j++) {
                CalendarPageRaw.ChangedDay day = dayMap.get(lessonsKeys.get(i));
                if (day.getCourse() == courseKey) {
                    filteredDays.put(lessonsKeys.get(i), day);
                }
            }
        }
        return filteredDays;
    }

}
package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineItem;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineRaw;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectTimelinePresenter extends AbstractPresenter<
        SubjectTimelineRaw, List<SubjectTimelineItem>, Integer, SubjectTimelineModel> {

    public SubjectTimelinePresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setModel(SubjectTimelineModel model) {
        mModel = model;
    }

    @Override
    public void loadData(Integer gcourse) {
        Observable<SubjectTimelineRaw> dataObservable = mModel.loadData(gcourse);
        dataObservable.subscribe(new Observer<SubjectTimelineRaw>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(SubjectTimelineRaw value) {
                mModel.processData(value);
                if (getView() != null) {
                    ((SubjectTimelineFragment) getView()).dataLoaded();
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
}

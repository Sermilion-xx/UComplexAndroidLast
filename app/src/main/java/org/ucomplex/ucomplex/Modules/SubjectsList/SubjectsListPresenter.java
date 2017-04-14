package org.ucomplex.ucomplex.Modules.SubjectsList;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Modules.SubjectsList.model.SubjectsListItem;
import org.ucomplex.ucomplex.Modules.SubjectsList.model.SubjectsListRaw;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectsListPresenter extends AbstractPresenter<
        SubjectsListRaw, List<SubjectsListItem>, Void, SubjectsListModel> {

    public SubjectsListPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setModel(SubjectsListModel model) {
        mModel = model;
    }

    @Override
    public void loadData(Void params) {
        Observable<SubjectsListRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<SubjectsListRaw>() {
            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(SubjectsListRaw value) {
                mModel.processData(value);
                if (getView() != null) {
                    ((SubjectsListActivity) getView()).dataLoaded();
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

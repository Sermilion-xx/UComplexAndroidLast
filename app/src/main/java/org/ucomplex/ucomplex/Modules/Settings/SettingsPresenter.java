package org.ucomplex.ucomplex.Modules.Settings;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Settings.model.SettingsItem;
import org.ucomplex.ucomplex.Modules.Settings.model.SettingsRaw;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SettingsPresenter extends AbstractPresenter<
        SettingsRaw, List<SettingsItem>,
        Void, SettingsModel> {

    public SettingsPresenter() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Override
    public void loadData(Void params) {
        Observable<SettingsRaw> dataObservable = mModel.loadData(params);
        dataObservable.subscribe(new Observer<SettingsRaw>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(SettingsRaw value) {
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
}
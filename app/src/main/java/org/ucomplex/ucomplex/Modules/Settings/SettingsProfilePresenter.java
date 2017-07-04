package org.ucomplex.ucomplex.Modules.Settings;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Settings.model.SettingsRaw;
import org.ucomplex.ucomplex.Modules.Settings.model.Status;
import org.ucomplex.ucomplex.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SettingsProfilePresenter extends AbstractPresenter<
        SettingsRaw, SettingsRaw,
        Void, SettingsProfileModel> {

    public SettingsProfilePresenter() {
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

    public void saveSettings(String currpass,
                             String oldpass,
                             String pass,
                             String email,
                             String phone,
                             Integer closed,
                             Integer searchable) {
        Observable<Status> dataObservable = mModel.saveSettings(
                currpass,
                oldpass,
                pass,
                email,
                phone,
                closed,
                searchable);
        dataObservable.subscribe(new Observer<Status>() {

            @Override
            public void onSubscribe(Disposable d) {
                showProgress();
            }

            @Override
            public void onNext(Status value) {
                if (getView() != null) {
                    if (value.getStatus().equals("error")) {
                        getView().showToast(R.string.error);
                    } else {
                        getView().showToast(R.string.settings_saved);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                hideProgress();
                if (getView() != null) {
                    getView().showToast(R.string.error);
                }
            }

            @Override
            public void onComplete() {
                hideProgress();
            }
        });
    }
}
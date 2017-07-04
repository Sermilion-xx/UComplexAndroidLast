package org.ucomplex.ucomplex.Modules.Settings;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Settings.model.SettingsItem;
import org.ucomplex.ucomplex.Modules.Settings.model.SettingsRaw;
import org.ucomplex.ucomplex.Modules.Settings.model.Status;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/07/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class SettingsProfileModel implements MVPModel<SettingsRaw, SettingsRaw, Void> {

    private SettingsRaw mData;
    private SettingsProfileService mService;

    public SettingsProfileModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setService(SettingsProfileService service) {
        this.mService = service;
    }

    @Override
    public Observable<SettingsRaw> loadData(Void params) {
        return mService.getProfile().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Status> saveSettings(String currpass,
                                           String oldpass,
                                           String pass,
                                           String email,
                                           String phone,
                                           Integer closed,
                                           Integer searchable) {
        return mService.saveProfile(currpass,
                oldpass,
                pass,
                email,
                phone,
                closed,
                searchable).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(SettingsRaw data) {
        this.mData = data;
    }

    @Override
    public void addData(SettingsRaw data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        mData = null;
    }

    @Override
    public SettingsRaw getData() {
        return mData;
    }

    @Override
    public SettingsRaw processData(SettingsRaw data) {
        mData = data;
        return mData;
    }
}

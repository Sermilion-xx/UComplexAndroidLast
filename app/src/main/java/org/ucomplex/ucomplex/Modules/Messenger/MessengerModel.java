package org.ucomplex.ucomplex.Modules.Messenger;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.DownloadCallback;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Messenger.model.CompanionInfo;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerItem;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerRaw;
import org.ucomplex.ucomplex.Modules.Portfolio.retrofit.DownloadFileService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class MessengerModel implements MVPModel<MessengerRaw, List<MessengerItem>, Integer> {

    private List<MessengerItem> mData;
    private MessengerService mService;
    private CompanionInfo companion;

    public MessengerModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setService(MessengerService service) {
        this.mService = service;
    }

    @Override
    public Observable<MessengerRaw> loadData(Integer params) {
        return mService.getMessages(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<MessengerItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<MessengerItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<MessengerItem> getData() {
        return mData;
    }

    @Override
    public List<MessengerItem> processData(MessengerRaw data) {
        mData = new ArrayList<>();
        for (MessengerItem item: data.getMessages()) {
            item.setFiles(data.getFiles().get(item.getId()));
            mData.add(item);
        }
        this.companion = data.getCompanion_info();
        return mData;
    }

    public CompanionInfo getCompanion() {
        return companion;
    }
}

package org.ucomplex.ucomplex.Modules.Messenger;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Messenger.model.CompanionInfo;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerItem;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerRaw;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    public Observable<MessengerRaw> sendMessage(String message, int companion, RequestBody description, List<MultipartBody.Part> multiParts) {
        return mService.sendMessages(message, companion, description, multiParts).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<MessengerItem> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        this.mData = data;
    }

    @Override
    public void addData(List<MessengerItem> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(0, data.get(0));
    }

    @Override
    public void clear() {
        mData = null;
    }

    @Override
    public List<MessengerItem> getData() {
        return mData;
    }

    @Override
    public List<MessengerItem> processData(MessengerRaw data) {
        List<MessengerItem> messages = new ArrayList<>();
        for (MessengerItem item : data.getMessages()) {
            item.setFiles(data.getFiles().get(item.getId()));
            messages.add(item);
        }
        this.companion = data.getCompanion_info();
        return messages;
    }

    public CompanionInfo getCompanion() {
        return companion;
    }

}

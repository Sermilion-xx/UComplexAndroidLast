package org.ucomplex.ucomplex.Modules.MessagesList;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.MessagesList.model.MessagesListItem;
import org.ucomplex.ucomplex.Modules.MessagesList.model.MessagesListRaw;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class MessagesListModel implements MVPModel<MessagesListRaw, List<MessagesListItem>, Void> {

    private List<MessagesListItem> mData;
    private MessagesListService mService;

    public MessagesListModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setService(MessagesListService service) {
        this.mService = service;
    }

    @Override
    public Observable<MessagesListRaw> loadData(Void params) {
        return mService.getMessagesList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<MessagesListItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<MessagesListItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<MessagesListItem> getData() {
        return mData;
    }

    @Override
    public List<MessagesListItem> processData(MessagesListRaw data) {
        mData = data.getDialogs();
        return mData;
    }
}

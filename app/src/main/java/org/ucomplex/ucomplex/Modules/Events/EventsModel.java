package org.ucomplex.ucomplex.Modules.Events;

import com.google.gson.Gson;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Events.model.EventItem;
import org.ucomplex.ucomplex.Modules.Events.model.EventsRaw;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class EventsModel implements MVPModel<EventsRaw, List<EventItem>, Integer> {

    private List<EventItem> mData;
    private EventsService eventsService;

    public EventsModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    @Inject
    public void setEventsService(EventsService service) {
        this.eventsService = service;
    }

    @Override
    public Observable<EventsRaw> loadData(Integer start) {
        return eventsService.getEvents(start).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<EventItem> data) {
        mData = data;
    }

    @Override
    public void addData(List<EventItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<EventItem> getData() {
        return mData;
    }

    @Override
    public void processData(EventsRaw data) {
        Gson gson = new Gson();
        for (EventItem item: data.getEvents()) {
            item.setParamsObj(gson.fromJson(item.getParams(), EventItem.EventParams.class));
            item.setParams(null);
        }
        mData = data.getEvents();
    }
}

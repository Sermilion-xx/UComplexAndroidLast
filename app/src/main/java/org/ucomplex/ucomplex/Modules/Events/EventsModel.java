package org.ucomplex.ucomplex.Modules.Events;

import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class EventsModel implements MVPModel<List<EventItem>,List<EventItem>,EventsParams> {

    @Override
    public Observable<List<EventItem>> loadData(EventsParams params) {
        return null;
    }

    @Override
    public void setData(List<EventItem> data) {

    }

    @Override
    public void addData(List<EventItem> data) {

    }

    @Override
    public void clear() {

    }

    @Override
    public List<EventItem> getData() {
        return null;
    }
}

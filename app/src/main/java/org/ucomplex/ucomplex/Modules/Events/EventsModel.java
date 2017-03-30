package org.ucomplex.ucomplex.Modules.Events;

import com.google.gson.Gson;

import org.json.JSONException;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Events.model.EventItem;
import org.ucomplex.ucomplex.Modules.Events.model.EventsRaw;

import java.util.ArrayList;
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
        mData = new ArrayList<>();
        Gson gson = new Gson();
        for (EventItem item: data.getEvents()) {
            item.setParamsObj(gson.fromJson(item.getParams(), EventItem.EventParams.class));
            item.setParams(null);
            String displayEvent = makeEvent(item.getType(), item.getParamsObj());
            item.setEventText(displayEvent);
            mData.add(item);
        }
    }


    private  String makeEvent(int type, EventItem.EventParams params)
            throws NumberFormatException {
        String result = "";
        String[] hourTypes = new String[]{"протокол занятия",
                "протокол рубежного контроля", "экзаменационную ведомость",
                "индивидуальное занятие"};
        String courseName;
        String name;
        switch (type) {

            case 1:
                courseName = params.getCourseName();
                result = "Загружен материал по дисциплине " + courseName + ".";
                break;

            case 2:
                int hourType = params.getHourType();
                courseName = params.getCourseName();
                name = params.getName();
                result = "Преподаватель " + name + " заполнил "
                        + hourTypes[hourType] + " по дисциплине " + courseName
                        + ".";
                break;

            case 3:
                String semestr = params.getSemester();
                String year = params.getYear();
                result = "Вы произвели оплату за " + semestr + "-й семестр " + year
                        + " учебного года.";
                break;

            case 4:
                String eventName = params.getEventName();
                String date = params.getDate();
                result = "Вы приглашены на участие в мероприятии " + eventName
                        + ", которое состоится " + date;
                break;

            case 5:
                name = params.getEventName();
                String author = params.getAuthor();
                result = "В книжную полку добавлена книга " + name + ", " + author;
                break;

            case 6:
                String message = "";
                if (params.getType() != 0) {
                    int t = params.getType();
                    if (t == 1) {
                        message = "Ваша фотография отклонена из-за несоответствия условиям загрузки личной фотографии.";
                    }
                } else {
                    message = params.getMessage();
                }
                result = "СИСТЕМНОЕ СООБЩЕНИЕ: \n" + message;
                break;
        }
        return result;
    }
}

package org.ucomplex.ucomplex.Modules.Events.model;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 29/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class EventsRaw {

    private List<EventItem> events;

    public EventsRaw() {

    }

    //for testing
    public EventsRaw(List<EventItem> events) {
        this.events = events;
    }

    public List<EventItem> getEvents() {
        return events;
    }

    public void setEvents(List<EventItem> events) {
        this.events = events;
    }
}

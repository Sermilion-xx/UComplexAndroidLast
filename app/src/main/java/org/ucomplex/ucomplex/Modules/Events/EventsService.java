package org.ucomplex.ucomplex.Modules.Events;


import org.ucomplex.ucomplex.Modules.Events.model.EventsRaw;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface EventsService {
    @GET("/user/events?mobile=1")
    Observable<EventsRaw> getEvents(@Query("start") Integer start);

}

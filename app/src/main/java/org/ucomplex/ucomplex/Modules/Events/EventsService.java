package org.ucomplex.ucomplex.Modules.Events;


import org.ucomplex.ucomplex.Modules.Events.model.EventsRaw;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    @POST("/user/events?mobile=1") @FormUrlEncoded
    Observable<EventsRaw> getEvents(@Field("start") Integer start);

}

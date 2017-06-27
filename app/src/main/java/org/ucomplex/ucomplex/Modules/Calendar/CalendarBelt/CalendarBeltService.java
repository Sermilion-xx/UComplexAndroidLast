package org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt;


import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.model.CalendarBeltRaw;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface CalendarBeltService {
    @POST("/student/ajax/calendar_belt?mobile=1") @FormUrlEncoded
    Observable<CalendarBeltRaw> getCalendarBelt(@Field("start") Integer start);
}

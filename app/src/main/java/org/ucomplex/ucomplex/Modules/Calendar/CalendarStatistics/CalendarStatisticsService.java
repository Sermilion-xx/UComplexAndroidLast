package org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics;


import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.model.CalendarStatisticsRaw;

import io.reactivex.Observable;
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

public interface CalendarStatisticsService {
    @POST("/student/ajax/all_courses_stat?mobile=1")
    Observable<CalendarStatisticsRaw> getStatistics();
}

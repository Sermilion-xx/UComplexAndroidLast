package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline;

import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineRaw;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface SubjectTimelineService {

    @POST("/student/ajax/calendar_belt?mobile=1")
    @FormUrlEncoded
    Observable<SubjectTimelineRaw> getTimeline(@Field("gcourse") Integer gcourse,
                                               @Field("start") Integer start);
}

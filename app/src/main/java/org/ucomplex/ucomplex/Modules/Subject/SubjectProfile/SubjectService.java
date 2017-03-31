package org.ucomplex.ucomplex.Modules.Subject.SubjectProfile;

import org.ucomplex.ucomplex.Modules.Subject.model.SubjectRaw;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface SubjectService {
    @POST("/student/ajax/my_subjects?mobile=1")  @FormUrlEncoded
    Observable<SubjectRaw> getSubject(@Field("subjId") Integer subjId);
}

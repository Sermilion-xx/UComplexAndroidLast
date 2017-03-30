package org.ucomplex.ucomplex.Modules.Subject;

import org.ucomplex.ucomplex.Modules.Subject.model.SubjectRaw;

import io.reactivex.Observable;
import retrofit2.http.GET;
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

    @GET("student/ajax/my_subjects?mobile=1")
    Observable<SubjectRaw> getSubject(@Query("gcourse") Integer start);
}

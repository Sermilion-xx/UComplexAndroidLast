package org.ucomplex.ucomplex.Modules.SubjectsList;

import org.ucomplex.ucomplex.Modules.SubjectsList.model.SubjectsListRaw;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface SubjectsListService {

    @GET("/student/subjects_list?mobile=1")
    Observable<SubjectsListRaw> getSubjectList();
}

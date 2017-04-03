package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.MaterialsRaw;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 01/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface SubjectTeachersMaterialsService {

    @POST("/student/ajax/teacher_files?mobile=1")  @FormUrlEncoded
    Observable<MaterialsRaw> getMaterials(@Field("folder") String folder);

}

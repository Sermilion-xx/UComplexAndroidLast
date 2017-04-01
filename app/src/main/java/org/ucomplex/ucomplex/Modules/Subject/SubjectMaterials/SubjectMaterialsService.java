package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

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

public interface SubjectMaterialsService {

    @POST("/teacher/my_files?mobile=1")  @FormUrlEncoded
    Observable<MaterialsRaw> getMaterials(@Field("folder") String folder);

}

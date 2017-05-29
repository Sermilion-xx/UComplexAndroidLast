package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank;

import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model.RoleInfoTeacherRankRaw;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoTeacherRaw;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface RoleInfoTeacherRankService {
    @POST("/user/get_teacher_votes?mobile=1") @FormUrlEncoded
    Observable<RoleInfoTeacherRankRaw> getRank(@Field("teacher") Integer teacher);
}

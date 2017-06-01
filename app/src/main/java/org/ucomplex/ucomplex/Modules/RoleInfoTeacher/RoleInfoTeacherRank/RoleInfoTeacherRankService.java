package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank;

import org.ucomplex.ucomplex.Modules.Login.model.LoginUser;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model.RoleInfoTeacherRankRaw;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    @POST("/user/get_teacher_votes?mobile=1")
    @FormUrlEncoded
    Observable<RoleInfoTeacherRankRaw> getRank(@Field("teacher") Integer teacher);

    @POST("/student/ajax/set_teacher_vote?mobile=1")
    Observable<LoginUser> sendRank(@Query("qs[1]") int vote1,
                                   @Query("qs[2]") int vote2,
                                   @Query("qs[3]") int vote3,
                                   @Query("qs[4]") int vote4,
                                   @Query("qs[5]") int vote5,
                                   @Query("qs[6]") int vote6,
                                   @Query("qs[7]") int vote7,
                                   @Query("qs[8]") int vote8,
                                   @Query("qs[9]") int vote9,
                                   @Query("qs[10]") int vote10);

}

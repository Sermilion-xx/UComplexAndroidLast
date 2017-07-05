package org.ucomplex.ucomplex.Modules.Settings;

import org.ucomplex.ucomplex.Modules.Settings.model.SettingsRaw;
import org.ucomplex.ucomplex.Modules.Settings.model.Status;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface SettingsProfileService {
    @POST("/student/profile?mobile=1")
    Observable<SettingsRaw> getProfile();

    @POST("/student/profile/save?mobile=1") @FormUrlEncoded
    Observable<Status> saveProfile(@Field("oldpass") String oldpass,
                                   @Field("pass") String pass,
                                   @Field("email") String email,
                                   @Field("phone") String phone,
                                   @Field("closed") Integer closed,
                                   @Field("searchable") Integer searchable);
}

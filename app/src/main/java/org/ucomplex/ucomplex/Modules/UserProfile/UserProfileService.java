package org.ucomplex.ucomplex.Modules.UserProfile;

import org.ucomplex.ucomplex.Modules.UserProfile.model.ResponseAddFriend;
import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileRaw;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 03/05/2017.a
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface UserProfileService {

    @POST("/user/person/{id}?mobile=1")
    Observable<UserProfileRaw> getUserProfile(@Path("id") Integer start);

    @POST("/user/friends/add?mobile=1") @FormUrlEncoded
    Observable<ResponseAddFriend> addAsFriend(@Field("user") Integer user);

    @POST("/user/friends/delete") @FormUrlEncoded
    Observable<Void> unfriend(@Field("user") Integer user);

    @POST("/user/blacklist/add") @FormUrlEncoded
    Observable<Void> block(@Field("user") Integer user);

    @POST("/user/blacklist/delete") @FormUrlEncoded
    Observable<Void> unblock(@Field("user") Integer user);

}

package org.ucomplex.ucomplex.Modules.Users.retrofit;

import org.ucomplex.ucomplex.Modules.Users.model.UsersRaw;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 13/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface UsersService {

    @POST("/student/online?mobile=1") @FormUrlEncoded
    Observable<UsersRaw> getOnlineUsers(@Field("start") int start);

    @POST("/user/friends?mobile=1") @FormUrlEncoded
    Observable<UsersRaw> getFriendsUsers(@Field("start") int start);

    @POST("/student/ajax/my_group?mobile=1") @FormUrlEncoded
    Observable<UsersRaw> getGroupUsers(@Field("start") int start);

    @POST("/student/ajax/my_teachers?mobile=1") @FormUrlEncoded
    Observable<UsersRaw> getTeachersUsers(@Field("start") int start);

    @POST("/user/blacklist?mobile=1") @FormUrlEncoded
    Observable<UsersRaw> getBlacklistUsers(@Field("start") int start);
}

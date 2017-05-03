package org.ucomplex.ucomplex.Modules.UserProfile;

import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileRaw;

import io.reactivex.Observable;
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

}

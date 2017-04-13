package org.ucomplex.ucomplex.Modules.Users.retrofit;

import org.ucomplex.ucomplex.Modules.Users.model.UsersRaw;

import io.reactivex.Observable;
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

public interface UsersOnlineService {
    @POST("/student/online?mobile=1")
    Observable<UsersRaw> getOnlineUsers();
}

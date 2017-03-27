package org.ucomplex.ucomplex.Modules.Login;


import org.ucomplex.ucomplex.Modules.Login.model.LoginUser;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface LoginService {
    @POST("/auth?mobile=1")
    Observable<LoginUser> login();
}

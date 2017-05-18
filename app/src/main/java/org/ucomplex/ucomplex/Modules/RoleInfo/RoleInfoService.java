package org.ucomplex.ucomplex.Modules.RoleInfo;

import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoRaw;
import org.ucomplex.ucomplex.Modules.UserProfile.model.UserProfileRaw;

import io.reactivex.Observable;
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

public interface RoleInfoService {
    @POST("/user/page/{roleId}?mobile=1")
    Observable<RoleInfoRaw> getRoleInfo(@Path("roleId") Integer roleId);
}

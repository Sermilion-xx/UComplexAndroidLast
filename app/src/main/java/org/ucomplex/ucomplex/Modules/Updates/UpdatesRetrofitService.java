package org.ucomplex.ucomplex.Modules.Updates;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 16/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface UpdatesRetrofitService {

    @POST("/user/my_news?mobile=1")
    Observable<UpdatesRaw> getUpdates();

}

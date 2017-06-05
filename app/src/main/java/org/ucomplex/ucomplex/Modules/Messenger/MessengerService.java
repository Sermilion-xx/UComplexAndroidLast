package org.ucomplex.ucomplex.Modules.Messenger;

import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerRaw;

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

public interface MessengerService {
    @POST("/user/messages/list?mobile=1") @FormUrlEncoded
    Observable<MessengerRaw> getMessages(@Field("companion") Integer companion);
}

package org.ucomplex.ucomplex.Modules.Messenger;

import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerRaw;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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

    @POST("/user/messages/add?mobile=1") @FormUrlEncoded
    Observable<MessengerRaw> sendMessages(@Field("msg") String message,
                                          @Field("companion") Integer companion);



}

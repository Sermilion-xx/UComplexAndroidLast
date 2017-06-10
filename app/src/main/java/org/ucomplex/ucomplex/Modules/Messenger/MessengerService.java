package org.ucomplex.ucomplex.Modules.Messenger;

import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerRaw;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

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

    @POST("/user/messages/list?mobile=1")
    @FormUrlEncoded
    Observable<MessengerRaw> getMessages(@Field("companion") Integer companion);

    @POST("/user/messages/add?mobile=1")
    @Multipart
    Observable<ResponseBody> sendMessages(@Part("msg") String message,
                                          @Part("companion") Integer companion,
                                          @Part List<MultipartBody.Part> files);

}

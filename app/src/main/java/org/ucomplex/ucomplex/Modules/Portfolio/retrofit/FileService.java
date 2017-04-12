package org.ucomplex.ucomplex.Modules.Portfolio.retrofit;

import org.ucomplex.ucomplex.Modules.Portfolio.model.RequestResult;
import org.ucomplex.ucomplex.Modules.Portfolio.model.ShareFileList;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 09/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface FileService {

    @POST("/student/my_files/rename_file?mobile=1")  @FormUrlEncoded
    Observable<RequestResult> rename(@Field("file") String file,
                                     @Field("name") String name);

    @POST("/student/my_files/delete_file?mobile=1")  @FormUrlEncoded
    Observable<String> delete(@Field("file") String file);

    @POST("/student/my_files/get_access?mobile=1")  @FormUrlEncoded
    Observable<ShareFileList> getShareList(@Field("file") String file);
}

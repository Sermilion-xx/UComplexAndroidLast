package org.ucomplex.ucomplex.Modules.Portfolio.retrofit;

import org.ucomplex.ucomplex.Modules.Portfolio.model.RequestResult;
import org.ucomplex.ucomplex.Modules.Portfolio.model.ShareFileList;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.MaterialsRaw;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

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
    Observable<RequestResult> delete(@Field("file") String file);

    @POST("/student/my_files/get_access?mobile=1")  @FormUrlEncoded
    Observable<ShareFileList> getShareList(@Field("file") String file);

    @Multipart
    @POST("student/my_files/add_files?mobile=1")
    Observable<MaterialsRaw> upload(
            @Part MultipartBody.Part file,
            @PartMap() HashMap<String, RequestBody> folder
    );
}

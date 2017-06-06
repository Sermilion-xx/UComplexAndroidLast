package org.ucomplex.ucomplex.Common.download;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 07/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface DownloadRetrofitService {

    @GET @Streaming
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}

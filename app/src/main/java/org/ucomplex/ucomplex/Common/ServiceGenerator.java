package org.ucomplex.ucomplex.Common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.ucomplex.ucomplex.Common.utility.GsonObjectDeserializerArrayOrObject;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.CalendarPageRaw;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model.Timetable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static org.ucomplex.ucomplex.Common.base.UCApplication.BASE_URL;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 10/02/2017.
 * Project: Listening
 * ---------------------------------------------------
 * <a href="http://www.skyeng.ru">www.skyeng.ru</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class ServiceGenerator {

    private static Retrofit.Builder builder;

    private static void buildRetrofitBuilder(String baseUrl) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Timetable.class , new GsonObjectDeserializerArrayOrObject<Timetable>())
                .create();
        builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    public static <S> S createService(Class<S> serviceClass, String authString, String ... baseUrl) {
        String base_url = BASE_URL;
        if (baseUrl.length > 0) {
            base_url = baseUrl[0];
        }
        buildRetrofitBuilder(base_url);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        httpClient.hostnameVerifier((hostname, session) -> true);
        httpClient.addInterceptor(chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", " Basic " + authString).build();
            return chain.proceed(request);
        });
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

}

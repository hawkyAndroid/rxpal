package com.hawky.rxpal.internal;


import com.hawky.rxpal.internal.interceptor.InterceptorFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author [*昨日重现*] lhy_ycu@163.com
 * @since version 1.0
 */
public class RetrofitService {
    private static final long DEFAULT_TIMEOUT = 5;// 5s
    private String mBaseUrl;// 服务器基地址
    private boolean mDebugLog;// 日志开关

    public void init(String baseUrl, boolean debug) {
        this.mBaseUrl = baseUrl;
        this.mDebugLog = debug;
    }

    public <T> T createService(Class<T> service, Map<String, String> headers) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        // 头部拦截
        if (headers != null) {
            builder.addInterceptor(InterceptorFactory.getHeaderInterceptor(headers));
        }
        // 日志拦截
        if (mDebugLog) {
            builder.addInterceptor(InterceptorFactory.getHttpLoggingInterceptor());
        }
        OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

    private static class Holder {
        private static final RetrofitService INSTANCE = new RetrofitService();
    }

    public static RetrofitService getInstance() {
        return Holder.INSTANCE;
    }

}

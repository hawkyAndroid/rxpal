package com.hawky.rxpal.internal.interceptor;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 拦截器
 *
 * @author [*昨日重现*] lhy_ycu@163.com
 * @since version 1.0
 */
public class InterceptorFactory {

    /**
     * 获取头部拦截
     */
    public static Interceptor getHeaderInterceptor(final Map<String, String> headers) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                if (headers != null && headers.size() > 0) {
                    Set<String> keys = headers.keySet();
                    for (String headerKey : keys) {
                        builder.addHeader(headerKey, headers.get(headerKey)).build();
                    }
                }
                return chain.proceed(builder.build());
            }
        };
    }

    /**
     * 获取日志拦截
     */
    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

}

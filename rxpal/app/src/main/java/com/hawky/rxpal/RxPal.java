package com.hawky.rxpal;

import com.google.gson.Gson;
import com.hawky.rxpal.internal.api.ApiService;
import com.hawky.rxpal.internal.RetrofitService;
import com.hawky.rxpal.internal.transformer.RxTransformer;
import com.hawky.rxpal.internal.func.StringFunc;
import com.hawky.rxpal.internal.subscriber.BaseSubscriber;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscription;

/**
 * REST-API  采用Retrofit+RxJava处理请求 (后面考虑添加文件上传、下载功能)
 *
 * @author [*昨日重现*] lhy_ycu@163.com
 * @since version 1.0
 */
public class RxPal {
    private static final MediaType MEDIA_TYPE_STRING = MediaType.parse("text/plain; charset=utf-8");// string请求
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");// json请求
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");// 上传文件类型

    public static void init(String baseUrl, boolean debug) {
        RetrofitService.getInstance().init(baseUrl, debug);
    }

    private static <T> T createService(Class<T> service, Map<String, String> headers) {
        return RetrofitService.getInstance().createService(service, headers);
    }

    /**
     * 执行GET请求 -- 无参
     *
     * @param url        请求地址
     * @param headers    请求头部
     * @param subscriber 订阅者（处理请求结果）
     */
    public static Subscription get(String url, Map<String, String> headers, BaseSubscriber<String> subscriber) {
        return createService(ApiService.class, headers).get(url)
                .compose(RxTransformer.<ResponseBody>mainTransformer())
                .map(new StringFunc())
                .subscribe(subscriber);
    }

    /**
     * 执行GET请求 -- 带参
     *
     * @param url        请求地址
     * @param headers    请求头部
     * @param subscriber 订阅者（处理请求结果）
     */
    public static Subscription get(String url, Map<String, String> headers, Map<String, Object> params, BaseSubscriber<String> subscriber) {
        return createService(ApiService.class, headers).get(url, params)
                .compose(RxTransformer.<ResponseBody>mainTransformer())
                .map(new StringFunc())
                .subscribe(subscriber);
    }

    /**
     * 执行POST请求  --JSON
     *
     * @param url        请求地址
     * @param headers    请求头部
     * @param params     请求参数
     * @param subscriber 订阅者（处理请求结果）
     */
    public static Subscription post(String url, Map<String, String> headers, Object params, BaseSubscriber<String> subscriber) {
        String json = params != null ? new Gson().toJson(params) : "{}";
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, json);
        return createService(ApiService.class, headers).post(url, requestBody)
                .compose(RxTransformer.<ResponseBody>mainTransformer())
                .map(new StringFunc())
                .subscribe(subscriber);
    }

    /**
     * 执行POST请求  --MAP
     *
     * @param url        请求地址
     * @param headers    请求头部
     * @param subscriber 订阅者（处理请求结果）
     */
    public static Subscription post(String url, Map<String, String> headers, Map<String, Object> params, BaseSubscriber<String> subscriber) {
        return createService(ApiService.class, headers).post(url, params)
                .compose(RxTransformer.<ResponseBody>mainTransformer())
                .map(new StringFunc())
                .subscribe(subscriber);
    }

}

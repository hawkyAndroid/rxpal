package com.hawky.rxpal.internal.subscriber;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;

/**
 * @author [*昨日重现*] lhy_ycu@163.com
 * @since version 1.0
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onError(e, convert(e));
    }

    public abstract void onError(Throwable e, int code);

    private int convert(Throwable e) {
        if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            return ErrorCode.ERROR_CONNECT;
        } else if (e instanceof SocketTimeoutException) {
            return ErrorCode.ERROR_SOCKET_TIMEOUT;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            return ErrorCode.ERROR_PARSE;
        } else {
            return ErrorCode.ERROR_BUSY;
        }
    }

}
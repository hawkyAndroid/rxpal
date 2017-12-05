package com.hawky.rxpal.internal.func;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * ResponseBody --> String
 *
 * @author [*昨日重现*] lhy_ycu@163.com
 * @since version 1.0
 */
public class StringFunc implements Func1<ResponseBody, String> {

    @Override
    public String call(ResponseBody responseBody) {
        String result = null;
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}

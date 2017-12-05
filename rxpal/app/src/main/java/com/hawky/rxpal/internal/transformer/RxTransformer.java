package com.hawky.rxpal.internal.transformer;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 转换器
 *
 * @author [*昨日重现*] lhy_ycu@163.com
 * @since version 1.0
 */
public class RxTransformer {

    public static <T> Observable.Transformer<T, T> mainTransformer() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable.Transformer<T, T> threadTransformer() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io());
            }
        };
    }

}

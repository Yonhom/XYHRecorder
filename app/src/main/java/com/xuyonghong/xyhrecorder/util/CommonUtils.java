package com.xuyonghong.xyhrecorder.util;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xuyonghong on 2017/5/27.
 */

public class CommonUtils {
    public static float dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Observable for a counter
     * @param timeUnit
     * @return
     */
    public static Observable<Long> getCounterObservable(TimeUnit timeUnit) {
        return Observable.interval(0, 1, timeUnit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

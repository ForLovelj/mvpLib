package com.alex.parkyun.http.cache;

import com.alex.parkyun.App;
import com.alex.parkyun.http.network.Net;

/**
 * Created by dth
 * Des:
 * Date: 2018-01-23.
 */

public class AppCache implements IAcache{

    private static AppCache mAppCache;
    private final ACache mACache;

    public static AppCache getInstence() {
        if (mAppCache == null) {
            synchronized (Net.class) {
                if (mAppCache == null) {
                    mAppCache = new AppCache();
                }
            }
        }
        return mAppCache;
    }

    private AppCache() {
        mACache = ACache.get(App.getAppContext());
    }
}

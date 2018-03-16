package com.alex.parkyun.http;

import com.alex.parkyun.base.BaseResponse;
import com.alex.parkyun.bean.HomeBean;
import com.alex.parkyun.http.cache.AppCache;
import com.alex.parkyun.http.cache.IAcache;
import com.alex.parkyun.http.network.IApi;
import com.alex.parkyun.http.network.Net;

import io.reactivex.Observable;

/**
 * Created by dth
 * Des: 统一的数据管理类
 * Date: 2018-01-23.
 */

public final class AppDataManager implements IDataManager{


    private static AppDataManager mAppDataManager;
    private final  IApi           mIApi;
    private final AppCache mAppCache;

    private AppDataManager() {
        mIApi = Net.getInstence().create();
        mAppCache = AppCache.getInstence();
    }

    public static AppDataManager getInstence() {
        if (mAppDataManager == null) {
            synchronized (AppDataManager.class) {
                if (mAppDataManager == null) {
                    mAppDataManager = new AppDataManager();
                }
            }
        }
        return mAppDataManager;
    }

    @Override
    public IApi getApi() {
        return mIApi;
    }

    @Override
    public IAcache getAcache() {
        return mAppCache;
    }

    @Override
    public Observable<BaseResponse<HomeBean>> getHomePageData() {
        return mIApi.getHomePageData();
    }
}

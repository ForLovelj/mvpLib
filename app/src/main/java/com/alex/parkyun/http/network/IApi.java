package com.alex.parkyun.http.network;

import com.alex.parkyun.base.BaseResponse;
import com.alex.parkyun.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by dth
 * Des:所有后台api在此申明
 * Date: 2018-01-23.
 */

public interface IApi {

    /**
     * 首页数据
     * @return
     */
    @POST("/app/goods/shopIndex")
    Observable<BaseResponse<HomeBean>> getHomePageData();
}

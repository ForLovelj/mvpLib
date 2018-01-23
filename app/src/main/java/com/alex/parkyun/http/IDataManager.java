package com.alex.parkyun.http;

import com.alex.parkyun.http.cache.IAcache;
import com.alex.parkyun.http.network.IApi;

/**
 * Created by dth
 * Des:
 * Date: 2018-01-23.
 */

public interface IDataManager extends IApi, IAcache{

    IApi getApi();

    IAcache getAcache();
}

package com.alex.parkyun.presenter;

import com.alex.parkyun.base.BaseObserver;
import com.alex.parkyun.base.BasePresenter;
import com.alex.parkyun.base.BaseResponse;
import com.alex.parkyun.bean.HomeBean;
import com.alex.parkyun.http.AppDataManager;
import com.alex.parkyun.presenter.viewImpl.IMainView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dth
 * Des:
 * Date: 2018-01-23.
 */

public class MainPresenter extends BasePresenter<IMainView>{

    public void getHomePageData() {
        mView.showLoadingView("加载中...");
        AppDataManager.getInstence().getHomePageData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResponse<HomeBean>>(mView) {
                    @Override
                    public void onSuccess(BaseResponse<HomeBean> response) {
                        HomeBean data = response.getData();
                        mView.onSuccess(data);
                    }
                });
    }
}

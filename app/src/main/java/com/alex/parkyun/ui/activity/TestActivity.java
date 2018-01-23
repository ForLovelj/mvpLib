package com.alex.parkyun.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alex.parkyun.R;
import com.alex.parkyun.base.BaseActivity;
import com.alex.parkyun.bean.HomeBean;
import com.alex.parkyun.presenter.MainPresenter;
import com.alex.parkyun.presenter.viewImpl.IMainView;

/**
 * Created by dth
 * Des:
 * Date: 2018-01-23.
 */

public class TestActivity extends BaseActivity<MainPresenter> implements IMainView{

    @Override
    public void onSuccess(HomeBean homeBean) {

    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

        mPresenter.getHomePageData();
        finish();
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int tellMeLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onRetryListener() {

    }

    @Override
    protected View getStatusTargetView() {
        return null;
    }
}

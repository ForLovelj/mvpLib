package com.alex.parkyun.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alex.parkyun.R;
import com.alex.parkyun.base.BaseFragment;
import com.alex.parkyun.presenter.HomePresenter;
import com.alex.parkyun.presenter.viewImpl.IHomeView;

/**
 * Created by dth
 * Des:
 * Date: 2018-01-25.
 */

public class HomeFragment extends BaseFragment<HomePresenter,IHomeView> implements IHomeView{


    @Override
    protected void fetchData() {

    }

    @Override
    protected void init(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int tellMeLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void onRetryListener() {

    }

    @Override
    protected View getStatusTargetView() {
        return null;
    }
}

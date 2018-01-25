package com.alex.parkyun.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alex.parkyun.AppContants;
import com.alex.parkyun.R;
import com.alex.parkyun.adapter.MyFragmentPagerAdapter;
import com.alex.parkyun.base.BaseActivity;
import com.alex.parkyun.bean.HomeBean;
import com.alex.parkyun.presenter.MainPresenter;
import com.alex.parkyun.presenter.viewImpl.IMainView;
import com.alex.parkyun.ui.fragment.HomeFragment;
import com.alex.parkyun.ui.fragment.MineFragment;
import com.alex.parkyun.ui.fragment.NearbyFragment;
import com.alex.parkyun.view.NonSwipeViewPager;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;

import butterknife.BindView;

@Route(path = AppContants.ARouterUrl.MAIN_ACTIVITY, extras = AppContants.LOGIN_INTERCEPTOR)
public class MainActivity extends BaseActivity<MainPresenter, IMainView> implements IMainView {


    @BindView(R.id.noSwipeVp)
    NonSwipeViewPager mNoSwipeVp;
    @BindView(R.id.rb_home)
    RadioButton       mRbHome;
    @BindView(R.id.rb_nearby)
    RadioButton       mRbNearby;
    @BindView(R.id.rb_mine)
    RadioButton       mRbMine;
    @BindView(R.id.maintab_rg)
    RadioGroup        mMaintabRg;
    @BindView(R.id.root)
    LinearLayout      mRoot;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

        initFragment();
        initListener();

    }

    private void initListener() {
        mMaintabRg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_home:
                    mNoSwipeVp.setCurrentItem(0,false);
                    break;
                case R.id.rb_nearby:
                    mNoSwipeVp.setCurrentItem(1,false);
                    break;
                case R.id.rb_mine:
                    mNoSwipeVp.setCurrentItem(2,false);
                    break;

            }
        });
    }

    private void initFragment() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NearbyFragment());
        fragments.add(new MineFragment());

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        mNoSwipeVp.setAdapter(adapter);
        mNoSwipeVp.setOffscreenPageLimit(3);//2n+1
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int tellMeLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onRetryListener() {

    }

    @Override
    protected View getStatusTargetView() {
        return null;
    }


    @Override
    public void onSuccess(HomeBean homeBean) {

    }

}

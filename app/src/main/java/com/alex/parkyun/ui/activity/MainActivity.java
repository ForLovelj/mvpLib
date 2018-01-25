package com.alex.parkyun.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alex.parkyun.R;
import com.alex.parkyun.base.BaseActivity;
import com.alex.parkyun.bean.HomeBean;
import com.alex.parkyun.presenter.MainPresenter;
import com.alex.parkyun.presenter.viewImpl.IMainView;
import com.alex.parkyun.utils.SnackbarUtil;
import com.alex.parkyun.utils.TimeUtils;
import com.alex.parkyun.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter,IMainView> implements IMainView{

    @BindView(R.id.content)
    Button         mContent;
    @BindView(R.id.loading)
    Button         mLoading;
    @BindView(R.id.error)
    Button         mError;
    @BindView(R.id.empty)
    Button         mEmpty;
    @BindView(R.id.zxing)
    Button         mZxing;
    @BindView(R.id.tv)
    TextView       mTv;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.root)
    LinearLayout   mRoot;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

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

        ToastUtils.showToast("重新加载...");
        mPresenter.getHomePageData();
    }

    @Override
    protected View getStatusTargetView() {
        return mRl;
    }


    @OnClick({R.id.content, R.id.loading, R.id.error, R.id.empty, R.id.zxing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.content:
                mPresenter.getHomePageData();
                break;
            case R.id.loading:
                showLoadingView("哈哈");
                mRoot.postDelayed(() -> dissmissLoadingView(), 2000);
                break;
            case R.id.error:
                showErrorView();
                break;
            case R.id.empty:
                showEmptyView();
                break;
            case R.id.zxing:
                SnackbarUtil.IndefiniteSnackbar(mRoot, "hello world!  "+ TimeUtils.getFriendlyTimeSpanByNow(System.currentTimeMillis() - 1000*60*60*48), Snackbar.LENGTH_SHORT, SnackbarUtil.Info).show();
                break;
        }
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        mTv.setText(homeBean.toString());
    }
}

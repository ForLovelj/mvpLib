package com.alex.parkyun.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alex.parkyun.AppContants;
import com.alex.parkyun.R;
import com.alex.parkyun.base.BaseActivity;
import com.alex.parkyun.presenter.LoginPresenter;
import com.alex.parkyun.presenter.viewImpl.ILoginView;
import com.alex.parkyun.utils.StatusBarUtil;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dth
 * Des:
 * Date: 2018-01-25.
 */

@Route(path = AppContants.ARouterUrl.LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity<LoginPresenter, ILoginView> implements ILoginView {

    @BindView(R.id.username)
    EditText     mUsername;
    @BindView(R.id.password)
    EditText     mPassword;
    @BindView(R.id.tv_commit)
    TextView     mTvCommit;
    @BindView(R.id.tvRegister)
    TextView     mTvRegister;
    @BindView(R.id.tv_forget)
    TextView     mTvForget;
    @BindView(R.id.loginForm)
    LinearLayout mLoginForm;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

        StatusBarUtil.setTranslucentForImageView(this, Color.TRANSPARENT, null);
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int tellMeLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onRetryListener() {

    }

    @Override
    protected View getStatusTargetView() {
        return null;
    }

    @OnClick({R.id.tv_commit, R.id.tvRegister, R.id.tv_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                break;
            case R.id.tvRegister:
                break;
            case R.id.tv_forget:
                break;
        }
    }
}

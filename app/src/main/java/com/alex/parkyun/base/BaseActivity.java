package com.alex.parkyun.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.alex.parkyun.R;
import com.alex.parkyun.utils.ToastUtils;
import com.alex.parkyun.widget.BaseLoadingViewHelper;
import com.alex.parkyun.widget.VaryViewHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * Created by dth
 * Des:
 * Date: 2018-01-23.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {

    private   VaryViewHelper        mVaryViewHelper;
    protected Activity              mActivity;
    private   BaseLoadingViewHelper httpNetLoadingViewHelper;
    protected T                     mPresenter;
    private   Unbinder              mUnbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        setContentView(tellMeLayout());

        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        mUnbinder = ButterKnife.bind(this);

        //bundle
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            getBundleExtras(bundle);
        }

        httpNetLoadingViewHelper = new BaseLoadingViewHelper(this);
        if (getStatusTargetView() != null) {
            mVaryViewHelper = new VaryViewHelper.Builder()
                    .setDataView(getStatusTargetView())//如果根部局无效，套一层父布局即可
                    //                    .setLoadingView(LayoutInflater.from(mContext).inflate(R.layout.layout_loadingview, null))
                    .setEmptyView(LayoutInflater.from(mActivity).inflate(R.layout.layout_emptyview, null))
                    .setErrorView(LayoutInflater.from(mActivity).inflate(R.layout.layout_errorview, null))
                    .setRefreshListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onRetryListener();
                        }
                    })
                    .build();
        }

        init(savedInstanceState);

    }

    /**
     * 初始化方法
     */
    protected abstract void init(@Nullable Bundle savedInstanceState);

    /**
     * 传递bundle数据
     * @param bundle
     */
    protected abstract void getBundleExtras(Bundle bundle);

    /**
     * 布局
     * @return
     */
    protected abstract int tellMeLayout();

    protected abstract T getPresenter();


    /**
     * 点击错误页面重新加载数据
     */
    protected  abstract void onRetryListener();

    /**
     *
     * @return
     */
    protected  abstract View getStatusTargetView();

    @Override
    public void addDisposable(Disposable disposable) {
        mPresenter.addDisposable(disposable);
    }

    public void startActivityWithAnim(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.activity_anim_in,R.anim.activity_anim_stay);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_anim_stay,R.anim.activity_anim_out);
    }

    Handler nethandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String text = "";
                    if(msg.obj == null){
                        text = getString(R.string.lib_loading);
                    }else {
                        text = (String) msg.obj;
                    }
                    httpNetLoadingViewHelper.setLoadingText(text);
                    httpNetLoadingViewHelper.showLoadingView();
                    break;
                case 1:
                    httpNetLoadingViewHelper.dismissLoadingView();
                    break;
                default:
            }
        }
    };

    @Override
    public void showLoadingView(String showText){
        Message message = Message.obtain();
        message.what = 0;
        message.obj = showText;
        nethandler.sendMessage(message);
    }


    @Override
    public void dissmissLoadingView(){
        nethandler.sendEmptyMessage(1);
    }

    @Override
    public void showErrorView(){
        dissmissLoadingView();
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showErrorView();
        }
    }

    @Override
    public void showEmptyView(){
        dissmissLoadingView();
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showEmptyView();
        }
    }

    @Override
    public void showDataView(){
        dissmissLoadingView();
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showDataView();
        }
    }

    @Override
    public void showException(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void onStop() {
        super.onStop();
        httpNetLoadingViewHelper.clearView();
    }

    @Override
    public void onDestroy() {

        if (mVaryViewHelper != null){
            mVaryViewHelper.releaseVaryView();
        }

        if (nethandler != null) {
            nethandler.removeCallbacksAndMessages(null);
            nethandler = null;
        }

        if (mPresenter != null) {
            mPresenter.unDisposable();
            mPresenter.detachView();
            mPresenter = null;
        }
        mUnbinder.unbind();
        mActivity = null;
        super.onDestroy();
    }

}

package com.alex.parkyun.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public abstract class BaseFragment <T extends BasePresenter> extends Fragment implements IBaseView{

    private   VaryViewHelper        mVaryViewHelper;
    protected Context               mContext;
    private   BaseLoadingViewHelper httpNetLoadingViewHelper;
    protected T                     mPresenter;
    private   Unbinder              mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(tellMeLayout(), container,false);
        mUnbinder = ButterKnife.bind(this,view);

        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        httpNetLoadingViewHelper = new BaseLoadingViewHelper(getActivity());

        if (getStatusTargetView() != null) {
            mVaryViewHelper = new VaryViewHelper.Builder()
                    .setDataView(getStatusTargetView())//如果根部局无效，套一层父布局即可
                    //                    .setLoadingView(LayoutInflater.from(mContext).inflate(R.layout.layout_loadingview, null))
                    .setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.layout_emptyview, null))
                    .setErrorView(LayoutInflater.from(mContext).inflate(R.layout.layout_errorview, null))
                    .setRefreshListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onRetryListener();
                        }
                    })
                    .build();
        }

        init(view, savedInstanceState);
    }

    /**
     * 初始化方法
     */
    protected abstract void init(View view,@Nullable Bundle savedInstanceState);

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
    public void onDestroyView() {

        if (mVaryViewHelper != null){
            mVaryViewHelper.releaseVaryView();
        }

        if (nethandler != null) {
            nethandler.removeCallbacksAndMessages(null);
            nethandler = null;
        }

        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {

        if (mPresenter != null) {
            mPresenter.unDisposable();
            mPresenter.detachView();
            mPresenter = null;
        }
        mContext = null;
        super.onDestroy();
    }
}

package com.alex.parkyun.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by dth
 * Des:
 * Date: 2018-01-23.
 */

public class BasePresenter<T extends IBaseView> {

    public  T                     mView;
    private CompositeDisposable mCompositeDisposable;


    public void attach(T mView) {
        this.mView = mView;
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }


    public void addDisposable(Disposable d) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(d);
    }

    public void unDisposable() {

        if (this.mCompositeDisposable != null) {
            this.mCompositeDisposable.dispose();
        }
    }
}

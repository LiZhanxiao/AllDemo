package com.listudio.alldemo.dialog;

import android.app.Activity;
import android.os.Handler;

import java.lang.ref.WeakReference;

public class LoadingDialogController {
    private WeakReference<Activity> mActivity = null;
    private int mRefCount = 0;
    private iLoadingViewCreator mLoadingViewCreator;
    private final int kDelayDismissTime = 100;
    private iLoadingView mLoadingView = null;
    private Handler mMainHandler = null;
    public LoadingDialogController(Activity activity, iLoadingViewCreator loadingViewCreator){
        mActivity = new WeakReference<>(activity);
        mLoadingViewCreator = loadingViewCreator;
        mMainHandler = Contant.contant.mainHandler;
    }

    public void show(boolean delayShow){
        Activity activity = mActivity.get();
        if (activity != null) {
            if (mLoadingView == null){
                mLoadingView = mLoadingViewCreator.create(activity);
            }

            if (mRefCount == 0) {
                if (delayShow) {
                    mMainHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (mRefCount != 0) {
                                mLoadingView.show();
                            }
                        }
                    }, kDelayDismissTime);
                } else {
                    mLoadingView.show();
                }
            }
            mRefCount++;
        }
    }

    public void dissmiss(){
        if (mRefCount > 0){
            mRefCount--;
            if (mRefCount == 0){
                mMainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mRefCount == 0) {
                            mLoadingView.dismiss();
                        }
                    }
                }, kDelayDismissTime);
            }
        }
    }
    public void release(){
        mMainHandler=null;
    }
}

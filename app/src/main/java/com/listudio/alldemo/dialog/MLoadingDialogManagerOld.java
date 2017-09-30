package com.listudio.alldemo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;

/**
 * Created by lizuanxiao on 2017/9/30.
 */

public class MLoadingDialogManagerOld {
    private Dialog dialog = null;
    private int count = 0;
    private boolean mNeedShow = false;
    private boolean mNeedDismiss = false;
    private boolean cancelable = false;

    public MLoadingDialogManagerOld() {

    }

    public MLoadingDialogManagerOld getInstance(Activity elsecontext) {
      if (dialog != null && dialog.isShowing()) {
        dismiss();
      }else { //如果为null，则肯定没显示，所以此条件时，dialog肯定没显示，那就创建新的，不管是不是同一个context
        dialog =null;
        dialog = new MLoadingDialog(elsecontext);
        dialog.setCancelable(cancelable);
        count = 1;
        mNeedDismiss = false;
        mNeedShow = false;
      }
      return this;
    }

    public MLoadingDialogManagerOld getInstance(Activity elsecontext, boolean beTransparent) {
      if (dialog != null && dialog.isShowing()) {
        dismiss();
      }else { //如果为null，则肯定没显示，所以此条件时，dialog肯定没显示，那就创建新的，不管是不是同一个context
        dialog =null;
        dialog = new MLoadingDialog(elsecontext,beTransparent);
        dialog.setCancelable(cancelable);
        count = 1;
        mNeedDismiss = false;
        mNeedShow = false;
      }
      return this;
    }

    public void show() {

      if (dialog.isShowing()) {
        count++;
        return;
      }

      try {
        mNeedShow = true;
        dialog.show();
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
      Handler handler = new Handler();
      handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          mNeedShow = false;
          if (mNeedDismiss) {
            dialog.dismiss();
            count = 0;
            mNeedDismiss = false;
          }
        }
      }, 600);
    }

    public void hide() {
      if (dialog != null) {
        dialog.hide();
      }
    }

    public void dismiss() {
      if (dialog == null) return;
      if (!dialog.isShowing()) return;
      if (count > 1) {
        count--;
        return;
      }

      try {
        if (!mNeedShow) {
          dialog.dismiss();
          count = 0;
          mNeedDismiss = false;
        } else {
          mNeedDismiss = true;
        }
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }

    public void setCancelable(boolean cancelable) {
      if (dialog != null) dialog.setCancelable(cancelable);
    }

    public boolean isShowing() {
      if (dialog != null) {
        return dialog.isShowing();
      }
      return false;
    }

    public void setTitle(String title) {
      if (dialog != null) {
        dialog.setTitle(title);
      }
    }

    public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
      if (dialog != null) {
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
      }
    }
}

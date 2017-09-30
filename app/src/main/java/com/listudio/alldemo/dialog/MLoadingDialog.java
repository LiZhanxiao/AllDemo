package com.listudio.alldemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import com.listudio.alldemo.R;

public class MLoadingDialog extends Dialog {

  public static final int DEFAULT_SHOW_DELAY_TIME = 1000;
  private boolean mNeedShow = true;
  private boolean mBeTransparent = true;

  public MLoadingDialog(Context context) {
    super(context, R.style.CustomDialog);
  }

  public MLoadingDialog(Context context, boolean beTransparent) {
    super(context, R.style.CustomDialog);
    mBeTransparent = beTransparent;
  }

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dialog_loading);
    setCanceledOnTouchOutside(false);
    if (mBeTransparent) {
      //在下面这种情况下，后台的activity不会被遮盖，也就是只会遮盖此dialog大小的部分
      WindowManager.LayoutParams a = getWindow().getAttributes();
      a.dimAmount = 0f; // 去背景遮盖
      getWindow().setAttributes(a);
    }
  }

  //	@Override
  //	public boolean onKeyDown(int keyCode, KeyEvent event) {
  //		if (keyCode == KeyEvent.KEYCODE_BACK && mCancelable) {
  //			this.hide();
  //			return true;
  //		}
  //		return super.onKeyDown(keyCode, event);
  //	}

  //默认显示时间
  public void showDelay() {
    showDelay(DEFAULT_SHOW_DELAY_TIME);
  }

  public void showDelay(long time) {
    if (isShowing()) return;
    show();
    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        dismiss();
      }
    }, time);
  }

  @Override
  public void show() {
    try {
      super.show();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  @Override
  public void dismiss() {
    mNeedShow = false;
    if (!isShowing()) {
      return;
    }
    try {
      super.dismiss();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }
}

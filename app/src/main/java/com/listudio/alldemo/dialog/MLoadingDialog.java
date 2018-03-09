package com.listudio.alldemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.listudio.alldemo.R;

public class MLoadingDialog extends Dialog implements iLoadingView{

  public static final int DEFAULT_SHOW_DELAY_TIME = 1000;
  private int layoutId = R.layout.dialog_loading;
  private boolean mNeedShow = true;
  private boolean mBeTransparent = true;

  public MLoadingDialog(Context context) {
    super(context, R.style.CustomDialog);
  }
  public MLoadingDialog(Context context, boolean beTransparent) {
    super(context, R.style.CustomDialog);
    mBeTransparent = beTransparent;
  }

  /**
   *
   * @param context
   * @param layoutId 默认是'R.layout.dialog_login_layout'
   */
  public MLoadingDialog(Context context, @LayoutRes int layoutId){
    super(context,R.style.CustomDialog);
    this.layoutId = layoutId;
  }
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(layoutId);
    setCanceledOnTouchOutside(false);
    if (mBeTransparent) {
      //在下面这种情况下，后台的activity不会被遮盖，也就是只会遮盖此dialog大小的部分
      WindowManager.LayoutParams a = getWindow().getAttributes();
      a.dimAmount = 0f; // 去背景遮盖
      getWindow().setAttributes(a);
    }

  }

  /**
   *
   * @param content 默认是'登录中...'
   */
  public void showText(String content){
    try {
      TextView text = (TextView) findViewById(R.id.login_text);
      text.setText(content);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  //默认显示时间
  public void showDelay() {
    showDelay(DEFAULT_SHOW_DELAY_TIME);
  }

  public void showDelay(long time) {
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        if(mNeedShow) {
          show();
        }
      }
    }, time);
  }

  @Override
  public void show() {
    try{
      super.show();
    }
    catch (Throwable throwable){
      throwable.printStackTrace();
    }
  }

  @Override
  public void dismiss() {
    mNeedShow = false;
    if(!isShowing()) {
      return;
    }
    try {
      super.dismiss();
    }
    catch (Throwable throwable){
      throwable.printStackTrace();
    }
  }
}

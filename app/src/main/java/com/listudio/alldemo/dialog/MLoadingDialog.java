package com.listudio.alldemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import com.listudio.alldemo.R;

public class MLoadingDialog extends Dialog {

  public static final int DEFAULT_SHOW_DELAY_TIME = 1000;
  private boolean mNeedShow = true;
  private boolean mBeTransparent = true;

  public static class Builder {
    private Context context;
    private View contentView;

    public Builder(Context context) {
      this.context = context;
    }

    public MLoadingDialog create() {

      LayoutInflater inflater = (LayoutInflater) context

          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      // instantiate the dialog with the custom Theme

      MLoadingDialog dialog = new MLoadingDialog(context, true);

      return dialog;
    }
  }

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
}

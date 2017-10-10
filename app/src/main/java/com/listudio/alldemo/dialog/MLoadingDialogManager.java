package com.listudio.alldemo.dialog;

import android.app.Activity;
import android.os.Handler;
import com.listudio.alldemo.utils.ActivityStack;

/**
 * Created by lizuanxiao on 2017/9/30.
 */

public class MLoadingDialogManager {
  private MLoadingDialog dialog;
  private Activity activity = null;
  //最好放到全局类中初始化
  private ActivityStack activityStack;
  private Handler handler;
  private boolean canShow = true;//500ms内不允许重复显示
  /**
   *
   */
  private int flag = -1;

  public static final class Builder {
    private MLoadingDialogManager manager;

    public Builder() {
      this.manager = new MLoadingDialogManager();
      this.manager.activityStack = new ActivityStack();
    }

    public Builder withDelay() {
      manager.handler = new Handler();
      return this;
    }

    public MLoadingDialogManager build() {
      return manager;
    }
  }

  private void dialogSingleton() {
    System.out.println("Dialog||反复调用");
    if (activity == null) {
      flag = 0;
      activity = activityStack.topActivity();
      dialog = new MLoadingDialog(activity);
    }
    if (activity.getClass().equals(activityStack.topActivity().getClass())) {
      flag = 1;
    } else {
      /**
       * 切换了页面
       * dialog in OldActivity isShow?dismiss:continue
       * 置空activity
       * 递归
       */
      if (dialog.isShowing()) {
        dialog.dismiss();
      }
      dialog = null;
      activity = null;
      dialogSingleton();
    }
  }

  public void show() {
    dialogSingleton();
    switch (flag) {
      case -1:
        break;
      case 0:
        dialog.show();
        break;
      case 1://activity相同 if允许显示
        if (!dialog.isShowing() && canShow) {
          dialog.show();
        }
        break;
    }
  }

  public void dismiss() {
    if (dialog == null || !dialog.isShowing()) return;

    dialog.dismiss();
    if (handler != null) {
      canShow = false;
      handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          canShow = true;
          dialog = null;
          activity = null;
        }
      }, 500);
    }
    System.out.println("Dialog||消失被调用");
  }
}


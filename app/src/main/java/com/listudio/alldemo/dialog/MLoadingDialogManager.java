package com.listudio.alldemo.dialog;

import android.app.Dialog;

/**
 * Created by lizuanxiao on 2017/9/30.
 */

public class MLoadingDialogManager {
  private Dialog dialog;
  public void dismiss(){
    if (dialog==null||!dialog.isShowing()){
      return;
    }

    try{
      dialog.dismiss();
    }catch (Throwable throwable){
      throwable.printStackTrace();
    }
  }
  public void show(){
    try {
      if (dialog != null) {
        dialog.show();
      }
    }catch (Throwable throwable){
      throwable.printStackTrace();
    }
  }
}


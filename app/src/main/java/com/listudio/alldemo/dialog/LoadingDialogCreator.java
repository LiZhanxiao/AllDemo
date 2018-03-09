package com.listudio.alldemo.dialog;

import android.app.Activity;


public class LoadingDialogCreator implements iLoadingViewCreator {
    @Override
    public iLoadingView create(Activity activity) {
        return new MLoadingDialog(activity);
    }
}

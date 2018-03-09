package com.listudio.alldemo.dialog;

import android.os.Handler;

public class Contant {
    public static Contant contant = new Contant();

    public Handler mainHandler;
    //全局初始化掉
    public void init(){
        mainHandler = new Handler();
    }
}

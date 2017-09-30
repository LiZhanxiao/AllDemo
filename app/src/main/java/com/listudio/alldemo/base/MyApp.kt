package com.listudio.alldemo.base

import android.app.Application
import cn.jpush.android.api.JPushInterface

/**
 * Created by lizuanxiao on 2017/7/12.
 */
class MyApp:Application(){
  override fun onCreate() {
    super.onCreate()
    JPushInterface.setDebugMode(true)
    JPushInterface.init(this)
  }
}
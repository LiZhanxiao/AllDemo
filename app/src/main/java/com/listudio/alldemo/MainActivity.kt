package com.listudio.alldemo

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.listudio.alldemo.dialog.MLoadingDialog

/**
 * Created by lizuanxiao on 2017/7/15.
 */
class MainActivity : AppCompatActivity() {
  var button: Button? = null
  var can: Boolean = false
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    button = findViewById(R.id.popup) as Button

    var mHandle = Handler()
    var mDialog = MLoadingDialog(this)

    mDialog.setCancelable(true)


    var count: Int = 0
    var run = object : Runnable {
      override fun run() {
        mDialog.showDelay()
        count++
        if(count<=1)
        mHandle.postDelayed(this, 100)
      }
    }
    button?.setOnClickListener {
      mHandle.postDelayed(run, 100)
    }


  }
}
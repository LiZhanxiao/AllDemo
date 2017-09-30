package com.listudio.alldemo.popupwindow;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.listudio.alldemo.R;

/**
 * Created by lizuanxiao on 2017/7/15.
 */

public class TestPopup extends BasePopupWindow implements View.OnClickListener {
  private TextView text01;
  private TextView text02;
  private TextView text03;
  private LinearLayout layout1;
  private LinearLayout layout2;
  private LinearLayout layout3;

  public TestPopup(Activity context) {
    super(context);
  }

  public TestPopup(Activity context, int w, int h) {
    super(context, w, h);

    text01 = (TextView) findViewById(R.id.text1);
    text02 = (TextView) findViewById(R.id.text2);
    text03 = (TextView) findViewById(R.id.text3);
    layout1 = (LinearLayout) findViewById(R.id.layout_01);
    layout2 = (LinearLayout) findViewById(R.id.layout_02);
    layout3 = (LinearLayout) findViewById(R.id.layout_03);

    setViewClickListener(this, text01, text02, text03);

  }

  @Override
  public View onCreatePopupView() {
    return createPopupById(R.layout.popup);
  }

  @Override
  public View initAnimaView() {
    return findViewById(R.id.layout);
  }

  @Override
  protected Animation initShowAnimation() {
    return getScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f,
        Animation.RELATIVE_TO_SELF, 0.0f);
  }

  @Override
  public View getClickToDismissView() {
    return getPopupWindowView();
  }

  @Override
  protected Animation initExitAnimation() {
    return getScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f,
        Animation.RELATIVE_TO_SELF, 0.0f);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.text1:
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
        break;
      case R.id.text2:
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.VISIBLE);
        break;
      case R.id.text3:
        layout3.setVisibility(View.GONE);
        layout1.setVisibility(View.VISIBLE);
        break;
    }
  }
}

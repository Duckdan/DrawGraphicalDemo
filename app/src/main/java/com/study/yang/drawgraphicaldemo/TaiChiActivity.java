package com.study.yang.drawgraphicaldemo;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

public class TaiChiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_chi);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        TaiChiView taiChiView = (TaiChiView) findViewById(R.id.tcv);
//        taiChiView.startAnimation(rotateAnimation);
        taiChiView.setListener(new TaiChiView.TaiJiListener() {
            @Override
            public void onBlackClick() {
                Toast.makeText(TaiChiActivity.this, "点击了黑色区域", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWriteClick() {
                Toast.makeText(TaiChiActivity.this, "点击了白色区域", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

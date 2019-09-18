package com.study.yang.drawgraphicaldemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 画扇形
     *
     * @param view
     */
    public void drawSector(View view) {
        jumpActivity(SectorActivity.class);
    }

    /**
     * 画内凹图形
     *
     * @param view
     */
    public void drawInternalConcave(View view) {
        jumpActivity(InternalConcaveActivity.class);
    }

    /**
     * 画太极
     *
     * @param view
     */
    public void drawTaiChi(View view) {
        jumpActivity(TaiChiActivity.class);
    }

    public void jumpActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


}

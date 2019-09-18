package com.study.yang.drawgraphicaldemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制圆弧
 */
public class DrawSectorDemo extends View {

    private Paint rectFPaint;

    private Paint circlePaint;

    public DrawSectorDemo(Context context) {
        this(context, null);
    }

    public DrawSectorDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawSectorDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setAntiAlias(true);
        //设置画笔为空
        circlePaint.setStyle(Paint.Style.STROKE);


        rectFPaint = new Paint();
        rectFPaint.setColor(Color.BLUE);
        rectFPaint.setAntiAlias(true);
        //设置画笔为空
        rectFPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0, 0, 800, 800);
        canvas.drawRect(rectF, rectFPaint);
        canvas.drawArc(rectF, -60, 60, false, circlePaint);
        canvas.drawCircle(100, 100, 50, rectFPaint);
    }
}

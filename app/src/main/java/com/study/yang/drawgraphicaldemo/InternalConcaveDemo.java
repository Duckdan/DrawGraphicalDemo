package com.study.yang.drawgraphicaldemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制内凹图形
 */
public class InternalConcaveDemo extends View {

    private Paint rectFPaint;

    private Paint circlePaint;
    private RectF rectF1;
    private RectF rectF2;
    private RectF rectF3;
    private RectF rectF4;

    public InternalConcaveDemo(Context context) {
        this(context, null);
    }

    public InternalConcaveDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InternalConcaveDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);


        rectFPaint = new Paint();
        rectFPaint.setColor(Color.BLUE);
        rectFPaint.setAntiAlias(true);
        rectFPaint.setStyle(Paint.Style.FILL);

        rectF1 = new RectF(-50, 100, 50, 200);
        rectF2 = new RectF(50, 0, 150, 100);
        rectF3 = new RectF(350, 0, 450, 100);
        rectF4 = new RectF(450, 100, 550, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        userArcToApi(canvas);
        drawRing(canvas);
        drawFanLeaf(canvas);
        drawMoon(canvas);
        drawUnion(canvas);
//        drawXor(canvas);

        Path path = new Path();
        path.moveTo(500, 500);
        path.cubicTo(500, 500, 600, 700, 900, 800);
//        path.lineTo(1100, 1100);
        rectFPaint.setColor(Color.RED);
        canvas.drawPath(path, rectFPaint);
    }

    /**
     * 去除两者之间的图形
     *
     * @param canvas
     */
    private void drawXor(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(1200, 1200, 100, Path.Direction.CCW);
        Path path2 = new Path();
        path2.addCircle(1250, 1250, 100, Path.Direction.CCW);
        path1.op(path2, Path.Op.XOR);//去除两者之间的图形
        rectFPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path1, rectFPaint);
    }

    /**
     * 画联合
     *
     * @param canvas
     */
    private void drawUnion(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(900, 500, 100, Path.Direction.CCW);
        Path path2 = new Path();
        path2.addCircle(950, 550, 100, Path.Direction.CCW);
        path1.op(path2, Path.Op.UNION);//联结两条路径
        canvas.drawPath(path1, rectFPaint);
    }

    /**
     * 画月亮
     *
     * @param canvas
     */
    private void drawMoon(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(900, 900, 100, Path.Direction.CCW);
        Path path2 = new Path();
        path2.addCircle(950, 950, 100, Path.Direction.CCW);
        path1.op(path2, Path.Op.REVERSE_DIFFERENCE);//从第二条路径中减去第一条路径
        canvas.drawPath(path1, rectFPaint);
    }

    /**
     * 画扇叶
     *
     * @param canvas
     */
    private void drawFanLeaf(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(600, 600, 100, Path.Direction.CCW);
        Path path2 = new Path();
        path2.addCircle(650, 650, 100, Path.Direction.CCW);
        path1.op(path2, Path.Op.INTERSECT);//两者相交的部分
        canvas.drawPath(path1, rectFPaint);
    }

    /**
     * 画圆环
     *
     * @param canvas
     */
    private void drawRing(Canvas canvas) {
        Path bigPath = new Path();
        bigPath.addCircle(300, 300, 50, Path.Direction.CCW);
        Path smallPath = new Path();
        smallPath.addCircle(300, 300, 25, Path.Direction.CCW);
        bigPath.op(smallPath, Path.Op.DIFFERENCE);  //大减小
        canvas.drawPath(bigPath, rectFPaint);
    }

    /**
     * 用addArc这个API来绘制弧线，这个API更适合来绘制扇形
     *
     * @param canvas
     */
    private void useAddArcApi(Canvas canvas) {
        Path path = new Path();
        path.addArc(rectF1, 90, -90);
        path.lineTo(50, 50);
        path.addArc(rectF2, 180, 90);
        path.lineTo(400, 0);
        path.addArc(rectF3, -90, 90);
        path.lineTo(450, 250);
        path.addArc(rectF4, 180, -90);
        path.lineTo(0, 300);
        canvas.drawPath(path, rectFPaint);
    }

    /**
     * 用arcTo这个API来绘制弧线
     *
     * @param canvas
     */
    private void userArcToApi(Canvas canvas) {
        Path path = new Path();
        //逆时针画弧线
        path.arcTo(rectF1, 90, -90);
        path.lineTo(50, 50);
        //顺时针画弧线
        path.arcTo(rectF2, 180, 90);
        path.lineTo(400, 0);
        //顺时针画弧线
        path.arcTo(rectF3, -90, 90);
        path.lineTo(450, 150);
        //逆时针画曲线
        path.arcTo(rectF4, 180, -90);
        path.lineTo(0, 200);
        canvas.drawPath(path, rectFPaint);
    }

    /**
     * 绘制半角路径
     *
     * @param canvas
     */
    private void drawPath(Canvas canvas) {
        Path path = new Path();
        path.moveTo(50, 0);
        path.lineTo(50, 50);
        path.lineTo(0, 50);
        path.close();
//        canvas.drawPath(path, rectFPaint);

        Path path1 = new Path();
        path1.addArc(rectF1, 0, 90);
//        path.close();
        //path减去path1
        path.op(path1, Path.Op.DIFFERENCE);


        canvas.drawPath(path, rectFPaint);
    }
}

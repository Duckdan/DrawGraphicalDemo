package com.study.yang.drawgraphicaldemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 太极View
 */
public class TaiChiView extends View {
    /**
     * 太极矩形外围
     */
    private RectF taiChiRectF;
    /**
     * 白色路径
     */
    private Path whitePath = new Path();
    private Paint whitePaint = new Paint();
    private Region whiteRegion = new Region();
    /**
     * 黑色路径
     */
    private Path blackPath = new Path();
    private Paint blackPaint = new Paint();
    private Region blackRegion = new Region();
    private float radius;

    private final int BLACK_FLAG = 0;
    private final int WRITE_FLAG = 1;
    private int touchFlag = -1;
    private TaiJiListener mListener;

    public TaiChiView(Context context) {
        this(context, null);
    }

    public TaiChiView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaiChiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化数据
     *
     * @param context
     */
    private void init(Context context) {
        //白色
        whitePaint.setColor(Color.WHITE);
        whitePaint.setAntiAlias(true);
        //黑色
        blackPaint.setColor(Color.BLACK);
        blackPaint.setAntiAlias(true);
    }

    /**
     * 当尺寸改变的时候绘制图形
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int side = w > h ? h : w;
        Region totalRegion = new Region(0, 0, w, h);
        taiChiRectF = new RectF(0, 0, side, side);
        radius = side / 2;
        RectF smallTopRectF = new RectF(radius / 2, 0, radius * 3 / 2, radius);
        RectF smallBottomRectF = new RectF(radius / 2, radius, radius * 3 / 2, side);
        //白色路径添加
        whitePath.addArc(taiChiRectF, 90, 180);
        whitePath.moveTo(radius, 0);
        whitePath.addArc(smallTopRectF, 270, 180);
        whitePath.moveTo(radius, radius);
        //逆时针
        whitePath.arcTo(smallBottomRectF, 270, -180);
        /**
         * 将区域设置为路径和所描述的区域的剪辑。
         * 如果结果区域为非空，则返回true。这就产生了一个区域
         * 这与路径绘制的像素相同（没有抗锯齿）。
         */
        //将白色路径设置
        whiteRegion.setPath(whitePath, totalRegion);
        //黑色路径添加
        blackPath.addArc(taiChiRectF, 270, 180);
        blackPath.moveTo(radius, side);
        blackPath.addArc(smallBottomRectF, 90, 180);
        blackPath.moveTo(radius, radius);
        //逆时针
        blackPath.arcTo(smallTopRectF, 90, -180);
        blackRegion.setPath(blackPath, totalRegion);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //白色
        canvas.drawPath(whitePath, whitePaint);
        canvas.drawCircle(radius, radius / 2, radius / 8, blackPaint);
        //黑色
        canvas.drawPath(blackPath, blackPaint);
        canvas.drawCircle(radius, radius * 3 / 2, radius / 8, whitePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int currentFlag = -1;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                touchFlag = getTouchFlag(x, y);
                currentFlag = touchFlag;
                break;
            case MotionEvent.ACTION_MOVE:
                currentFlag = getTouchFlag(x, y);
                break;
            case MotionEvent.ACTION_UP:
                currentFlag = getTouchFlag(x, y);
                if (null != mListener && currentFlag == touchFlag && currentFlag != -1) {
                    if (currentFlag == BLACK_FLAG) {
                        mListener.onBlackClick();
                    } else if (currentFlag == WRITE_FLAG) {
                        mListener.onWriteClick();
                    }
                }
                touchFlag = currentFlag = -1;
                break;
            case MotionEvent.ACTION_CANCEL:
                touchFlag = currentFlag = -1;
                break;
        }
        Log.e("currentFlag", "-->" + currentFlag);
        return true;
    }

    /**
     * 判断落在哪个区域
     */
    private int getTouchFlag(int x, int y) {
        if (blackRegion.contains(x, y)) {
            return BLACK_FLAG;
        } else if (whiteRegion.contains(x, y)) {
            return WRITE_FLAG;
        }
        return -1;
    }

    public void setListener(TaiJiListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 设置触摸监听
     */
    public interface TaiJiListener {
        void onBlackClick();

        void onWriteClick();
    }

}

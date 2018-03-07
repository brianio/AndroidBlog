package com.geelaro.customview.custom.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by geelaro on 2018/1/29.
 * 贝塞尔曲线 - 二阶
 */

public class Bezier extends View {
    private Paint mPaint;
    private int centerX, centerY;
    private PointF start, end, control;

    public Bezier(Context context) {
        super(context);
        init();
    }

    public Bezier(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        initPaint();
        initPoint();
    }

    void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(60);
    }

    void initPoint() {
        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化位置
        centerX = w / 2;
        centerY = h / 2;

        start.x = centerX - 300;
        start.y = centerY;
        end.x = centerX + 300;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY - 100;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //根据触摸点更新控制点，并重绘
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(control.x, control.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);

        //绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(control.x,control.y,end.x,end.y,mPaint);

        //绘制贝塞尔曲线
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.RED);

        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.quadTo(control.x, control.y, end.x, end.y);

        canvas.drawPath(path, mPaint);
    }
}

package com.geelaro.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by geelaro on 2018/1/26.
 * 一个雷达网布局
 */

public class RadarView extends View {

    private int count = 6;//数据个数
    private float angle = (float) (Math.PI * 2 / count);
    private float radius; //网格最大半径
    private int mHeight; //view高度
    private int mWidth;  //view宽度
    private String[] mTitles = {"a", "b", "c", "d", "e", "f"}; //数据标题
    private double[] mData = {56, 60, 47, 80, 95, 70};//数据
    private float valueMax = 100; //数据最大值
    private Paint mainPaint; //雷达区画笔
    private Paint valuePaint; //数据区画笔
    private Paint textPaint; //文本画笔

    public RadarView(Context context) {
        super(context);
        initPaint();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mainPaint = new Paint();
        mainPaint.setStyle(Paint.Style.STROKE);//描边
        mainPaint.setColor(Color.GRAY);
        mainPaint.setStrokeWidth(3f);
        //
        valuePaint = new Paint();
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        valuePaint.setColor(Color.BLUE);
        //文本
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
        radius = Math.min(w, h) / 2 * 0.9f;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);//移动坐标原点到中心位置
        //绘制正多边形
        drawPolygon(canvas);
        //
        drawLine(canvas);
        //绘制标题
        drawTitle(canvas);
        //绘制区域
        drawRegion(canvas);
    }

    /**
     * 绘制正多边形蜘蛛网
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1); //r是蜘蛛丝之间间隔
        for (int i = 1; i < count; i++) {
            float cruR = r * i; //当前半径
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(cruR, 0);
                } else {
                    float x = (float) (Math.cos(angle * j) * cruR);
                    float y = (float) (Math.sin(angle * j) * cruR);
                    path.lineTo(x, y);
                }
            }
            path.close(); //闭合路径

            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制直线
     */
    private void drawLine(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            float x = (float) (radius * Math.cos(angle * i));
            float y = (float) (radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制文本
     */
    private void drawTitle(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float currentAngle = angle * i;
            float x = (float) ((radius + fontHeight / 2) * Math.cos(currentAngle));
            float y = (float) ((radius + fontHeight / 2) * Math.sin(currentAngle));
            if (currentAngle >= 0 && currentAngle <= Math.PI / 2) { //右下区域
                canvas.drawText(mTitles[i], x, y, textPaint);
            } else if (currentAngle >= Math.PI / 2 && currentAngle <= Math.PI) { //左下区域
                float dis = textPaint.measureText(mTitles[i]);//文本长度
                canvas.drawText(mTitles[i], x - dis, y, textPaint);
            } else if (currentAngle >= Math.PI && currentAngle <= 3 * Math.PI / 2) { //左上区域
                float dis = textPaint.measureText(mTitles[i]); //文本长度
                canvas.drawText(mTitles[i], x - dis, y, textPaint);
            } else if (currentAngle >= 3 * Math.PI / 2 && currentAngle <= 2 * Math.PI) {//右上区域
                canvas.drawText(mTitles[i], x, y, textPaint);
            }
        }

    }

    /**
     * 绘制覆盖区域
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        float lastPosition = 0;
        for (int i = 0; i < count; i++) {
            double percent = mData[i] / valueMax;
            float x = (float) (radius * Math.cos(angle * i) * percent);
            float y = (float) (radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                lastPosition = x;
                path.lineTo(x, 0);
            } else {
                path.lineTo(x, y);
            }
            //绘制小圆点
            canvas.drawCircle(x, y, 10, valuePaint);
        }
        path.lineTo(lastPosition, 0);
        path.close(); //闭合路径
        valuePaint.setAlpha(117);
        canvas.drawPath(path, valuePaint);
    }


    /**
     * 外部接口
     */
    //设置标题
    public void setTitles(String[] titles) {
        mTitles = titles;
    }

    //设置数据
    public void setData(double[] data) {
        mData = data;
    }

    //设置最大数据值
    public void setValueMax(float valueMax) {
        this.valueMax = valueMax;
    }

    //设置蜘蛛网颜色
    public void setMainPaintColor(int color) {
        mainPaint.setColor(color);
    }

    //设置标题文本颜色
    public void setTextPaint(int color) {
        textPaint.setColor(color);
    }

    //设置覆盖区域颜色
    public void setValuePaint(int color) {
        valuePaint.setColor(color);
    }


}

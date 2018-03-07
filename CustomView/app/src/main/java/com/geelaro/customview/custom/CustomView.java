package com.geelaro.customview.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.geelaro.customview.R;

/**
 * Created by geelaro on 2018/1/6.
 * 自定义view
 */

public class CustomView extends View{
    private Paint mPaint;

    public CustomView(Context context) {
        super(context);
        initPaint();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK); //设置画面颜色
        mPaint.setStyle(Paint.Style.STROKE); //设置画笔填充模式为描边
        mPaint.setStrokeWidth(10f); //设置画笔宽度为10px
    }

    //测量当前view大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
    }
    //确定view大小
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    //实际绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(100,100,300,300,mPaint);//绘制一条线

        RectF rectF = new RectF(200,200,400,500);

        canvas.drawRect(rectF,mPaint);//绘制一个矩形

        //绘制一个椭圆

        RectF  rect = new RectF(100,300,1000,800);

        canvas.drawOval(rect,mPaint);
        //圆形
        canvas.drawCircle(800,1000,300,mPaint);
        //圆弧
        RectF rectF1 = new RectF(200,1000,700,1500);
        RectF rectF2 = new RectF(200,1600,700,2100);
        canvas.drawRect(rectF1,mPaint);//先画背景矩形
        canvas.drawRect(rectF2,mPaint);
        //改变画笔
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF1,0,90,true,mPaint);//先画一个有中心点
        canvas.drawArc(rectF2,0,90,false,mPaint);//再绘制一个无中心点的

        //旋转
        canvas.rotate(90);
        canvas.rotate(90,200,200);//后两个参数控制旋转中心

        //缩放
        canvas.scale(1.1f,2.2f);
        canvas.scale(08.f,0.5f,100,300);//缩放中心

        //位移
        canvas.translate(200,200);//移动坐标中心点

        //
        canvas.restoreToCount(3);

        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.ic_launcher);


        mPaint.setTypeface(Typeface.DEFAULT);
        //Path
        Path path = new Path();

        Path src = new Path();


        Matrix matrix = new Matrix();
        path.addPath(src,matrix);

        path.arcTo(new RectF(),20,30,true);


        canvas.drawPath(path,mPaint);

    }

}

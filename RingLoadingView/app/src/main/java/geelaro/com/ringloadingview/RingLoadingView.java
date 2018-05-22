package geelaro.com.ringloadingview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LEE on 2018/5/22.
 * 两色圆环加载view
 */

public class RingLoadingView extends View {
    //第一个颜色 默认为红色
    private int firstColor;
    //第二个颜色，默认为蓝色
    private int secondColor;
    //画笔
    private Paint firstPaint;
    private Paint secondPaint;
    //view大小
    private int mWidth;
    private int mHeight;
    //开始角度
    private int startAngle = 1;

    public RingLoadingView(Context context) {
        this(context, null);
    }

    public RingLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RingLoadingView);

        firstColor = typedArray.getColor(R.styleable.RingLoadingView_firstColor, Color.RED);
        secondColor = typedArray.getColor(R.styleable.RingLoadingView_secondColor, Color.BLUE);
        //
        typedArray.recycle();

        //initial Paint
        firstPaint = new Paint();
        firstPaint.setStyle(Paint.Style.STROKE);
        firstPaint.setStrokeWidth(10f);

        secondPaint = new Paint();
        secondPaint.setStyle(Paint.Style.STROKE);
        secondPaint.setStrokeWidth(10f);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w ;
        mHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth/2, mHeight/2);

        int r = (int) (Math.min(mWidth,mHeight)*0.4);//圆环半径

        @SuppressLint("DrawAllocation")
        RectF rectF = new RectF(-r, -r, r, r);

        //设置第一个圆环颜色
        firstPaint.setColor(firstColor);
        //绘制第一个半圆弧
        canvas.drawArc(rectF, startAngle, 175, false, firstPaint);
        //设置第二个圆环颜色
        secondPaint.setColor(secondColor);
        //绘制第二个半圆弧
        canvas.drawArc(rectF, startAngle + 180, 175, false, secondPaint);

        startAngle+=6;


        if (startAngle == 360) {
            startAngle = 1;
        }
        //开始绘制
        postInvalidate();

    }


    //设置圆环颜色
    public void setFirstColor(int firstColor) {
        this.firstColor = firstColor;
        invalidate();
    }

    public void setSecondColor(int secondColor) {
        this.secondColor = secondColor;
        invalidate();
    }

}

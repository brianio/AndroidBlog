package com.geelaro.customview.pie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by LEE on 2018/1/7.
 */

public class PieView extends View {
    private final static String TAG = PieView.class.getSimpleName();
    private int[] mColor = new int[]{0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private Paint mPaint = new Paint();//画笔
    private int mWidth; //宽度
    private int mHeight; //高度
    private float mStartAngle = 0;
    private List<PieData> mData; //数据

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Log.i(TAG, "width is: " + w + ", height is: " + h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null) {
            return;
        }
        float currentAngle = mStartAngle; //当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2); //把原点移动view中心位置
        float r = (float) ((Math.min(mHeight, mWidth) / 2) * 0.8); //半径
        RectF rectF = new RectF(-r, -r, r, r); //饼状图的区域

        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rectF, currentAngle, pie.getAngle(), true, mPaint);
            currentAngle += pie.getAngle();
        }

    }
    public void setStartAngle(float mStartAngle){
        this.mStartAngle = mStartAngle;
        invalidate(); //刷新
    }
    //设置数据
    public void setData(List<PieData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();//在更改了数据需要重绘界面时要调用invalidate()这个函数重新绘制
    }

    private void initData(List<PieData> mData) {

        if (mData == null || mData.size() == 0) {
            return;//数据异常，直接返回
        }
        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            sumValue += pieData.getValue();

            int j = i % mColor.length;
            pieData.setColor(mColor[j]);
        }
        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            float percent = pie.getValue() / sumValue;
            float angle = percent * 360;
            pie.setPercent(percent);
            pie.setAngle(angle);
            Log.i(TAG, pie.getName() + "的angle: " + angle);
        }
    }
}

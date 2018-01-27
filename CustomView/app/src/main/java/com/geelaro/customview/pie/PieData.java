package com.geelaro.customview.pie;

/**
 * Created by LEE on 2018/1/7.
 */

public class PieData {
    private String name; //姓名
    private float value; //数值
    private float percent;//百分比

    private int color = 0; //颜色
    private float angle = 0;//角度

    public PieData(String name,float value){
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public float getPercent() {
        return percent;
    }

    public int getColor() {
        return color;
    }

    public float getAngle() {
        return angle;
    }

}

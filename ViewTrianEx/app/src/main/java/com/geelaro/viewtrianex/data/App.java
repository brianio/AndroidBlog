package com.geelaro.viewtrianex.data;

/**
 * Created by Administrator on 2016/11/2.
 */

public class App {
    private String aName;
    private int aIcon;

    public App() {
    }

    public App(String name, int icon) {
        this.aName = name;
        this.aIcon = icon;
    }

    public String getaName() {
        return aName;
    }


    public int getaIcon() {
        return aIcon;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public void setaIcon(int aIcon) {
        this.aIcon = aIcon;
    }
}

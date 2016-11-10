package com.geelaro.viewtrianex.data;

/**
 * Created by Administrator on 2016/10/14.
 */

public class Animal {
    private String aName;
    private String aSpeak;
    private int aIcon;

    public Animal() {
    }

    public Animal(String name, String speak, int icon) {
        this.aName = name;
        aSpeak = "";
        this.aSpeak = speak;
        this.aIcon = icon;
    }

    public String getaName() {
        return aName;
    }

    public String getaSpeak() {
        return aSpeak;
    }

    public int getaIcon() {
        return aIcon;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public void setaSpeak(String aSpeak) {
        this.aSpeak = aSpeak;
    }

    public void setaIcon(int aIcon) {
        this.aIcon = aIcon;
    }
}

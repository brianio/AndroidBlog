package com.geelaro.viewtrianex.data;

/**
 * Created by geelaro on 2016/10/26.
 */

public class Data {
    //A people's image
    private int imgId;
    //A people's name
    private String name;
    //A people's speach
    private String speach;

    public Data() {
    }

    public Data(String name, String speach,int imgId) {
        this.imgId = imgId;
        this.name = name;
        this.speach = speach;
    }

    public int getImgId() {
        return imgId;
    }

    public String getName() {
        return name;
    }

    public String getSpeach() {
        return speach;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeach(String speach) {
        this.speach = speach;
    }
}

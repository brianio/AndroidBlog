package com.geelaro.viewtrianex.data;

/**
 * Created by Administrator on 2016/11/2.
 */

public class Book {

    private String bName;
    private String bAuthor;

    public Book() {
    }

    public Book(String name, String author) {
        this.bName = name;
       this.bAuthor=author;
    }

    public String getbName() {
        return bName;
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }


}

package com.brianio.mysqlite;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by Brian on 2016/8/12.
 */
public class News extends DataSupport{
    private int id;
    private String title;
    private String context;
    private Date publishdate;
    private int commentcount;

    // generated getters and setters.


}

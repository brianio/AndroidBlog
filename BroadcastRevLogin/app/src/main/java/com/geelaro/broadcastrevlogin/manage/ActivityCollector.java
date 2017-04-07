package com.geelaro.broadcastrevlogin.manage;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEE on 2017/4/7.
 * 管理程序所有的活动
 */

public class ActivityCollector {
    public static List<Activity> mActivities=new ArrayList<Activity>();

    //add activity
    public static void addActivity(Activity activity){
        mActivities.add(activity);
    }

    // remove activity
    public static void removeActivity(Activity activity){
        mActivities.remove(activity);
    }
    // finish all activities
    public static void finishAll(){
        for (Activity activity:mActivities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}

package com.telchina.wx.base;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zg on 2015/8/12.
 */
public class ActivityCollector {

    public static List<FragmentActivity> activities = new ArrayList<>();

    public static void addActivity(FragmentActivity activity) {
        activities.add(activity);
    }

    public static void removeActivity(FragmentActivity activity) {
        activities.add(activity);
    }

    public static void finishAll() {
        for (FragmentActivity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}

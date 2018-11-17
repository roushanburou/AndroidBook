package com.brkc.activitypractice1;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：活动管理器
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/11/12
 * @update 添加更新的内容
 */
public class ActivityCollector {

    public static List<Activity> activities=new ArrayList<>();      // 定义一个Activity集合

    public static void addActivity(Activity activity){      // 集合中添加Activity的方法
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){       // 集合中移除Activity的方法
        activities.remove(activity);
    }

    public static void finishAll(){     // finish掉所有的activity，退出应用所用到的方法
        for (Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();      // finish掉activity
            }
        }
        activities.clear();     // 清空集合
    }
}

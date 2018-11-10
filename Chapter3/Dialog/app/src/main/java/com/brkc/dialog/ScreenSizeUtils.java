package com.brkc.dialog;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 描述：计算手机屏幕的宽高
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/9/16
 * @update 添加更新的内容
 */
public class ScreenSizeUtils {

    private static ScreenSizeUtils instance = null;
    private int screenWidth, screenHeight;

    public static ScreenSizeUtils getInstance(Context mContext) {
        if (instance == null) {
            synchronized (ScreenSizeUtils.class) {
                if (instance == null)
                    instance = new ScreenSizeUtils(mContext);
            }
        }
        return instance;
    }

    private ScreenSizeUtils(Context mContext) {
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        screenHeight = dm.heightPixels;// 获取屏幕分辨率高度
    }

    //获取屏幕宽度
    public int getScreenWidth() {
        return screenWidth;
    }

    //获取屏幕高度
    public int getScreenHeight() {
        return screenHeight;
    }
}

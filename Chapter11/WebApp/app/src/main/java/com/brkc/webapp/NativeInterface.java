package com.brkc.webapp;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/11/6
 * @update 添加更新的内容
 */
public class NativeInterface {

    private Context mContext;

    NativeInterface(Context mContext){
        this.mContext = mContext;
    }

    @JavascriptInterface
    public void showToast() {
        Toast.makeText(mContext, "getAndroid", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public String getData() {
        return "Android data";
    }

}

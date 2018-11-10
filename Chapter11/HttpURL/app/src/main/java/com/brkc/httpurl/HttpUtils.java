package com.brkc.httpurl;

import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/11/3
 * @update 添加更新的内容
 */
public class HttpUtils {

    static HttpURLConnection conn = null;
    static InputStream in = null;

    public static InputStream getData(final String u) {
        in = null;
        conn = null;
        try {
            // 第一步：实例化URL对象。
            URL url = new URL(u);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = conn.getInputStream();
            } else {
                Log.e("GET", "get请求失败");
            }
        } catch (Exception e) {
            Log.e("GET", "get请求失败");
            e.printStackTrace();
        } finally {

        }
        return in;
    }
}

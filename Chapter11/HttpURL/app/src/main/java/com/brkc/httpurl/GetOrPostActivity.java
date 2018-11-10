package com.brkc.httpurl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetOrPostActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_or_post);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void httpGet(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                BufferedReader reader = null;
                try {
                    // 第一步：实例化URL对象。
                    URL url = new URL("https://www.baidu.com/");
                    // 第二步：实例化HttpUrlConnection对象。
                    conn = (HttpURLConnection) url.openConnection();
                    // 第三步：设置请求连接属性，传递参数等。
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.setDoInput(true);//设置这个连接是否可以写入数据
                    conn.setDoOutput(false);//设置这个连接是否可以输出数据
                    // 第四步：获取返回码判断是否链接成功。
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        // 第五步：读取输入流。
                        Log.e("GET", "get请求成功");
                        InputStream in = conn.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(in));
                        final StringBuilder builder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(builder);
                            }
                        });
                    } else {
                        Log.e("GET", "get请求失败");
                    }
                } catch (Exception e) {
                    Log.e("GET", "get请求失败");
                    e.printStackTrace();
                } finally {
                    // 第六步：关闭链接。
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }

    public void httpPost(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    // 第一步：实例化URL对象。
                    URL url = new URL("https://www.baidu.com/");
                    // 第二步：实例化HttpUrlConnection对象。
                    conn = (HttpURLConnection) url.openConnection();
                    // 第三步：设置请求连接属性，传递参数等。
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.setDoInput(false);//设置这个连接是否可以写入数据
                    conn.setDoOutput(true);//设置这个连接是否可以输出数据
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");//设置消息的类型
                    conn.connect();// 连接，从上述至此的配置必须要在connect之前完成，实际上它只是建立了一个与服务器的TCP连接
                    // 第五步：发送输出流。
                    OutputStream out = conn.getOutputStream();
                    BufferedOutputStream bos = new BufferedOutputStream(out);//缓冲字节流包装字节流
                    bos.write("name=admin&password=123456".getBytes());//把这个字节数组的数据写入缓冲区中
                    bos.flush();//刷新缓冲区，发送数据
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 第六步：关闭链接。
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }
}

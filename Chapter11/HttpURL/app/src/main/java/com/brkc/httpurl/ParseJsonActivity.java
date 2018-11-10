package com.brkc.httpurl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ParseJsonActivity extends AppCompatActivity {


    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void jsonObParseJson(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream in = HttpUtils.getData("http://192.168.43.51/province.json");
                String json = getJsonString(in);
                parse(json);
            }
        }).start();
    }

    // 获取Json数据
    private String getJsonString(InputStream in) {
        StringBuilder builder = new StringBuilder();
        try {
            // GB2312 或 UTF-8 解決读取文件中文乱码
            InputStreamReader reader = new InputStreamReader(in,"GB2312");
            BufferedReader bf = new BufferedReader(reader);
            String line;
            while ((line = bf.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    // 解析Json数据
    private void parse(String jsonData) {
        final StringBuilder builder = new StringBuilder();
        try {
            JSONArray json = new JSONArray(jsonData);
            for (int i = 0; i < json.length(); i++) {
                JSONObject jb = json.getJSONObject(i);
                builder.append(jb.getInt("id") + "  :  " + jb.getString("name") + "\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(builder.toString());
            }
        });
    }
}

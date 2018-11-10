package com.brkc.savedatademo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KeyValueActivity extends AppCompatActivity {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_value);
        tvData = (TextView) findViewById(R.id.tv_data);
    }

    // 数据写入 SharedPreference
    public void onKeyValueSave(View view) {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString("name", "胡一菲");
        editor.putString("gender", "女博士");
        editor.putInt("age", 28);
        editor.putBoolean("married", false);
        editor.apply();
    }

    // 从 SharedPreference 读取数据
    public void onKeyValueRead(View view) {
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        String name = pref.getString("name", "");
        String gender = pref.getString("gender", "");
        int age = pref.getInt("age", 0);
        boolean married = pref.getBoolean("false", false);
        tvData.setText(name + "\n" + gender + "\n" + age + "\n" + married + "\n");
    }
}

package com.brkc.activitypractice2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView tvUser;
    private TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvUser = (TextView) findViewById(R.id.tvUser);
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        Intent intent = getIntent();
        String data1 = intent.getStringExtra("param1");
        String data2 = intent.getStringExtra("param2");
        if (data1 != null)
            tvUser.setText("亲爱的 "+ data1 +"，欢迎您");
        if (data2 != null)
            tvPassword.setText("您输入的密码为：" + data2);
    }
}

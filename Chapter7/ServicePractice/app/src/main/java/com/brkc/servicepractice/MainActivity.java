package com.brkc.servicepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onOpenForegroundService(View view) {
        Intent intent = new Intent(this,ForegroundService.class);
        intent.putExtra("cmd",0);//0,开启前台服务,1,关闭前台服务
        startService(intent);
    }

    public void onStopForegroundService(View view) {
        Intent intent = new Intent(this,ForegroundService.class);
        intent.putExtra("cmd",1);//0,开启前台服务,1,关闭前台服务
        startService(intent);
    }
}

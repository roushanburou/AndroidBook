package com.bkrc.activity2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainFirstActivity extends AppCompatActivity {

    private static final String TAG = "MainFirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_first);
        Log.e(TAG, "任务栈ID：" + getTaskId());
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }

    public void returnActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startSecondActivity(View view) {
        Intent intent = new Intent(this, MainSecondActivity.class);
        startActivity(intent);
    }
}

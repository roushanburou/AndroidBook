package com.bkrc.activity2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainSecondActivity extends AppCompatActivity {

    private static final String TAG = "MainSecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
        Log.e(TAG, "任务栈ID：" + getTaskId());
    }


    public void startMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startFirstActivity(View view) {
        Intent intent = new Intent(this, MainFirstActivity.class);
        startActivity(intent);
    }
}

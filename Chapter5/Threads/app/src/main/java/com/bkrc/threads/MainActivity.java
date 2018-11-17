package com.bkrc.threads;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new MyThread()).start();
    }

    class MyThread implements Runnable {
        @Override
        public void run() {
            // 处理逻辑
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this,HandlerActivity.class);
        startActivity(intent);
    }

    public void onClick2(View view) {
        Intent intent = new Intent(this,OtherHandlerActivity.class);
        startActivity(intent);
    }

    public void onClick3(View view) {
        Intent intent = new Intent(this,AsyncTaskActivity.class);
        startActivity(intent);
    }
}

package com.brkc.activitypractice1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void startActivity1(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void startActivity3(View view) {
        Intent intent = new Intent(this,Main3Activity.class);
        startActivity(intent);
    }

    public void onFinish(View view) {
        ActivityCollector.finishAll();
    }
}

package com.brkc.activitypractice1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static java.lang.Integer.parseInt;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startActivity2(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    public void startActivity3(View view) {
        Intent intent = new Intent(this,Main3Activity.class);
        startActivity(intent);
    }
}

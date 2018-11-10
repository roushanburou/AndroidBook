package com.brkc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {

    private TextView text_view;
    public static final String TAG = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text_view = (TextView) findViewById(R.id.text_view);
        Intent intent = getIntent();
        String data = intent.getStringExtra(MainActivity.TAG);
        if (data != null)
            text_view.setText(data);
    }

    public void finish(View view) {
        finish();
    }

    public void setResultFinish(View view) {
        Intent intent = new Intent();
        intent.putExtra(TAG, "Hello MainActivity！");
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(TAG, "Hello MainActivity！");
        setResult(RESULT_OK,intent);
        finish();
    }
}

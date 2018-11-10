package com.brkc.savedatademo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFilesSave(View view) {
        Intent intent = new Intent(this,FilesSaveActivity.class);
        startActivity(intent);
    }

    public void onKeyValueSave(View view) {
        Intent intent = new Intent(this,KeyValueActivity.class);
        startActivity(intent);
    }
}

package com.brkc.json;

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


    public void startFastJsonActivity(View view) {
        startActivity(new Intent(this,FastJsonActivity.class));
    }

    public void startNativeJsonPraseActivity(View view) {
        startActivity(new Intent(this,NativeJsonPraseActivity.class));
    }

    public void startGsonActivity(View view) {
        startActivity(new Intent(this,GsonActivity.class));
    }
}

package com.brkc.bdpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btnOffline;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOffline = (Button) findViewById(R.id.btnOffline);

        btnOffline.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent("com.nyl.nativebroadcast.ForceOfflineReceiver");
        sendBroadcast(intent);
    }
}

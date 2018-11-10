package com.brkc.broadcastreceiver;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSysBroadcastReceiver(View view) {
        Intent intent = new Intent(this, SysBroadcastReceiverActivity.class);
        startActivity(intent);
    }

    public void onSendBroadcast(View view) {
        Intent intent = new Intent(this, SendBroadcastActivity.class);
        startActivity(intent);
    }
}

package com.bkrc.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bkrc.listview.custom.CustomerLVActivity;
import com.bkrc.listview.simple.SimpleLVActivity;
import com.bkrc.listview.video.VideoLVActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == button){
            Intent intent = new Intent(this,SimpleLVActivity.class);
            startActivity(intent);
        } else if (view == button2){
            Intent intent = new Intent(this,CustomerLVActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this,VideoLVActivity.class);
            startActivity(intent);
        }
    }
}

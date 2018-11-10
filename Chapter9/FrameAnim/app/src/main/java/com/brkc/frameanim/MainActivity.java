package com.brkc.frameanim;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageview;
    AnimationDrawable animationdrawable;
    Button startbt,stopbt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

    }
    void initview()
    {

        imageview =findViewById(R.id.image);
        startbt =findViewById(R.id.start);
        stopbt =findViewById(R.id.stop);
        animationdrawable = (AnimationDrawable) imageview.getDrawable();
        startbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationdrawable.start();
            }
        });
        stopbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationdrawable.stop();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(animationdrawable.isRunning())
        {
            animationdrawable.stop();
        }
    }
}

package com.brkc.threadpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn4;

    private boolean isRedButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn4 = (Button) findViewById(R.id.btn4);

        btn4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btn4.removeCallbacks(null); // 移除原有线程，提升性能
                btn4.setBackground(getDrawable(R.drawable.up_button_y_selector));
                isRedButton = true;
                btn4.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isRedButton)
                            btn4.setBackground(getDrawable(R.drawable.up_button_r_selector));
                    }
                }, 1000);
                return false;
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRedButton = false;
                btn4.removeCallbacks(null); // 移除原有线程，提升性能
                btn4.setBackground(getDrawable(R.drawable.up_button_g_selector));
            }
        });
    }
}

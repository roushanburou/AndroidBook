package com.bkrc.threads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OtherHandlerActivity extends AppCompatActivity {

    private Button btn_post;
    private Button btn_postDelay;
    private Button btn_runOnUIThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_handler);
        btn_post = (Button) findViewById(R.id.btn_post);
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        btn_post.post(new Runnable() {
                            @Override
                            public void run() {
                                btn_post.setText("我已更新");
                            }
                        });
                    }
                }).start();
            }
        });

        btn_postDelay = (Button) findViewById(R.id.btn_postDelay);
        btn_postDelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        btn_postDelay.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn_postDelay.setText("我已更新");
                            }
                        },1000);
                    }
                }).start();
            }
        });

        btn_runOnUIThread = (Button) findViewById(R.id.btn_runOnUIThread);
        btn_runOnUIThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btn_runOnUIThread.setText("我已更新");
                            }
                        });
                    }
                }).start();
            }
        });
    }

}

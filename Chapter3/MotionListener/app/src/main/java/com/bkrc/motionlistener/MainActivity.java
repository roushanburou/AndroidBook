package com.bkrc.motionlistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button5;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 第一种基于监听器的事件处理模型 1、onClickListener(new View.OnClickListener())
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "我是NO.1", Toast.LENGTH_SHORT).show();
            }
        });
        // 第一种基于监听器的事件处理模型 2、onClickListener(new MyOnClickListener())
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new MyOnClickListener());
        // 第一种基于监听器的事件处理模型 3onClickListener(this)
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);

        // 自定义监听
        textView = (TextView) findViewById(R.id.textview1);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1）定义事件监听器
                // 2）实现下载操作的工具类
                // 3）在 main 函数中模拟事件源
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DownloadUtils.getInstance().download(new DownloadListener() {
                                @Override
                                public void onDownloading(final int progress) {
                                    textView.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            textView.setText("当前下载的进度为：" + progress);
                                        }
                                    });
                                }

                                @Override
                                public void onDownloaded() {
                                    textView.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            textView.setText("下载完成");
                                        }
                                    });
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "我是NO.3", Toast.LENGTH_SHORT).show();
    }

    public void myClick(View view) {
        Toast.makeText(this, "我是第三种模型onClick", Toast.LENGTH_SHORT).show();
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "我是NO.2", Toast.LENGTH_SHORT).show();
        }
    }


    boolean isRepeat = false;
    // 第二种基于回调的事件处理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 监听回退按钮
        // 添加 回退两次才能退出 功能
        if ( keyCode == KeyEvent.KEYCODE_BACK ){
            if ( !isRepeat ){
                isRepeat = true;
                Toast.makeText(this, "请再次点击回退", Toast.LENGTH_SHORT).show();
            } else {
                return super.onKeyDown(keyCode,event);
            }
        }
        return false;
    }
}

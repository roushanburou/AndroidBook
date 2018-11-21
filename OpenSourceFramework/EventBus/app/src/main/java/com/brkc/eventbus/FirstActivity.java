package com.brkc.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FirstActivity extends AppCompatActivity {
    private TextView tv_title;
    private Button bt_eventbus_send_main;
    private Button bt_eventbus_send_sticky;
    private TextView tv_eventbus_send_result;

    boolean isFirstFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        initView();

        initData();

        initListener();
    }

    private void initListener() {
        // 主线程发送数据按钮点击事件处理
        bt_eventbus_send_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 4 发送消息
                EventBus.getDefault().post(new MessageEvent("主线程发送过来的数据"));

                // 结束当前页面
                finish();
            }
        });

        // 接收粘性事件数据按钮的点击事件处理
        bt_eventbus_send_sticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isFirstFlag) {

                    isFirstFlag = false;

                    // 4 注册
                    EventBus.getDefault().register(FirstActivity.this);
                }

            }
        });
    }


    // 3 接收粘性事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void StickyEventBus(StickyEvent event){
        // 显示接收的数据
        tv_eventbus_send_result.setText(event.msg);
    }

    private void initData() {
        tv_title.setText("EventBus发送数据页面");
    }

    private void initView() {
        tv_title = (TextView)findViewById(R.id.tv_title);
        bt_eventbus_send_main = (Button)findViewById(R.id.bt_eventbus_send_main);
        bt_eventbus_send_sticky = (Button)findViewById(R.id.bt_eventbus_send_sticky);
        tv_eventbus_send_result = (TextView)findViewById(R.id.tv_eventbus_send_result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 5 解注册
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(FirstActivity.this);
    }
}

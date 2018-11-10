package com.brkc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/9/29
 * @update 添加更新的内容
 */
public class MainActivity extends Activity{

    public static final String TAG = "MainActivity";
    private TextView text_view;
    private final int REQUEST_TO_MAIN2 = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_view = (TextView) findViewById(R.id.text_view);
    }

    // 一般启动
    public void normalStartActivity(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    // 带数据的一般启动
    public void normalStartActivityIntentData(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra(TAG,"Hello SecondActivity！");
        startActivity(intent);
    }

    // 带回调结果的启动
    public void startActivityForResult(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivityForResult(intent,REQUEST_TO_MAIN2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_TO_MAIN2:
                if (resultCode == RESULT_OK) {
                    text_view.setText("返回结果：" + data.getStringExtra(Main2Activity.TAG));
                }
                break;
            default:
        }
    }
}

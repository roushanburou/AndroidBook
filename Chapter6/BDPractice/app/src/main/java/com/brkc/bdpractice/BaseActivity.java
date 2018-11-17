package com.brkc.bdpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/11/13
 * @update 添加更新的内容
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BaseActivity", "onCreate: " + getClass().getSimpleName());
        ActivityCollector.addActivity(this);//正在创建的活动添加到活动管理器中
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);//即将销毁的活动从活动管理器中移除
    }
}

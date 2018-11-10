package com.brkc.servicedemo2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onOpenForegroundService(View view) {
        Intent intent = new Intent(this,ForegroundService.class);
        intent.putExtra("cmd",0);//0,开启前台服务,1,关闭前台服务
        startService(intent);
    }

    public void onStopForegroundService(View view) {
        Intent intent = new Intent(this,ForegroundService.class);
        intent.putExtra("cmd",1);//0,开启前台服务,1,关闭前台服务
        startService(intent);
    }

    // 开启IntentService
    public void onStartIntentService(View view) {
        Intent intent = new Intent(this,MyIntentService.class);
        startService(intent);
    }

    // 隐式启动
//    // 方案一：设置应用包名。
//    public void onStartExplicitService(View view) {
//        Intent intent = new Intent("com.brkc.servicedemo2.ForegroundService");
//        intent.putExtra("cmd",0);//0,开启前台服务,1,关闭前台服务
//        intent.setPackage(getPackageName());
//        startService(intent);
//    }
    // 方案二：将隐式启动转化为显示启动。
    public void onStartExplicitService(View view) {
        Intent mIntent = new Intent("com.brkc.servicedemo2.ForegroundService");//辅助Intent
        mIntent.putExtra("cmd",0);//0,开启前台服务,1,关闭前台服务
        final Intent serviceIntent = new Intent(getExplicitIntent(this, mIntent));
        startService(serviceIntent);
    }

    public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);
        // Set the component to be explicit
        explicitIntent.setComponent(component);
        return explicitIntent;
    }

}

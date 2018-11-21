package com.brkc.butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_butterknife)
    public TextView tvButterKnife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        // 初始化标题
        tvTitle.setText("ButterKnife");

        tvButterKnife.setText("我好喜欢butterknife");
    }

    @OnCheckedChanged(R.id.cb_butterknife)
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        if (isChecked)
            Toast.makeText(MainActivity.this, "选中了checkbox", Toast.LENGTH_SHORT).show();
        else Toast.makeText(MainActivity.this, "取消了checkbox", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.bt_butterknife)
    public void btButterKnife(View view) {
        Toast.makeText(MainActivity.this, "点击了button", Toast.LENGTH_SHORT).show();
    }
}

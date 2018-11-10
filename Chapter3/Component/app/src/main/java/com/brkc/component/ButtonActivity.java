package com.brkc.component;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ButtonActivity extends AppCompatActivity {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        button1 = findViewById(R.id.button1);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ButtonActivity.this, ((Button)view).getText() + " 被触发", Toast.LENGTH_SHORT).show();
//            }
//        });
        button1.setOnClickListener(new MyOnClickListener());
    }

    // 1、使用 onClick 方法使用响应事件
    public void onClick(View view) {
        Toast.makeText(this, ((Button)view).getText() + " 被触发", Toast.LENGTH_SHORT).show();
    }



    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (R.id.button1 == view.getId()){
                Toast.makeText(ButtonActivity.this, ((Button)view).getText() + " 被触发", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

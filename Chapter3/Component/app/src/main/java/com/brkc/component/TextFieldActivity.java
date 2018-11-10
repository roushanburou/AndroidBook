package com.brkc.component;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TextFieldActivity extends AppCompatActivity {

    private EditText edit_text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_field);

        // 初始化控件
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.auto_complete_edit_text1);
        // 得到对应的数组
        String[] countries = getResources().getStringArray(R.array.email);
        // 创建一个文本建议提示适配器
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);

        edit_text1 = (EditText) findViewById(R.id.edit_text1);
        edit_text1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handle = false;
                if ( i == KeyEvent.ACTION_DOWN) {
                    Toast.makeText(TextFieldActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    handle = true;
                }
                return handle;
            }
        });
    }
}

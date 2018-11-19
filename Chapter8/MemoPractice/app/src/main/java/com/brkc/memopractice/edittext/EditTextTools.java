package com.brkc.memopractice.edittext;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.brkc.memopractice.MainActivity;
import com.brkc.memopractice.dao.Content;
import com.brkc.memopractice.dao.ContentDatabase;

import java.security.Key;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/11/13
 * @update 添加更新的内容
 */
public class EditTextTools {

    public static void addClearListener(final EditText e1, final Button b1) {

        e1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 监听如果输入串长度大于0那么就显示clear按钮。
                String s1 = s + "";
                if (s.length() > 0) {
                    b1.setVisibility(View.VISIBLE);
                } else {
                    b1.setVisibility(View.INVISIBLE);
                }

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 清空输入框
                e1.setText("");

            }
        });
    }

    public static void addChangeDataListener(final Context context, final UpdateListener listener, final EditText e1) {

        e1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handle = false;
                if (actionId == KeyEvent.ACTION_DOWN) {
                    if (!e1.getText().toString().equals("")) {
                        if (listener == null) {
                            Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                            listener.etUpdate(e1.getText().toString());
                            e1.setText("");
                        }
                        // 关闭键盘
                        KeyboardTools.closeKeyboard(e1,context);
                        handle = true;
                    } else {
                        Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
                    }
                }
                return handle;
            }
        });
    }
}

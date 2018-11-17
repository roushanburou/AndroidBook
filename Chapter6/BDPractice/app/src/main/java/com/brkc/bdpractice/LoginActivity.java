package com.brkc.bdpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText etAccount;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView(); //初始化布局
    }

    private void initView() {
        etAccount = (EditText) findViewById(R.id.etAccount);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //获取用户输入的账号,密码的数据
        String account = etAccount.getText().toString();
        String password = etPassword.getText().toString();
        //如果账号是123且密码是123,就登录成功
        if (account.equals("123") && password.equals("123")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(LoginActivity.this,"账号或密码是无效的",Toast.LENGTH_SHORT).show();
        }
    }
}

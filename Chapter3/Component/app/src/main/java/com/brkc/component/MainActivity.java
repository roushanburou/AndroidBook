package com.brkc.component;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton(View view) {
        Intent intent = new Intent(this,ButtonActivity.class);
        startActivity(intent);
    }

    public void onTextField(View view) {
        Intent intent = new Intent(this,TextFieldActivity.class);
        startActivity(intent);
    }

    public void onCheckbox(View view) {
        Intent intent = new Intent(this,CheckboxActivity.class);
        startActivity(intent);
    }

    public void onRadioButton(View view) {
    }

    public void onToggleButton(View view) {
    }

    public void onSpinner(View view) {
    }

    public void onPickers(View view) {
    }
}

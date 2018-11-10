package com.brkc.component;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class CheckboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_1:
                if (checked)
                    Toast.makeText(this, "我爱吃肉", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "我不爱吃肉", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_2:
                if (checked)
                    Toast.makeText(this, "我爱睡觉", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "我不爱睡觉", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_3:
                if (checked)
                    Toast.makeText(this, "我爱玩游戏", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "我不爱玩游戏", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

package com.hhl.textview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hhl.textview.PathTextView;
import com.hhl.textview.demo.patheffect.R;

public class MainActivity extends AppCompatActivity {
    private PathTextView mPathTextView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPathTextView = (PathTextView) findViewById(R.id.path);
        mEditText = (EditText) findViewById(R.id.edit);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPathTextView.init(mEditText.getText().toString());
            }
        });
    }


}

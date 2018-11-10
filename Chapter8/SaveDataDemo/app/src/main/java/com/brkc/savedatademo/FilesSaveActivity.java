package com.brkc.savedatademo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

public class FilesSaveActivity extends Activity {

    String filename = "myFile";
    String fileContents = "Hello world!你好,世界！";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_save);
    }

    // 写入数据：File
    public void onFiles(View view) {
        File file = new File(getFilesDir(), filename);
        Log.e("TAG",file.getPath());
        try {
            OutputStream os = new FileOutputStream(file.getPath());
            os.write(fileContents.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 写入数据：openFileOutput()
    public void onTextFiles(View view) {
        try {
            OutputStream os = openFileOutput(filename, Context.MODE_PRIVATE);
            os.write(fileContents.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 读取数据
    public void onReadFiles(View view) {
        try {
            InputStream is = openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder content = new StringBuilder();
            String line;
            byte[] bytes = new byte[20];
            while((line = reader.readLine()) != null){
                content.append(line);
            }
            ((Button)view).setText(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.brkc.httpurl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connHttp(View view) {
        Intent intent = new Intent(this,GetOrPostActivity.class);
        startActivity(intent);
    }

    public void parseXML(View view) {
        Intent intent = new Intent(this,ParseXMLActivity.class);
        startActivity(intent);
    }

    public void parseJson(View view) {
        Intent intent = new Intent(this,ParseJsonActivity.class);
        startActivity(intent);
    }
}

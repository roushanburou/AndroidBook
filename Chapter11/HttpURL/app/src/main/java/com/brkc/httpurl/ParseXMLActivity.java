package com.brkc.httpurl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParseXMLActivity extends AppCompatActivity {

    TextView tv;

    StringBuilder person = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_xml);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void pullParseXML(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream in = HttpUtils.getData("http://192.168.43.51/person.xml");
                parse(in);
            }
        }).start();
    }

    public void parse(final InputStream in) {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            int eventType = parser.getEventType();
            String id = "";
            String name = "";
            String age = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String node = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (node.equals("id")) {
                            id = parser.nextText();
                        } else if (node.equals("name")) {
                            name = parser.nextText();
                        } else if (node.equals("age")) {
                            age = parser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (node.equals("person")) {
                            person.append("id：" + id + "\n" + "name：" + name + "\n" + "age：" + age + "\n\n");
                        }
                        break;
                    default:
                        break;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(person);
                    }
                });
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

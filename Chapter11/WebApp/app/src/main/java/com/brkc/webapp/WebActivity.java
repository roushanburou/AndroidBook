package com.brkc.webapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.webView);
    }

    public void openWeb(View view) {
        WebSettings webSettings = webView.getSettings();
        //支持双击-前提是页面要支持才显示
//        webView.getSettings().setUseWideViewPort(true);
        //支持缩放按钮-前提是页面要支持才显示
//        webView.getSettings().setBuiltInZoomControls(true);

        // 设置支持javaScript脚步语言
        webSettings.setJavaScriptEnabled(true);

        // 设置客户端-不跳转到默认浏览器中
        webView.setWebViewClient(new WebViewClient());
        // 设置浏览器的渲染机制
        webView.setWebChromeClient(new WebChromeClient());
//        webView.loadUrl("http://www.baidu.com");
        webView.loadUrl("http://192.168.43.51/JavaAndJavaScriptCall.html");
        // 设置支持js调用java
        webView.addJavascriptInterface(new NativeInterface(this),"Android");
    }


    public void javaCallJs(View view) {
        webView.loadUrl("javascript:javaCallJs()");
    }

    public void javaCallJs1(View view) {
        webView.loadUrl("javascript:javaCallJs('SuperMan')");
    }

    public void showDialog(View view) {
        webView.loadUrl("javascript:showDialog()");
    }
}

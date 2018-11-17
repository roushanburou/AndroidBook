package com.bkrc.threads;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {
    private Button btn_start;
    private ProgressBar progressBar;
    private TextView textView;
    class DownloadTask extends AsyncTask<Integer ,Integer,String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setProgress(0);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            for (int i=0;i<=100;i++){
                publishProgress(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            textView.setText("当前下载进度"+values[0]+"%");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        btn_start=findViewById(R.id.btn_start);
        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.textView);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask().execute(100);
            }
        });
    }
}

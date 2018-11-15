package com.hhl.sample;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.format.Time;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity
{
    private DoubleNumberView hourNumberView,minuteNumberView;
    private SecondsNumberView secondsNumberView;

    private int secondsNumber = 0;
    private int minuteNumber = 0;
    private int hourNumber =0;

    int number = 0;

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            secondsNumberView.setText(secondsNumber);//秒
            minuteNumberView.setText(minuteNumber);
            hourNumberView.setText(hourNumber);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hourNumberView = (DoubleNumberView) findViewById(R.id.hour_number_view);
        minuteNumberView = (DoubleNumberView) findViewById(R.id.minute_number_view);
        secondsNumberView = (SecondsNumberView) findViewById(R.id.seconds_number_view);
        // 变成时钟
//        Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
//        t.setToNow(); // 取得系统时间。
//        hourNumber = t.hour; // 0-23
//        minuteNumber = t.minute;
//        number = t.second;

        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                number++;

                if(number<60){
                    secondsNumber = number;//秒
                }else if(number == 60){
                    number = 0;
                    secondsNumber = number;
                    if(minuteNumber <60){
                        minuteNumber +=1;
                    }else if(minuteNumber == 60){
                        minuteNumber = 0;
                        hourNumber +=1;
                    }
                }

                handler.obtainMessage().sendToTarget();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);
    }

}

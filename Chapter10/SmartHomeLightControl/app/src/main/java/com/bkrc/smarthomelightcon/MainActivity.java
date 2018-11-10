package com.bkrc.smarthomelightcon;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private SensorManager mSensorManager;

    private LinearLayout relativeLayout1;
    private ColorPickerView cpv;
    private TextView llutv;
    private Button progress;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private float[] hsv = new float[3];
    private float v;

    private void findViews() {
        relativeLayout1 = (LinearLayout)findViewById( R.id.relativeLayout1 );
        cpv = (ColorPickerView)findViewById( R.id.cpv );
        llutv = (TextView)findViewById( R.id.llutv );
        progress = (Button)findViewById( R.id.progress );
        btn1 = (Button)findViewById( R.id.btn1 );
        btn2 = (Button)findViewById( R.id.btn2 );
        btn3 = (Button)findViewById( R.id.btn3 );

        progress.setOnClickListener( this );
        btn1.setOnClickListener( this );
        btn2.setOnClickListener( this );
        btn3.setOnClickListener( this );
        cpv.setOnColorChangedListenner(new ColorPickerView.OnColorChangedListener() {
            // 手指抬起，选定颜色时
            @Override
            public void onColorChanged(int r, int g, int b) {
                if(r==0 && g==0 && b==0) return;
            }
            // 颜色移动的时候
            @Override
            public void onMoveColor(int r, int g, int b) {
                if(r==0 && g==0 && b==0) return;
                Color.RGBToHSV(r,g,b,hsv);
                progress.setBackgroundColor(Color.HSVToColor(hsv));
            }
        });
    }

    @Override
    public void onClick(View v) {
        if ( v == btn1 ) {

        } else if ( v == btn2 ) {

        } else if ( v == btn3 ) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        // 获取传感器管理器
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // 初始化光照度传感器
        Sensor orientationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this,orientationSensor,
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        v = event.values[0];
        hsv[2] = 1-event.values[0]/1000;
        progress.setBackgroundColor(Color.HSVToColor(hsv));
        llutv.setText("当前光照值：" + v + " xl");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

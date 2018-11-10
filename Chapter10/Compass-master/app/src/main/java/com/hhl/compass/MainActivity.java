package com.hhl.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private SensorEventListener mSensorEventListener;
    private CompassView compassView;
    private float val;

    float[] accelerometerValues = new float[3];
    float[] magneticFieldValues = new float[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BarUtils.setColor(this,getResources().getColor(R.color.black),0);
        compassView = (CompassView) findViewById(R.id.ccv);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

//                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//                    accelerometerValues = event.values;
//                }
//                else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
//                    magneticFieldValues = event.values;
//                }
//                float[] values = new float[3];
//                float[] R = new float[9];
//                SensorManager.getRotationMatrix(R, null, accelerometerValues,
//                        magneticFieldValues);
//                SensorManager.getOrientation(R, values);
//                values[0] = (float) Math.toDegrees(values[0]);
//                compassView.setVal(values[0]);
                // 方向传感器
                compassView.setVal(event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        // 初始化方向传感器
        Sensor orientationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSensorManager.registerListener(mSensorEventListener,orientationSensor,
                SensorManager.SENSOR_DELAY_GAME);

//        // 初始化加速度传感器
//        Sensor accelerometer = mSensorManager
//                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        // 初始化地磁场传感器
//        Sensor magnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//        mSensorManager.registerListener(mSensorEventListener,accelerometer,
//                SensorManager.SENSOR_DELAY_GAME);
//        mSensorManager.registerListener(mSensorEventListener,magnetic,
//                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(mSensorEventListener);
    }
}

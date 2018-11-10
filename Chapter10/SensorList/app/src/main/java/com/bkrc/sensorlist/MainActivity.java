package com.bkrc.sensorlist;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvSensors = (TextView) findViewById(R.id.tv_sensors);
        //获取传感器SensorManager对象
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        tvSensors.append("经检测该手机有" + sensors.size() + "个传感器，它们分别是：\n");
        for (Sensor s: sensors) {

            String tempString = "\n设备名称：" + s.getName() + "\n设备版本：" + s.getVersion()
                    + "\n设备厂商：" +s.getVendor() + "\n\n";
            switch (s.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    tvSensors.append(s.getType() + " 加速度传感器 accelerometer" + tempString);
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    tvSensors.append(s.getType() + " 温度传感器 temperature" + tempString);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    tvSensors.append(s.getType() + " 陀螺仪传感器 gyroscope" + tempString);
                    break;
                case Sensor.TYPE_LIGHT:
                    tvSensors.append(s.getType() + " 光照度传感器 light" + tempString);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    tvSensors.append(s.getType() + " 磁场传感器 magnetic field" + tempString);
                    break;
                case Sensor.TYPE_PRESSURE:
                    tvSensors.append(s.getType() + " 压力传感器 pressure" + tempString);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    tvSensors.append(s.getType() + " 临近传感器 proximity" + tempString);
                    break;
                case Sensor.TYPE_RELATIVE_HUMIDITY:
                    tvSensors.append(s.getType() + " 湿度传感器 humidity" + tempString);
                    break;
                case Sensor.TYPE_ORIENTATION:
                    tvSensors.append(s.getType() + " 方向传感器 orientation" + tempString);
                    break;
                case Sensor.TYPE_GRAVITY:
                    tvSensors.append(s.getType() + " 重力传感器 gravity" + tempString);
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION:
                    tvSensors.append(s.getType() + " 线性加速传感器 linear acceleration" + tempString);
                    break;
                case Sensor.TYPE_ROTATION_VECTOR:
                    tvSensors.append(s.getType() + " 旋转向量传感器 rotation vector" + tempString);
                    break;
                default:
                    tvSensors.append(s.getType() + " 未知传感器" + tempString);
                    break;
            }
        }
    }
}

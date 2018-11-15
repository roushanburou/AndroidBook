package com.hhl.weatherforecast;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhl.weatherforecast.utils.CheckNet;
import com.hhl.weatherforecast.utils.LogUtil;
import com.hhl.weatherforecast.utils.StatusBarUtil;
import com.hhl.weatherforecast.utils.StringUtil;
import com.hhl.weatherforecast.utils.XMLParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private LinearLayout boss;
    private TextView tvCity;
    private TextView tvUpdatetime;
    private TextView textView6;
    private ImageView ivType;
    private TextView tvWendu;
    private ImageView ivNone1;
    private TextView tvType;
    private TextView tvQuality;
    private TextView tvDate;
    private TextView tvSunRise;
    private TextView tvSunFall;
    private ImageView ivTemperature;
    private TextView tvTemperatureHigh;
    private TextView tvTemperatureLow;
    private ImageView ivWindDirection;
    private TextView tvWindDirectionName;
    private TextView tvWindDirection;
    private ImageView ivHumidity;
    private TextView tvIvHumidityName;
    private TextView tvIvHumidity;
    private TextView tvDate1;
    private ImageView ivType1;
    private TextView tvType1;
    private TextView tvHigh1;
    private TextView tvLow1;
    private TextView tvDate2;
    private ImageView ivType2;
    private TextView tvType2;
    private TextView tvHigh2;
    private TextView tvLow2;
    private TextView tvDate3;
    private ImageView ivType3;
    private TextView tvType3;
    private TextView tvHigh3;
    private TextView tvLow3;
    private TextView tvDate4;
    private ImageView ivType4;
    private TextView tvType4;
    private TextView tvHigh4;
    private TextView tvLow4;
    private ImageView ivStart1;
    private ImageView ivStart2;
    private ImageView ivStart3;
    private TextView tvWeatherAdvice;

    private void findViews() {
        // 设置阴天的状态栏 #547282
        StatusBarUtil.setStatusBarColor(this, Color.parseColor("#547282"));
        boss = (LinearLayout) findViewById(R.id.boss);
        tvCity = (TextView)findViewById( R.id.tv_city );
        tvUpdatetime = (TextView)findViewById( R.id.tv_updatetime );
        textView6 = (TextView)findViewById( R.id.textView6 );
        ivType = (ImageView)findViewById( R.id.iv_type );
        tvWendu = (TextView)findViewById( R.id.tv_wendu );
        ivNone1 = (ImageView)findViewById( R.id.iv_none1 );
        tvType = (TextView)findViewById( R.id.tv_type );
        tvQuality = (TextView)findViewById( R.id.tv_quality );
        tvDate = (TextView)findViewById( R.id.tv_date );
        tvSunRise = (TextView)findViewById( R.id.tv_sun_rise );
        tvSunFall = (TextView)findViewById( R.id.tv_sun_fall );
        ivTemperature = (ImageView)findViewById( R.id.iv_temperature );
        tvTemperatureHigh = (TextView)findViewById( R.id.tv_temperature_high );
        tvTemperatureLow = (TextView)findViewById( R.id.tv_temperature_low );
        ivWindDirection = (ImageView)findViewById( R.id.iv_wind_direction );
        tvWindDirectionName = (TextView)findViewById( R.id.tv_wind_direction_name );
        tvWindDirection = (TextView)findViewById( R.id.tv_wind_direction );
        ivHumidity = (ImageView)findViewById( R.id.iv_humidity );
        tvIvHumidityName = (TextView)findViewById( R.id.tv_iv_humidity_name );
        tvIvHumidity = (TextView)findViewById( R.id.tv_iv_humidity );
        tvDate1 = (TextView)findViewById( R.id.tv_date1 );
        ivType1 = (ImageView)findViewById( R.id.iv_type1 );
        tvType1 = (TextView)findViewById( R.id.tv_type1 );
        tvHigh1 = (TextView)findViewById( R.id.tv_high1 );
        tvLow1 = (TextView)findViewById( R.id.tv_low1 );
        tvDate2 = (TextView)findViewById( R.id.tv_date2 );
        ivType2 = (ImageView)findViewById( R.id.iv_type2 );
        tvType2 = (TextView)findViewById( R.id.tv_type2 );
        tvHigh2 = (TextView)findViewById( R.id.tv_high2 );
        tvLow2 = (TextView)findViewById( R.id.tv_low2 );
        tvDate3 = (TextView)findViewById( R.id.tv_date3 );
        ivType3 = (ImageView)findViewById( R.id.iv_type3 );
        tvType3 = (TextView)findViewById( R.id.tv_type3 );
        tvHigh3 = (TextView)findViewById( R.id.tv_high3 );
        tvLow3 = (TextView)findViewById( R.id.tv_low3 );
        tvDate4 = (TextView)findViewById( R.id.tv_date4 );
        ivType4 = (ImageView)findViewById( R.id.iv_type4 );
        tvType4 = (TextView)findViewById( R.id.tv_type4 );
        tvHigh4 = (TextView)findViewById( R.id.tv_high4 );
        tvLow4 = (TextView)findViewById( R.id.tv_low4 );
        ivStart1 = (ImageView)findViewById( R.id.iv_start_1 );
        ivStart2 = (ImageView)findViewById( R.id.iv_start_2 );
        ivStart3 = (ImageView)findViewById( R.id.iv_start_3 );
        tvWeatherAdvice = (TextView)findViewById( R.id.tv_weather_advice );
    }

    @Override
    public void onClick(View v) {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        findViews();
        initData(tvCity.getText().toString());
    }

    private void initData(String city) {
        if(CheckNet.getNetState(this) == CheckNet.NET_NONE){
            Toast.makeText(this, "请连接网络更新。。。", Toast.LENGTH_SHORT).show();
        }
        else {
            new DownloadWebpageText().execute(city);
        }
    }

    // 选择城市
    public void showCityDialog(View view) {
        View v = getLayoutInflater().inflate(R.layout.half_dialog_view, null);
        final EditText editText = (EditText) v.findViewById(R.id.dialog_edit);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("选择城市")//设置对话框的标题
                .setView(v)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = editText.getText().toString();
                        initData(content);
                        tvCity.setText(content.contains("市") ? content : content + "市");
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 访问网络异步进行
     */
    private class DownloadWebpageText extends AsyncTask {

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Object o) {
//            super.onPostExecute(o);
//            LogUtil.i("The response is: " + o);
            WeatherData weatherData = new XMLParser().parseXML((String) o);
            updateWeather(weatherData);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl((String) objects[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        private String downloadUrl(String cityCode) throws IOException {
            final String adress = "http://wthrcdn.etouch.cn/WeatherApi?city=" + cityCode;
            InputStream is = null;
            // Only display the first 500 characters of the retrieved
            // web page content.
            int len = 500;

            try {
                URL url = new URL(adress);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(8000 /* milliseconds */);
                conn.setConnectTimeout(8000 /* milliseconds */);
                conn.setRequestMethod("GET");// 获取状态连接附加值
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                LogUtil.i("The response is: " + response);
                is = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = readIt(is, len);
                return contentAsString;

            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        /**
         * readIT2.0
         * 改进：可以读取整串流
         */
        public String readIt(InputStream stream, int len) throws IOException {
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(stream));
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = reader.readLine())!= null){
                sb.append(str);
            }
            return sb.toString();
        }
    }

    private void updateWeather(WeatherData weatherData) {
        // “第一天”数据区
        tvDate.setText(StringUtil.getDate());
        tvWendu.setText(weatherData.getWendu());
        tvType.setText(weatherData.TODAY.get(3));
        updateWeatherTypeImg(weatherData.TODAY.get(3),ivType);
        tvUpdatetime.setText(weatherData.getUpdatetime());
        tvTemperatureHigh.setText(StringUtil.splitSpace(weatherData.TODAY.get(1))[1]);
        tvTemperatureLow.setText(StringUtil.splitSpace(weatherData.TODAY.get(2))[1]);
        tvQuality.setText(weatherData.getQuality());
        tvSunRise.setText(weatherData.getSunrise_1());
        tvSunFall.setText(weatherData.getSunset_1());
        tvIvHumidity.setText(weatherData.getShidu());
        tvWindDirection.setText(weatherData.getFengxiang() + " " + weatherData.getFengli());

//            int pm25 = Integer.parseInt(todayWeather.getPm25());
        // 预测“第二天”数据区
        tvDate1.setText(weatherData.FORECAST1.get(0));
        tvHigh1.setText(StringUtil.splitSpace(weatherData.FORECAST1.get(1))[1]);
        tvLow1.setText(StringUtil.splitSpace(weatherData.FORECAST1.get(2))[1]);
        tvType1.setText(weatherData.FORECAST1.get(3));
        updateWeatherTypeImg(weatherData.FORECAST1.get(3),ivType1);

        // 预测“第三天”数据区
        tvDate2.setText(weatherData.FORECAST2.get(0));
        tvHigh2.setText(StringUtil.splitSpace(weatherData.FORECAST2.get(1))[1]);
        tvLow2.setText(StringUtil.splitSpace(weatherData.FORECAST2.get(2))[1]);
        tvType2.setText(weatherData.FORECAST2.get(3));
        updateWeatherTypeImg(weatherData.FORECAST2.get(3),ivType2);

        // 预测“第四天”数据区
        tvDate3.setText(weatherData.FORECAST3.get(0));
        tvHigh3.setText(StringUtil.splitSpace(weatherData.FORECAST3.get(1))[1]);
        tvLow3.setText(StringUtil.splitSpace(weatherData.FORECAST3.get(2))[1]);
        tvType3.setText(weatherData.FORECAST3.get(3));
        updateWeatherTypeImg(weatherData.FORECAST3.get(3),ivType3);

        // 预测“第五天”数据区
        tvDate4.setText(weatherData.FORECAST4.get(0));
        tvHigh4.setText(StringUtil.splitSpace(weatherData.FORECAST4.get(1))[1]);
        tvLow4.setText(StringUtil.splitSpace(weatherData.FORECAST4.get(2))[1]);
        tvType4.setText(weatherData.FORECAST4.get(3));
        updateWeatherTypeImg(weatherData.FORECAST4.get(3),ivType4);
    }

    private void updateWeatherTypeImg(String s,ImageView img) {
        if(s!=null) {
            Log.d("type", s);
            switch (s) {
                case "晴":
                    img.setImageResource(R.drawable.sunny);
                    // 设置背景
                    boss.setBackgroundResource(R.drawable.day_sunny);
                    // 设置晴天的状态栏 #3C85D4
                    StatusBarUtil.setStatusBarColor(this, Color.parseColor("#3C85D4"));
                    break;
                case "阴":
                    img.setImageResource(R.drawable.overcast);
                    break;
                case "雾":
                    img.setImageResource(R.drawable.foggy);
                    break;
                case "多云":
                    img.setImageResource(R.drawable.cloudy);
                    break;
                case "小雨":
                    img.setImageResource(R.drawable.light_rain);
                    break;
                case "中雨":
                    img.setImageResource(R.drawable.moderate_rain);
                    break;
                case "大雨":
                    img.setImageResource(R.drawable.heavy_rain);
                    break;
                case "阵雨":
                    img.setImageResource(R.drawable.shower);
                    break;
                case "雷阵雨":
                    img.setImageResource(R.drawable.thunder_rain);
                    break;
                case "雷阵雨加暴":
                    img.setImageResource(R.drawable.rain_hail);
                    break;
                case "暴雨":
                    img.setImageResource(R.drawable.heavy_rain);
                    break;
                case "大暴雨":
                    img.setImageResource(R.drawable.heavy_rain);
                    break;
                case "特大暴雨":
                    img.setImageResource(R.drawable.heavy_rain);
                    break;
                case "阵雪":
                    img.setImageResource(R.drawable.haze);
                    break;
                case "暴雪":
                    img.setImageResource(R.drawable.heavy_snow);
                    break;
                case "大雪":
                    img.setImageResource(R.drawable.heavy_snow);
                    break;
                case "小雪":
                    img.setImageResource(R.drawable.light_snow);
                    break;
                case "雨夹雪":
                    img.setImageResource(R.drawable.sleet);
                    break;
                case "中雪":
                    img.setImageResource(R.drawable.moderate_snow);
                    break;
                case "沙尘暴":
                    img.setImageResource(R.drawable.sandstorm);
                    break;
                default:
                    break;
            }
        }
    }
}

package com.hhl.weatherforecast.utils;

import android.util.Log;

import com.hhl.weatherforecast.WeatherData;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * 作者： 小白攻城狮 on 2017/10/17.
 * 作用： 网页XML转换类
 * 来源：
 */

public class XMLParser {

    /**
     * 要存储的数据量(思路：当存储至上限)
     */
    private int numWeatherData = 9;

    public WeatherData parseXML(String xmlData) {
        WeatherData weatherData = null;
        try {
            int weather = 0;
            weatherData = new WeatherData();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));

            int eventType = xmlPullParser.getEventType();
            Log.e("MWeather", "start parse xml");

            return saveData(weatherData, weather, xmlPullParser, eventType);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherData;
    }

    private WeatherData saveData(WeatherData weatherData, int weather, XmlPullParser xmlPullParser, int eventType) throws XmlPullParserException, IOException {
        while (eventType != xmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                //文档开始位置
                case XmlPullParser.START_DOCUMENT:
                    Log.e("parse", "start doc");
                    break;
                //标签元素开始位置
                case XmlPullParser.START_TAG:
                    // 取出储存单元
                    ArrayList<String> data = getDayData(weather,weatherData);
                    if (xmlPullParser.getName().equals("weather")) {
                        Log.e("weather", "储存已切换");
                        data = getDayData(weather, weatherData);
                        // 二级储存
                        saveSecondData(xmlPullParser, eventType, data);
                        weather++;
                    }
                    else if (xmlPullParser.getName().equals("updatetime")) {
                        eventType = xmlPullParser.next();
                        weatherData.setUpdatetime(xmlPullParser.getText());
                    }
                    else if (xmlPullParser.getName().equals("wendu")) {
                        eventType = xmlPullParser.next();
                        weatherData.setWendu(xmlPullParser.getText());
                    }
                    else if (xmlPullParser.getName().equals("fengli")) {
                        eventType = xmlPullParser.next();
                        weatherData.setFengli(xmlPullParser.getText());
                    }
                    else if (xmlPullParser.getName().equals("shidu")) {
                        eventType = xmlPullParser.next();
                        weatherData.setShidu(xmlPullParser.getText());
                    }
                    else if (xmlPullParser.getName().equals("fengxiang")) {
                        eventType = xmlPullParser.next();
                        weatherData.setFengxiang(xmlPullParser.getText());
                    }
                    else if (xmlPullParser.getName().equals("sunrise_1")) {
                        eventType = xmlPullParser.next();
                        weatherData.setSunrise_1(xmlPullParser.getText());
                    }
                    else if (xmlPullParser.getName().equals("sunset_1")) {
                        eventType = xmlPullParser.next();
                        weatherData.setSunset_1(xmlPullParser.getText());
                    }
                    else if (xmlPullParser.getName().equals("pm25")) {
                        eventType = xmlPullParser.next();
                        weatherData.setPm25(xmlPullParser.getText());
                    }
                    else if (xmlPullParser.getName().equals("aqi")) {
                        eventType = xmlPullParser.next();
                        weatherData.setAqi(xmlPullParser.getText());
                    }
                    else if (xmlPullParser.getName().equals("quality")) {
                        eventType = xmlPullParser.next();
                        weatherData.setQuality(xmlPullParser.getText());
                    }
                    else if (xmlPullParser.getName().equals("majorPollutants")) {
                        eventType = xmlPullParser.next();
                        weatherData.setMajorPollutants(xmlPullParser.getText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    break;
            }
            eventType = xmlPullParser.next();
        }
        weatherData.toString();
        LogUtil.e(weatherData.TODAY.toString());
        return weatherData;
    }

    private void saveSecondData(XmlPullParser xmlPullParser, int eventType, ArrayList<String> data) throws XmlPullParserException, IOException {
        while (data.size() != numWeatherData) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (xmlPullParser.getName().equals("date")) {
                        eventType = xmlPullParser.next();
                        data.add(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("high")) {
                        eventType = xmlPullParser.next();
                        data.add(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("low")) {
                        eventType = xmlPullParser.next();
                        data.add(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("type")) {
                        eventType = xmlPullParser.next();
                        data.add(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("fengxiang")) {
                        eventType = xmlPullParser.next();
                        data.add(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals("fengli")) {
                        eventType = xmlPullParser.next();
                        data.add(xmlPullParser.getText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    break;
            }
            eventType = xmlPullParser.next();
        }
        LogUtil.e(data.toString());
    }

    private ArrayList getDayData(int positon, WeatherData weatherData){
        switch (positon){
            case 0:
                return weatherData.TODAY;
            case 1:
                return weatherData.FORECAST1;
            case 2:
                return weatherData.FORECAST2;
            case 3:
                return weatherData.FORECAST3;
            case 4:
                return weatherData.FORECAST4;
        }
        return null;
    }

}

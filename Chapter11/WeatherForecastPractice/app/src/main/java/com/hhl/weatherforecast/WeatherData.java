package com.hhl.weatherforecast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 作者： 小白攻城狮 on 2017/10/17.
 * 作用：
 * 来源：
 */

public class WeatherData implements Serializable{

     public ArrayList<String> TODAY = new ArrayList<>();
     public ArrayList<String> FORECAST1 = new ArrayList<>();
     public ArrayList<String> FORECAST2 = new ArrayList<>();
     public ArrayList<String> FORECAST3 = new ArrayList<>();
     public ArrayList<String> FORECAST4 = new ArrayList<>();

     private String updatetime;
     private String wendu;
     private String fengli;
     private String shidu;
     private String fengxiang;
     private String sunrise_1;
     private String aqi;
     private String pm25;
     private String sunset_1;
     private String suggest;
     private String quality;
     private String MajorPollutants;

     @Override
     public String toString() {
          return "WeatherData{" +
                  "updatetime='" + updatetime + '\'' +
                  ", wendu='" + wendu + '\'' +
                  ", fengli='" + fengli + '\'' +
                  ", shidu='" + shidu + '\'' +
                  ", fengxiang='" + fengxiang + '\'' +
                  ", sunrise_1='" + sunrise_1 + '\'' +
                  ", aqi='" + aqi + '\'' +
                  ", pm25='" + pm25 + '\'' +
                  ", sunset_1='" + sunset_1 + '\'' +
                  ", suggest='" + suggest + '\'' +
                  ", quality='" + quality + '\'' +
                  ", MajorPollutants='" + MajorPollutants + '\'' +
                  '}';
     }

     public String getAqi() {
          return aqi;
     }

     public void setAqi(String aqi) {
          this.aqi = aqi;
     }

     public String getPm25() {
          return pm25;
     }

     public void setPm25(String pm25) {
          this.pm25 = pm25;
     }

     public String getUpdatetime() {
          return updatetime;
     }

     public void setUpdatetime(String updatetime) {
          this.updatetime = updatetime;
     }

     public String getWendu() {
          return wendu;
     }

     public void setWendu(String wendu) {
          this.wendu = wendu;
     }

     public String getFengli() {
          return fengli;
     }

     public void setFengli(String fengli) {
          this.fengli = fengli;
     }

     public String getShidu() {
          return shidu;
     }

     public void setShidu(String shidu) {
          this.shidu = shidu;
     }

     public String getFengxiang() {
          return fengxiang;
     }

     public void setFengxiang(String fengxiang) {
          this.fengxiang = fengxiang;
     }

     public String getSunrise_1() {
          return sunrise_1;
     }

     public void setSunrise_1(String sunrise_1) {
          this.sunrise_1 = sunrise_1;
     }

     public String getSunset_1() {
          return sunset_1;
     }

     public void setSunset_1(String sunset_1) {
          this.sunset_1 = sunset_1;
     }

     public String getSuggest() {
          return suggest;
     }

     public void setSuggest(String suggest) {
          this.suggest = suggest;
     }

     public String getQuality() {
          return quality;
     }

     public void setQuality(String quality) {
          this.quality = quality;
     }

     public String getMajorPollutants() {
          return MajorPollutants;
     }

     public void setMajorPollutants(String majorPollutants) {
          MajorPollutants = majorPollutants;
     }
}

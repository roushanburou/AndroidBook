package com.hhl.weatherforecast.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 作者： 小白攻城狮 on 2017/10/19.
 * 作用： StringUtil
 * 来源：
 */

public class StringUtil {

    /**
     * 知识补充：1.String的split方法支持正则表达式；
     *           2.正则表达式\s表示匹配任何空白字符，+表示匹配一次或多次。
     */
    public static String[] splitSpace(String str){
        return  str.split("\\s+");
    }

    public static String getDate(){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("MM月dd日");
        return sDateFormat.format(new java.util.Date());
    }

    /**
     * 转化时间格式 ： 例：00:00:00
     * @return
     */
    public static String[] toDate(String time){
        return time.split(":");
    }

    /**
     * 字符串转hex字符串
     */
    public static String strToHex(String str){
        try {
            return String.format("%x", new BigInteger(1, str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 不够位数的在前面补0，保留code的长度位数字
     * @param code
     * @return
     */
    public static String autoGenericCode(String code) {
        String result = "";
        // 保留code的位数
        result = String.format("%0" + code.length() + "d", Integer.parseInt(code));
        return result;
    }

    /**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    public static String autoGenericCode(String code, int num) {
        String result = "";
        // 保留num的位数
        // 0 代表前面补充0
        // num 代表长度为4
        // d 代表参数为正数型
        result = String.format("%0" + num + "d", Integer.parseInt(code));

        return result;
    }

    /**
     * 国际单位转为英码 (单位英里) 保留一位小数
     * @param size
     * @return
     */
    public static String transformInt2Mi(String size){
        int i = (int) (Integer.parseInt(size)*0.6123);
        return i + "";
    }

    public static String transformFloat2Mi(String size){
        float i = Float.parseFloat(size);
        if (i == 0) return "0.0";
        DecimalFormat df = new DecimalFormat(".#");
        i = (float) (i*0.6);
        if (i < 1) return "0" + df.format(i);
        return df.format(i);

    }

    /**
     * 国际单位转为英码 (单位英尺)
     * @param size
     * @return
     */
    public static String transformInt2Ft(String size){
        int i = (int) (Integer.parseInt(size)*3.2808);
        return i + "";
    }

    public static String transformFloat2Ft(String size){
        float i = (float) (Float.parseFloat(size)*3.3);
        return i + "";
    }

    /**
     * 英码转为国际单位 (单位英里)
     * @param size
     * @return
     */
    public static String transformMI2Int(String size){
        int i = (int) (Integer.parseInt(size)/0.6123);
        return i + "";
    }

    public static String transformMI2Float(String size){
        float i = (float) (Float.parseFloat(size)/0.6);
        return i + "";
    }


}

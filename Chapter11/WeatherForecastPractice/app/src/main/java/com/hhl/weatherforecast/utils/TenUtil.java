package com.hhl.weatherforecast.utils;

import static java.lang.Integer.parseInt;

/**
 * 作者： 小白攻城狮 on 2017/11/17.
 * 作用：
 * 来源：
 */

public class TenUtil {

    /*
    * 字节转10进制
    */
    public static int byte2Int(byte b){
        int r = (int) b;
        return r;
    }

    /*
    * 字节数组转10进制字符串数组
    */
    public static String[] bytes2Ints(byte[] b){
        String[] str = new String[b.length];
        for (int i = 0; i < b.length; i++){
            str[i] = Integer.toString((int) b[i]);
        }
        return str;
    }
    /*
    * 字节数组转10进制字符串
    */
    public static String bytes2Str(byte[] b){
        String str = new String();
        for (int i = 0; i < b.length; i++){
            str += " "+ Integer.toString((int) b[i]);
        }
        return str;
    }

    /*
     * 10进制转字节
     */
    public static byte int2Byte(int i){
        byte r = (byte) i;
        return r;
    }

    /*
     * 16进制转字符串
     */
    public static String hexInt2String(byte b){
        return Integer.toString(hexInt2int(b));
    }

    public static String hexInt2String(int b){
        return Integer.toString(hexInt2int(b));
    }

    /*
     * 16进制转整形
     */
    public static int hexInt2int(byte b){
        return parseInt(Integer.toString((int)b),10);
    }

    public static int hexInt2int(int b){
        return parseInt(Integer.toString(b),10);
    }

    /*
     * 字符串转16进制
     * 用途： 如果是从界面上拿过来的数据下发，必须要这么走
     */
    public static int String2hexInt(String b){
        return  Integer.parseInt(Integer.toHexString(parseInt(b)),16);
    }
}

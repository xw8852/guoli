package com.guoli.hotel.utils;

import android.util.Log;


/**
 * ClassName:DigitalUtils <br/>
 * @Description:    数字工具类
 * Date:     2013-3-25 下午9:11:02 <br/>
 * @author   maple
 * @version
 * @since    JDK 1.6
 * @see
 */
public class DigitalUtils {
    
    private static final String TAG = DigitalUtils.class.getSimpleName();

    /***
     *
     * convertToFloat:把字符串对象转换为对应的float值. <br/>
     * @author maple
     * @param numberChar
     * @return
     * @since JDK 1.6
     */
    public static float convertToFloat(String numberChar){
        try {
            return Float.parseFloat(numberChar);
        } catch (NumberFormatException e) {
            Log.i(TAG, "convertToFloat()---> numberChar=" + numberChar + "字符串转换为folat时发生异常!");
            return -1;
        }
    }
    /***
     *
     * convertToInt:把字符串对象转换为对应的int值. <br/>
     * @author maple
     * @param numberChar
     * @return
     * @since JDK 1.6
     */
    public static int convertToInt(String numberChar){
        try {
            return Integer.valueOf(numberChar);
        } catch (NumberFormatException e) {
            Log.i(TAG, "convertToInt()---> numberChar=" + numberChar + "字符串转换为int时发生异常!");
            return -1;
        }
    }
    /***
     *
     * convertToLong:把字符串对象转换为对应的long值. <br/>
     * @author maple
     * @param numberChar
     * @return
     * @since JDK 1.6
     */
    public static long convertToLong(String numberChar){
        try {
            return Long.valueOf(numberChar);
        } catch (NumberFormatException e) {
            Log.i(TAG, "convertToLong()---> numberChar=" + numberChar + "字符串转换为long时发生异常!");
            return -1;
        }
    }
    /***
     *
     * convertToInt:把字符串对象转换为对应的double值. <br/>
     * @author maple
     * @param numberChar
     * @return
     * @since JDK 1.6
     */
    public static double convertToDouble(String numberChar){
        try {
            return Double.valueOf(numberChar);
        } catch (NumberFormatException e) {
            Log.i(TAG, "convertToDouble()---> numberChar=" + numberChar + "字符串转换为double时发生异常!");
            return -1;
        }
    }

    /**
     *
     * convertToString:保留小数点后指定位数的小数. <br/>
     * @author maple
     * @param number    指定小数
     * @param digits    指定位数
     * @return
     * @since JDK 1.6
     */
    public static String convertToString(double number, int digits){
        final String STYLE = "%." + digits + "f";
        return String.format(STYLE, number);
    }
}


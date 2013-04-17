/**
 * Project Name:Guoli
 * File Name:DigitalUtils.java
 * Package Name:com.guoli.hotel.utils
 * Date:2013-3-25下午9:11:02
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.utils;


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
            e.printStackTrace();
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
            return Integer.parseInt(numberChar);
        } catch (NumberFormatException e) {
            e.printStackTrace();
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


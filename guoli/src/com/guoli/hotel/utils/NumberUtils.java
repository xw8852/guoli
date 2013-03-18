/**
 * Project Name:Guoli
 * File Name:NumberUtils.java
 * Package Name:com.guoli.hotel.utils
 * Date:2013-3-18上午10:05:53
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.utils;

import java.text.DecimalFormat;

import android.content.Context;

/**
 * ClassName:NumberUtils <br/>
 * @Description:    数字格式化工具类
 * Date:     2013-3-18 上午10:05:53 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class NumberUtils {

    /**
     * 
     * formatDiscount:对数字保留2位小数. <br/>
     * @author maple
     * @param discount
     * @return
     * @since JDK 1.6
     */
    public static String formatDiscount(double discount, Context ctx) {
        return new DecimalFormat("0.##").format(discount);
    }
}


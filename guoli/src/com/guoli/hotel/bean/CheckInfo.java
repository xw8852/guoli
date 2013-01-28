/**
 * Project Name:SplashActivity
 * File Name:CheckInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-1-28下午7:57:19
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;
/**
 * ClassName:CheckInfo <br/>
 * @Description:    入住日期清单bean
 * Date:     2013-1-28 下午7:57:19 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CheckInfo {

    /**日期*/
    private String date;
    
    /**价格*/
    private String price;
    
    /**双早餐*/
    private String name;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


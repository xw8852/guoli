/**
 * Project Name:Guoli
 * File Name:ItemInfo.java
 * Package Name:com.guoli.hotel.activity.hotel
 * Date:2013-3-19下午3:47:13
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.bean;

/**
 * ClassName:ItemInfo <br/>
 * 
 * @Description: Date: 2013-3-19 下午3:47:13 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class MapItemInfo {

    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + key + ", " + value + "}";
    }

}

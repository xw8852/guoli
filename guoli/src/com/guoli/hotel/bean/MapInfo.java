/**
 * Project Name:Guoli
 * File Name:MapInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-4-7上午10:15:26
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;
/**
 * ClassName:MapInfo <br/>
 * @Description:
 * Date:     2013-4-7 上午10:15:26 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MapInfo {

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
    public boolean equals(Object o) {
        if (!(o instanceof MapInfo)) {
            return false;
        }
        MapInfo info = (MapInfo) o;
        return info.getValue() != null && info.getValue().equals(value);
    }
}


/**
 * Project Name:Guoli
 * File Name:OrderPriceResponse.java
 * Package Name:com.guoli.hotel.net.response.bean
 * Date:2013-4-17下午5:35:13
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.response.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.guoli.hotel.bean.CheckInfo;

/**
 * ClassName:OrderPriceResponse <br/>
 * @Description:    order_ordprice接口返回的对象
 * Date:     2013-4-17 下午5:35:13 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class OrderPriceResponse {

    @SerializedName("roomresult")
    private List<CheckInfo> list;

    public List<CheckInfo> getList() {
        return list;
    }

    public void setList(List<CheckInfo> list) {
        this.list = list;
    }
}


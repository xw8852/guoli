/**
 * Project Name:Guoli
 * File Name:HotelAdapter.java
 * Package Name:com.guoli.hotel.adapter
 * Date:2013-1-11下午1:04:59
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ClassName:HotelAdapter <br/>
 * @Description:    酒店列表适配器
 * Date:     2013-1-11 下午1:04:59 <br/>
 * @author   maple
 * @version  
 * @param <T>
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelAdapter<T> extends AbstractAdapter<T> {

    public HotelAdapter(List<T> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent, LayoutInflater inflater) {
        
        return null;
    }
    
}


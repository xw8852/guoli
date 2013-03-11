/**
 * Project Name:Guoli
 * File Name:AreaInfoAdapter.java
 * Package Name:com.guoli.hotel.adapter
 * Date:2013-3-11下午8:45:05
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
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.bean.AreaInfo;

/**
 * ClassName:AreaInfoAdapter <br/>
 * @Description:    区域选择适配器
 * Date:     2013-3-11 下午8:45:05 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AreaInfoAdapter extends AbstractAdapter<AreaInfo> {

    public AreaInfoAdapter(List<AreaInfo> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent, LayoutInflater inflater) {
        if (data == null) {
            return null;
        }
        if (position < 0 || position > data.size() - 1) {
            return null;
        }
        AreaInfo info = getItem(position);
        if (info == null) {
            return null;
        }
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_single_layout, null);
            holder.nameView = (TextView) convertView.findViewById(R.id.nameView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameView.setText(info.getName());
        return convertView;
    }

    private class ViewHolder{
        TextView nameView;
    }
}


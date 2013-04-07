/**
 * Project Name:Guoli
 * File Name:SingleTextAdapter.java
 * Package Name:com.guoli.hotel.adapter
 * Date:2013-4-7上午10:14:44
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
import com.guoli.hotel.bean.MapInfo;

/**
 * ClassName:SingleTextAdapter <br/>
 * @Description:    item仅有一个元素的listView适配器
 * Date:     2013-4-7 上午10:14:44 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SingleTextAdapter extends AbstractAdapter<MapInfo> {
    
    private String mValue;

    public SingleTextAdapter(List<MapInfo> data, Context context) {
        super(data, context);
    }
    public SingleTextAdapter(List<MapInfo> data, Context context, String value) {
        super(data, context);
        mValue = value;
    }
    @Override
    public View createView(int position, View convertView, ViewGroup parent, LayoutInflater inflater) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_single_layout, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.nameView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MapInfo info = getItem(position);
        if (info != null && mValue != null && mValue.trim().equals(info.getValue().trim())) {
            convertView.setBackgroundResource(R.color.selected_item_bg);
        } else {
            convertView.setBackgroundResource(android.R.color.transparent);
        }
        holder.textView.setText(info == null ? "" : info.getValue());
        return convertView;
    }

    private class ViewHolder{
        TextView textView;
    }
}


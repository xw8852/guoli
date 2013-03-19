/**
 * Project Name:Guoli
 * File Name:DialogItemAdapter.java
 * Package Name:com.guoli.hotel.adapter
 * Date:2013-3-19下午3:59:03
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
import com.guoli.hotel.bean.MapItemInfo;

/**
 * ClassName:DialogItemAdapter <br/>
 * @Description:    listdialog适配器
 * Date:     2013-3-19 下午3:59:03 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MapItemAdapter extends AbstractAdapter<MapItemInfo> {

    public MapItemAdapter(List<MapItemInfo> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent, LayoutInflater inflater) {
        if (data == null || data.size() < 1) {
            return null;
        }
        if (position < 0 || position > data.size() - 1) {
            return null;
        }
        MapItemInfo info = getItem(position);
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
        holder.nameView.setText(info.getValue());
        return convertView;
    }
    
    private class ViewHolder{
        TextView nameView;
    }

}


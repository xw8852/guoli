/**
 * Project Name:Guoli
 * File Name:CityInfoListAdapter.java
 * Package Name:com.guoli.hotel.adapter
 * Date:2013-3-4下午8:15:35
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.net.bean.CityInfo;
import com.guoli.hotel.widget.SosUniversalAdapter;

/**
 * ClassName:CityInfoListAdapter <br/>
 * 
 * @Description: 城市列表适配器 Date: 2013-3-4 下午8:15:35 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class CityInfoListAdapter extends SosUniversalAdapter {

    private HashMap<String, List<CityInfo>> map;
    private Context ctx;
    private CityInfo mInfo;

    public CityInfoListAdapter(HashMap<String, List<CityInfo>> map, Context ctx, CityInfo info) {
        super();
        this.map = map;
        this.ctx = ctx;
        mInfo = info;
    }

    private class ViewHolder {
        TextView nameView;
    }

    @Override
    public int getCount() {
        int size = 0;
        for (String key : map.keySet()) {
            size += map.get(key).size();
        }
        return size;
    }

    @Override
    public CityInfo getItem(int position) {
        List<CityInfo> infos = (List<CityInfo>) getSections()[getSectionForPosition(position)];
        return infos.get(position - getPositionForSection(getSectionForPosition(position)));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    protected void onNextPageRequested(int page) {

    }

    @Override
    protected void bindSectionHeader(View view, int position, boolean displaySectionHeader) {
        TextView title = (TextView) view.findViewById(R.id.nameTitle);
        if (displaySectionHeader) {
            title.setVisibility(View.VISIBLE);
            String title1 = map.keySet().toArray(new String[map.size()])[getSectionForPosition(position)];
            title.setText(title1);
        } else
            title.setVisibility(View.GONE);

    }

    @Override
    public View getAmazingView(int position, View convertView, ViewGroup parent) {
        CityInfo info = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.city_list_item, null);
            ViewHolder holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.nameView);
            convertView.setTag(holder);
        }
        //为当前选中的城市设置背景色
        if (mInfo != null && mInfo.equals(info)) {
            convertView.setBackgroundResource(R.color.selected_item_bg);
        } else {
            convertView.setBackgroundResource(android.R.color.transparent);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.nameView.setText(info.getCityName());
        return convertView;
    }

    @Override
    public void configurePinnedHeader(View header, int position, int alpha) {
        if (header == null)
            return;
        String title = map.keySet().toArray(new String[map.size()])[getSectionForPosition(position)];
        ((TextView) header).setText(title);
        header.invalidate();
    }

    @Override
    public int getPositionForSection(int section) {
        List[] arr = getSections();
        if (section < 0)
            section = 0;
        if (section >= map.size())
            section = map.size() - 1;
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            if (section == i) {
                return c;
            }
            c += arr[i].size();
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        List[] arr = getSections();
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            if (position >= c && position < c + arr[i].size())
                return i;
            c += arr[i].size();
        }
        return -1;
    }

    @Override
    public List[] getSections() {
        List[] arr = new List[map.size()];
        int i = 0;
        for (List<CityInfo> list : map.values()) {
            arr[i] = list;
            i++;
        }
        return arr;
    }

}

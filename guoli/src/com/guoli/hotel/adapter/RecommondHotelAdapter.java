/**
 * Project Name:Guoli
 * File Name:RecommondHotelAdapter.java
 * Package Name:com.guoli.hotel.adapter
 * Date:2013-3-9下午2:42:36
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.adapter;

import java.text.DecimalFormat;
import java.util.List;

import com.guoli.hotel.R;
import com.guoli.hotel.bean.RecommendHotelInfo;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * ClassName:RecommondHotelAdapter <br/>
 * @Description:    推荐列表适配器
 * Date:     2013-3-9 下午2:42:36 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RecommondHotelAdapter extends AbstractAdapter<RecommendHotelInfo> {
    
    private List<RecommendHotelInfo> mList;

    public RecommondHotelAdapter(List<RecommendHotelInfo> data, Context context) {
        super(data, context);
        mList = data;
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent, LayoutInflater inflater) {
        if (mList == null) {
            return null;
        }
        if (position < 0 || position >= getCount()) {
            return null;
        }
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_recommend_hotel, null);
            holder.imgView = (ImageView) convertView.findViewById(R.id.hotel_img);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.hotel_star_level);
            holder.nameView = (TextView) convertView.findViewById(R.id.hotel_name);
            holder.dateView = (TextView) convertView.findViewById(R.id.hotelDate);
            holder.addressView = (TextView) convertView.findViewById(R.id.hotel_address);
            holder.reasonView = (TextView) convertView.findViewById(R.id.hotelReason);
            holder.areaView = (TextView) convertView.findViewById(R.id.hotel_area);
            holder.priceView = (TextView) convertView.findViewById(R.id.hotel_price);
            holder.discountView = (TextView) convertView.findViewById(R.id.hotel_discount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        initItemViewContent(getItem(position), holder);
        return convertView;
    }
    
    private void initItemViewContent(RecommendHotelInfo info, ViewHolder holder){
        if (info == null || holder == null) {
            return;
        }
        //TODO 异步加载酒店图片
        //holder.imgView
        holder.nameView.setText(info.getName());
        initDateView(holder.dateView, info.getDate());
        initRecommandReasonView(holder.reasonView, info.getReason());
        initStarLevelView(holder.ratingBar, info.getLevel());
        initPriceView(holder.priceView, info.getPrice());
        holder.addressView.setText(info.getAddress());
        holder.areaView.setText(info.getZone());
        holder.discountView.setText(formatDiscount(info.getDiscount()));
    }
    
    /**
     * 
     * initPriceView:设置酒店价格. <br/>
     * @author maple
     * @param priceView
     * @param price
     * @since JDK 1.6
     */
    private void initPriceView(TextView priceView, String price) {
        if (priceView == null) {
            return;
        }
        if (TextUtils.isEmpty(price)) {
            priceView.setText("");
            return;
        }
        priceView.setText(price);
    }

    /**
     * 
     * initStarLevelView:设置酒店星级. <br/>
     * @author maple
     * @param ratingBar
     * @param level
     * @since JDK 1.6
     */
    private void initStarLevelView(RatingBar ratingBar, String level) {
        if (ratingBar == null) {
            return;
        }
        if (TextUtils.isEmpty(level)) {
            ratingBar.setNumStars(0);
            return;
        }
        int starNum = 0;
        try {
            starNum = Integer.parseInt(level);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        ratingBar.setNumStars(starNum);
    }

    /**
     * 
     * initRecommandReasonView:设置推荐理由. <br/>
     * @author maple
     * @param view
     * @param reason
     * @since JDK 1.6
     */
    private void initRecommandReasonView(TextView view, String reason) {
        if (view == null || TextUtils.isEmpty(reason)) {
            return;
        }
        reason = String.format(getResources().getString(R.string.recommend_reason), reason);
        view.setText(reason);
    }

    /**
     * 
     * initDateView:设置入住期间的值. <br/>
     * @author maple
     * @param view
     * @param date
     * @since JDK 1.6
     */
    private void initDateView(TextView view, String date){
        if (view == null || TextUtils.isEmpty(date)) {
            return;
        }
        date = String.format(getResources().getString(R.string.date_period), date);
        view.setText(date);
    }
    
    /**
     * 
     * formatDiscount:格式化折扣. <br/>
     * 
     * @author maple
     * @param discount
     * @return
     * @since JDK 1.6
     */
    private String formatDiscount(String discount) {
        if (TextUtils.isEmpty(discount)) {
            return "";
        }
        float tempDiscount = 0;
        try {
            tempDiscount = Float.parseFloat(discount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "";
        }
        String dis = new DecimalFormat("0.##").format(tempDiscount);
        String desc = getResources().getString(R.string.discount_desc);
        return String.format(desc, dis);
    }

    private class ViewHolder{
        ImageView imgView;
        RatingBar ratingBar;
        TextView nameView;
        TextView dateView;
        TextView addressView;
        TextView reasonView;
        TextView areaView;
        TextView priceView;
        TextView discountView;
    }
}


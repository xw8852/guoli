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

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.guoli.hotel.GuoliApplication;
import com.guoli.hotel.R;
import com.guoli.hotel.bean.RecommendHotelInfo;
import com.guoli.hotel.utils.ImageUtil;
import com.msx7.core.Controller;

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
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.starLevelView);
            holder.nameView = (TextView) convertView.findViewById(R.id.hotel_name);
            holder.dateView = (TextView) convertView.findViewById(R.id.hotelDate);
            holder.addressView = (TextView) convertView.findViewById(R.id.hotel_address);
            holder.reasonView = (TextView) convertView.findViewById(R.id.hotelReason);
            holder.areaView = (TextView) convertView.findViewById(R.id.hotelAreaView);
            holder.priceView = (TextView) convertView.findViewById(R.id.hotelPriceView);
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
        Log.d("MSG", GuoliApplication.PIC_PATH_PRE+File.separator+info.getPicPath()+info.getPicName());
        Controller.getApplication().loadThumbnailImage(ImageUtil.getThumbnailImageUrl(info.getPicPath(),info.getPicName()),holder.imgView,R.drawable.hotel_default);
        holder.nameView.setText(info.getName());
        initDateView(holder.dateView, info.getDate());
        initRecommandReasonView(holder.reasonView, info.getReason());
        holder.ratingBar.setRating(info.getLevel());
        holder.priceView.setText(info.getPrice() + "");
        holder.addressView.setText(info.getAddress());
        holder.areaView.setText(info.getZone());
        holder.discountView.setText(formatDiscount(info.getDiscount()));
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
    private String formatDiscount(double discount) {
        String dis = new DecimalFormat("0.##").format(discount);
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


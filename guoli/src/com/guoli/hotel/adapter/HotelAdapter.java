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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.bean.HotelInfo;
import com.guoli.hotel.utils.CallUtils;
import com.guoli.hotel.utils.DigitalUtils;
import com.guoli.hotel.utils.ImageUtil;
import com.msx7.core.Controller;

/**
 * ClassName:HotelAdapter <br/>
 * 
 * @Description: 酒店列表适配器 Date: 2013-1-11 下午1:04:59 <br/>
 * @author maple
 * @version
 * @param <T>
 * @since JDK 1.6
 * @see
 */
public class HotelAdapter extends AbstractAdapter<HotelInfo> {

    public HotelAdapter(List<HotelInfo> data, Context context) {
        super(data, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent, LayoutInflater inflater) {
        if (data == null || data.size() < 1) { return null; }
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_hotel, null);
            holder = new ViewHolder();
            holder.imgView = (ImageView) convertView.findViewById(R.id.hotel_img);
            holder.nameView = (TextView) convertView.findViewById(R.id.hotel_name);
            holder.addressView = (TextView) convertView.findViewById(R.id.hotel_address);
            holder.areaView = (TextView) convertView.findViewById(R.id.hotel_area);
            holder.levelView = (RatingBar) convertView.findViewById(R.id.hotel_star_level);
            holder.priceView = (TextView) convertView.findViewById(R.id.hotel_price);
            holder.discountView = (TextView) convertView.findViewById(R.id.hotel_discount);
            holder.callView = (TextView) convertView.findViewById(R.id.hotel_call);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HotelInfo info = getItem(position);
        holder.nameView.setText(info.getName());
        holder.addressView.setText(info.getAddress());
        holder.areaView.setText(info.getDistrict());
        setStarLevelView(holder.levelView, info.getStarLavel());
        //加载图片
        Controller.getApplication().loadThumbnailImage(ImageUtil.getThumbnailImageUrl(info.getPicPath(), info.getFileName()), holder.imgView,R.drawable.hotel_default);
        int price = info.getPrice();
        if (price != 0) {
            holder.callView.setVisibility(View.GONE);
            holder.priceView.setVisibility(View.VISIBLE);
            holder.discountView.setVisibility(View.VISIBLE);
            holder.priceView.setText(formatPrice(price));
            //折扣
            double discount = info.getDiscount();
            if (discount >= 9.8) {
                holder.discountView.setText("");
            } else {
                String desc = getResources().getString(R.string.discount_desc);
                String temp = DigitalUtils.convertToString(info.getDiscount(), 1);
                holder.discountView.setText(String.format(desc, temp));
            }
            return convertView;
        }
        holder.priceView.setVisibility(View.GONE);
        holder.discountView.setVisibility(View.GONE);
        holder.callView.setVisibility(View.VISIBLE);
        holder.callView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               CallUtils callUtils = new CallUtils(context);
               callUtils.callServer();
            }
        });
        return convertView;
    }
    
    /**
     * 
     * setStarLevelView:设置酒店星级. <br/>
     * @author maple
     * @param ratingBar
     * @param starLevel
     * @since JDK 1.6
     */
    private void setStarLevelView(RatingBar ratingBar, String starLevel){
        if (ratingBar == null) {
            return;
        }
        int num = 0;
        try {
            num = Integer.parseInt(starLevel);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        ratingBar.setNumStars(num);
        ratingBar.setRating(num);
    }

    /**
     * 
     * formatPrice:按照需求格式化价格. <br/>
     * 
     * @author maple
     * @param price
     * @return
     * @since JDK 1.6
     */
    private String formatPrice(int price) {
        String desc = getResources().getString(R.string.price_desc);
        return String.format(desc, price);
    }

   

    private final class ViewHolder {
        /** 酒店缩略图 */
        ImageView imgView;
        /** 酒店名称 */
        TextView nameView;
        /** 酒店地址 */
        TextView addressView;
        /** 酒店区域 */
        TextView areaView;
        /** 酒店星级 */
        RatingBar levelView;
        /** 酒店价格 */
        TextView priceView;
        /** 酒店折扣 */
        TextView discountView;
        /** 电话查询 */
        TextView callView;
    }
}

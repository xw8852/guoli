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

import java.text.DecimalFormat;
import java.util.List;

import com.guoli.hotel.R;
import com.guoli.hotel.bean.HotelInfo;
import com.guoli.hotel.utils.CallUtils;
import com.guoli.hotel.utils.StringUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
public class HotelAdapter<T> extends AbstractAdapter<T> {

    public HotelAdapter(List<T> data, Context context) {
        super(data, context);
    }

    @SuppressWarnings("unchecked")
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
        Object obj = data.get(position);
        if (!(obj instanceof HotelInfo)) { return null; }
        HotelInfo info = (HotelInfo) obj;
        holder.nameView.setText(info.getName());
        holder.addressView.setText(info.getAddress());
        holder.areaView.setText(info.getArea());
        holder.levelView.setNumStars(info.getLevel());
        loadHotelImg(info.getPicPath(), holder.imgView);
        int price = info.getPrice();
        if (price != 0) {
            holder.callView.setVisibility(View.GONE);
            holder.priceView.setVisibility(View.VISIBLE);
            holder.discountView.setVisibility(View.VISIBLE);
            holder.priceView.setText(formatPrice(price));
            holder.discountView.setText(formatDiscount(info.getDiscount()));
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

    /**
     * 
     * formatDiscount:格式化折扣. <br/>
     * 
     * @author maple
     * @param discount
     * @return
     * @since JDK 1.6
     */
    private String formatDiscount(float discount) {
        String dis = new DecimalFormat("0.##").format(discount);
        String desc = getResources().getString(R.string.discount_desc);
        return String.format(desc, dis);
    }

    /**
     * 
     * loadHotelImg:加载酒店图片. <br/>
     * 
     * @author maple
     * @param picPath
     * @param imgView
     * @since JDK 1.6
     */
    private void loadHotelImg(String picPath, ImageView imgView) {
        if (StringUtils.isBlank(picPath) || imgView == null) { return; }
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

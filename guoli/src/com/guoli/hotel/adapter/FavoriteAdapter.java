/**
 * Project Name:SplashActivity
 * File Name:FavoriteAdapter.java
 * Package Name:com.guoli.hotel.adapter
 * Date:2013-1-26下午4:50:33
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
import android.widget.ImageView;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.bean.FavoriteHotelInfo;
import com.guoli.hotel.utils.ImageUtil;
import com.msx7.core.Controller;

/**
 * ClassName:FavoriteAdapter <br/>
 * 
 * @Description: 我的收藏列表适配器 Date: 2013-1-26 下午4:50:33 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class FavoriteAdapter extends AbstractAdapter<FavoriteHotelInfo> {

	public FavoriteAdapter(List<FavoriteHotelInfo> data, Context context) {
		super(data, context);

	}

	@Override
	public View createView(int position, View convertView, ViewGroup parent, LayoutInflater inflater) {
		if (data == null || data.size() < 1) {
			return null;
		}
		FavoriteHotelInfo info = getItem(position);

		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_favorite, null);
			holder.imageView = (ImageView) convertView.findViewById(R.id.hotel_img);
			holder.nameView = (TextView) convertView.findViewById(R.id.hotel_name);
			holder.addressView = (TextView) convertView.findViewById(R.id.hotel_address);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// TODO 接口返回图片地址后调用异步下载接口加载图片
		if (info != null) {
			holder.nameView.setText(info.shopname);
			holder.addressView.setText(info.address);
			Controller.getApplication().loadThumbnailImage(ImageUtil.getThumbnailImageUrl(info.picpath, info.filename),
					holder.imageView, R.drawable.hotel_default);
		}
		return convertView;
	}

	private class ViewHolder {
		ImageView imageView;
		TextView nameView;
		TextView addressView;
	}
}

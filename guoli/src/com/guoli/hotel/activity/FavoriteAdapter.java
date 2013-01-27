package com.guoli.hotel.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guoli.hotel.R;

public class FavoriteAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;

	public FavoriteAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.favorite_item, null);
			viewHolder.favoriteImg = (ImageView) convertView
					.findViewById(R.id.favoriteImg);
			viewHolder.favoriteName = (TextView) convertView
					.findViewById(R.id.favoriteName);
			viewHolder.favoriteAdr = (TextView) convertView
					.findViewById(R.id.favoriteAdr);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	private final class ViewHolder {
		/** 收藏夹酒店图片 */
		ImageView favoriteImg;
		/** 收藏夹酒店名字 */
		TextView favoriteName;
		/** 收藏夹酒店地址 */
		TextView favoriteAdr;

	}

}

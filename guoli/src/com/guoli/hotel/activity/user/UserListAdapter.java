package com.guoli.hotel.activity.user;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guoli.hotel.R;

public class UserListAdapter extends BaseAdapter {

	private Context context;
	private List<UserInfo> infos;

	public UserListAdapter(Context context, List<UserInfo> infos) {
		this.context = context;
		this.infos = infos;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (null == convertView) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.user_select_item, null);
			holder.deleteBtn = (ImageView) convertView.findViewById(R.id.deleteBtn);
			holder.selectBtn = (ImageView) convertView.findViewById(R.id.selectBtn);
			holder.nameView = (TextView) convertView.findViewById(R.id.name);
			holder.selectBtn.setTag(UserSelectActivity.TAG_IS_INVISIBLE);

			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		if (infos != null) {
			holder.nameView.setText(infos.get(position).personname);
		}
		return convertView;
	}

	public class ViewHolder {
		ImageView deleteBtn;
		ImageView selectBtn;
		TextView nameView;
	}

}

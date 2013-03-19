package com.guoli.hotel.activity.user;

import java.util.List;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.user.UserSelectActivity.UserName;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserListAdapter extends BaseAdapter {

	private Context context;
	private List<UserName> names;

	public UserListAdapter(Context context, List<UserName> names) {
		this.context = context;
		this.names = names;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return names.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (null == holder) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.user_select_item, null);
			holder.deleteBtn = (ImageView) convertView.findViewById(R.id.deleteBtn);
			holder.selectBtn = (ImageView) convertView.findViewById(R.id.selectBtn);
			holder.nameView = (TextView) convertView.findViewById(R.id.name);
		}
		if (names != null) {
			holder.nameView.setText(names.get(position).name);
		}
		return convertView;
	}

	public class ViewHolder {
		ImageView deleteBtn;
		ImageView selectBtn;
		TextView nameView;
	}

}

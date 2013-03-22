package com.guoli.hotel.activity.user;

import java.util.HashMap;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoli.hotel.R;
import com.guoli.hotel.bean.FavoriteUserInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.FavoriteUserBean;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.msx7.core.Manager;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

public class UserListAdapter extends BaseAdapter {

	private int mPosition;
	private Dialog mDialog;
	private Context context;
	private List<FavoriteUserInfo> infos;

	public UserListAdapter(Context context, List<FavoriteUserInfo> infos) {
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
			mPosition = position;
			holder.nameView.setText(infos.get(position).personname);
			holder.deleteBtn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					DialogUtils.showDialog(context, "", "are you sure ?", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							String id = infos.get(mPosition).id;
							Request request = new GuoliRequest("user_delperson", new FavoriteUserBean(LoginUtils.uid,
									null, id));
							Manager.getInstance().executePoset(request, delUserListener);

							mDialog = DialogUtils.showProgressDialog(context, "删除中");
						}
					});
				}
			});
		}
		return convertView;
	}

	IResponseListener delUserListener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
			if (null == response) {
				return;
			}

			if (null != mDialog && mDialog.isShowing()) {
				mDialog.cancel();
			}

			Log.d("MSG", "onSuccess:" + response.getData().toString());
			HashMap<String, Object> map = new Gson().fromJson(response.result.toString(),
					new TypeToken<HashMap<String, Object>>() {
					}.getType());
			if ("1".equalsIgnoreCase(map.get("success").toString())) {
				infos.remove(infos.get(mPosition));
				UserListAdapter.this.notifyDataSetChanged();
			}

			Toast.makeText(context, map.get("message").toString(), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onError(Response response) {
			if (null != mDialog && mDialog.isShowing()) {
				mDialog.cancel();
			}
			Log.d("MSG", "onError:" + response.getData().toString());
		}
	};

	public class ViewHolder {
		ImageView deleteBtn;
		ImageView selectBtn;
		TextView nameView;
	}

}

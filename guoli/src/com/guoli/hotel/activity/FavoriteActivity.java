/**
 * Project Name:SplashActivity
 * File Name:FavoriteActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-26下午4:40:45
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
 */

package com.guoli.hotel.activity;

import java.util.HashMap;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoli.hotel.R;
import com.guoli.hotel.activity.hotel.HotelDetailActivity;
import com.guoli.hotel.adapter.FavoriteAdapter;
import com.guoli.hotel.bean.FavoriteHotelInfo;
import com.guoli.hotel.bean.FavoriteInfo;
import com.guoli.hotel.net.GuoliRequest;
import com.guoli.hotel.net.request.bean.HotelRoom;
import com.guoli.hotel.net.request.bean.UserOderListBean;
import com.guoli.hotel.utils.DialogUtils;
import com.guoli.hotel.utils.DigitalUtils;
import com.guoli.hotel.utils.LoginUtils;
import com.guoli.hotel.utils.ToastUtil;
import com.msx7.core.Manager;
import com.msx7.core.command.ErrorCode;
import com.msx7.core.command.IResponseListener;
import com.msx7.core.command.model.Request;
import com.msx7.core.command.model.Response;

/**
 * ClassName:FavoriteActivity <br/>
 * 
 * @Description: 我的收藏页面 Date: 2013-1-26 下午4:40:45 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class FavoriteActivity extends BaseActivity implements OnItemClickListener {

	private ListView mListView;
	private FavoriteInfo favoriteInfo;
	private List<FavoriteHotelInfo> hotelInfos;
	private Dialog dialog;
	private FavoriteAdapter adapter;

	private LinearLayout loadMoreLayout;
	private TextView loadMoreBtn;
	private LinearLayout loadingLayout;

	private static final int PAGE_NUMBER = 1;

	public FavoriteActivity() {
		mLayoutId = R.layout.favorite_layout;
		mTitleTextId = R.string.my_favorite;
		mRightTextId = R.string.exit;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		showLeftBtn();
		showRightBtn();

		requetFavoriteData(setBean(PAGE_NUMBER));
	}

	private UserOderListBean setBean(int i) {
		UserOderListBean bean = new UserOderListBean(LoginUtils.uid, i);
		return bean;
	}

	private void requetFavoriteData(UserOderListBean bean) {
		Request request = new GuoliRequest("user_myfavorite", bean);
		Manager.getInstance().executePoset(request, getFavoriteListener);
		showLoadingDialog(R.string.loading_msg);
	}

	@Override
	protected void findViews() {
		mListView = (ListView) findViewById(R.id.favoriteListView);
		mListView.setOnItemLongClickListener(mItemLongClickLisenter);
		mListView.setOnCreateContextMenuListener(listener);
		mListView.setOnItemClickListener(this);

		loadMoreLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.load_more_foot, null);
		loadingLayout = (LinearLayout) loadMoreLayout.findViewById(R.id.loading);
		loadMoreBtn = (TextView) loadMoreLayout.findViewById(R.id.loadMoreBtn);
		loadMoreBtn.setOnClickListener(loadMoreListener);
		mListView.addFooterView(loadMoreLayout);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (mListView.getAdapter() == null || !(mListView.getAdapter().getCount() > position)) {
			return;
		}

		HotelRoom hotelRoom = setHotelRoom(position);
		Intent intent = new Intent();
		intent.putExtra(HotelDetailActivity.KEY_REQUEST, hotelRoom);
		intent.setClass(FavoriteActivity.this, HotelDetailActivity.class);
		startActivity(intent);
	}

	private HotelRoom setHotelRoom(int position) {
		HotelRoom hotelRoom = new HotelRoom();
		hotelRoom.setStartDate(hotelInfos.get(position).startDate);
		hotelRoom.setEndDate(hotelInfos.get(position).endDate);
		hotelRoom.setId(hotelInfos.get(position).shopid);
		return hotelRoom;
	}

	/**
	 * 
	 * updateListView:更新列表数据. <br/>
	 * 
	 * @author maple
	 * @param list
	 * @since JDK 1.6
	 */
	private void updateListView(FavoriteInfo favoriteInfo) {
		loadingLayout.setVisibility(View.GONE);
		hotelInfos = favoriteInfo.getHotelInfos();
		if (hotelInfos == null || hotelInfos.size() < 1) {
			return;
		}

		if (adapter == null) {
			adapter = new FavoriteAdapter(hotelInfos, this);
			mListView.setAdapter(adapter);
			return;
		} else {
			adapter.addMore(hotelInfos);
		}

		int pageNumber = DigitalUtils.convertToInt(favoriteInfo.getPageno());
		int pageCount = DigitalUtils.convertToInt(favoriteInfo.getPagecount());
		if (pageNumber == pageCount) {
			mListView.removeFooterView(loadMoreLayout);
		} else {
			if (mListView.getFooterViewsCount() == 0) {
				mListView.addFooterView(loadMoreLayout);
			}
			loadMoreLayout.setVisibility(View.VISIBLE);
			loadMoreBtn.setVisibility(View.VISIBLE);
		}
	}

	IResponseListener getFavoriteListener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
			dismissLoadingDialog();
			if (response == null) {
				return;
			}
			Log.d("MSG", "onSuccess:" + response.getData().toString());
			FavoriteInfo infos = new Gson().fromJson(response.result.toString(), FavoriteInfo.class);
			favoriteInfo = infos;

			updateListView(favoriteInfo);
		}

		@Override
		public void onError(Response response) {
			dismissLoadingDialog();
		}
	};

	OnCreateContextMenuListener listener = new OnCreateContextMenuListener() {

		@Override
		public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
			menu.add(0, 0, 0, "取消收藏");
		}
	};

	private FavoriteHotelInfo hotelInfo;

	private OnItemLongClickListener mItemLongClickLisenter = new OnItemLongClickListener() {
		@Override
		public boolean onItemLongClick(AdapterView<?> adapterView, View parent, int position, long id) {
			hotelInfo = (FavoriteHotelInfo) adapterView.getItemAtPosition(position);
			mListView.showContextMenu();
			return true;
		}
	};

	public boolean onContextItemSelected(MenuItem item) {
		cancelFavoriteRequest(item.getItemId());
		return true;
	}

	private void cancelFavoriteRequest(int i) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", LoginUtils.uid);
		map.put("id", hotelInfos.get(i).id);
		map.put("shopid", hotelInfos.get(i).shopid);
		Request request = new GuoliRequest("user_cancelfav", map);
		Manager.getInstance().executePoset(request, cancelListener);
		dialog = DialogUtils.showProgressDialog(FavoriteActivity.this, "请求中...");
	};

	IResponseListener cancelListener = new IResponseListener() {

		@Override
		public void onSuccess(Response response) {
			if (null != dialog && dialog.isShowing()) {
				dialog.cancel();
			}

			if (null == response) {
				return;
			}

			Log.d("MSG", "onSuccess:" + response.getData().toString());
			HashMap<String, Object> map = new Gson().fromJson(response.result.toString(),
					new TypeToken<HashMap<String, Object>>() {
					}.getType());

			if ("1".equalsIgnoreCase(map.get("success").toString())) {
				ToastUtil.show(map.get("message").toString());
				adapter.remove(hotelInfo);
				adapter.notifyDataSetChanged();
			} else {
				ToastUtil.show("失败");
			}

		}

		@Override
		public void onError(Response response) {
			if (dialog != null && dialog.isShowing())
				dialog.cancel();
			ToastUtil.show(ErrorCode.getErrorCodeString(response.errorCode));
		}
	};

	View.OnClickListener loadMoreListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			int pageNumber = DigitalUtils.convertToInt(favoriteInfo.getPageno());
			requetFavoriteData(setBean(pageNumber + 1));
		}
	};

}

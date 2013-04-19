/**
 * Project Name:Guoli
 * File Name:HotelsMapActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-11下午4:15:52
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
 */

package com.guoli.hotel.activity.hotel;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoli.hotel.R;
import com.guoli.hotel.bean.HotelInfo;
import com.guoli.hotel.bean.SearchInfo;
import com.guoli.hotel.net.request.bean.HotelRoom;

/**
 * ClassName:HotelsMapActivity <br/>
 * 
 * @Description: 酒店列表的地图模式 Date: 2013-1-11 下午4:15:52 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class HotelsMapActivity extends MapActivity {
	/** 城市 */
	private TextView mCityView;
	/** 入住日期 */
	private TextView mOccyView;
	/** 离店日期 */
	private TextView mLeaveView;
	/** 商家数 */
	private SearchInfo searchInfo;
	private TextView mCountView;
	private TextView titleView;
	private MapView mapView;
	// 先写一个坐标
	private GeoPoint centerPoint;
	private MapController controller;

	public static final String KEY_HOTEL_LIST = "holtelList";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hotels_map_mode);
		findViews();
		String json = getIntent().getStringExtra(KEY_HOTEL_LIST);
		ArrayList<HotelInfo> hotleList = new Gson().fromJson(json, new TypeToken<ArrayList<HotelInfo>>() {
		}.getType());
		searchInfo = getIntent().getParcelableExtra("SearchInfo");
		// TODO 酒店信息列表供地图显示酒店位置使用
		if (hotleList != null && hotleList.size() > 0 && searchInfo != null) {
			initViews(hotleList);
		}
	}

	private void findViews() {
		titleView = (TextView) findViewById(R.id.title_text);
		mCityView = (TextView) findViewById(R.id.city_text);
		mOccyView = (TextView) findViewById(R.id.occupancy_date);
		mLeaveView = (TextView) findViewById(R.id.leave_date);
		mCountView = (TextView) findViewById(R.id.count_view);

		BMapManager mapManager = new BMapManager(getApplication());
		mapManager.init(HotelLocationActivity.MAP_KEY, null);
		super.initMapActivity(mapManager);
		mapManager.start();
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		controller = mapView.getController();
		controller.setZoom(HotelLocationActivity.ZOOM_LEVEL);
	}

	private void initViews(ArrayList<HotelInfo> hotleList) {
		parseData(hotleList);
		titleView.setText(R.string.hotels_map_title);
		mCityView.setText(searchInfo.getCityName());
		mOccyView.setText(searchInfo.getStartDate());
		mLeaveView.setText(searchInfo.getEndDate());
		mCountView.setText(initSpecialView(hotleList.size(), R.string.result_count_desc));
	}

	private void parseData(ArrayList<HotelInfo> hotleList) {
		for (int i = 0; i < hotleList.size(); i++) {
			int price = hotleList.get(i).getPrice();
			double lng = hotleList.get(i).getMapx();
			double lat = hotleList.get(i).getMapy();
			String name = hotleList.get(i).getName();
			String hotelId = hotleList.get(i).getId();

			GeoPoint point = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
			markerVisible(point, name, price, hotelId);
			centerPoint = new GeoPoint((int) ((hotleList.get(0).getMapy()) * 1E6),
					(int) ((hotleList.get(0).getMapx()) * 1E6));
		}
		controller.setCenter(centerPoint);
	}

	private void markerVisible(GeoPoint point, String name, int price, final String hotelId) {
		LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.map_marker, null);
		TextView nameView = (TextView) layout.findViewById(R.id.hotelName);
		TextView priceView = (TextView) layout.findViewById(R.id.hotelPrice);
		nameView.setText(name);
		priceView.setText(initSpecialView(price, R.string.hotel_map_price));
		mapView.addView(layout, new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT,
				MapView.LayoutParams.WRAP_CONTENT, null, MapView.LayoutParams.BOTTOM_CENTER));
		MapView.LayoutParams mapviewParams = (MapView.LayoutParams) layout.getLayoutParams();
		mapviewParams.point = point;
		mapView.updateViewLayout(layout, mapviewParams);

		layout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				HotelRoom hotelRoom = setHotelRoom(hotelId);
				Intent intent = new Intent();
				intent.putExtra(HotelDetailActivity.KEY_REQUEST, hotelRoom);
				intent.setClass(HotelsMapActivity.this, HotelDetailActivity.class);
				startActivity(intent);
			}
		});
	}

	private HotelRoom setHotelRoom(String hotelId) {
		HotelRoom hotelRoom = new HotelRoom();
		hotelRoom.setId(hotelId);
		hotelRoom.setEndDate(searchInfo.getEndDate());
		hotelRoom.setStartDate(searchInfo.getStartDate());
		return hotelRoom;
	}

	private String initSpecialView(int count, int id) {
		String desc = getResources().getString(id);
		desc = String.format(desc, count);
		return desc;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}

/**
 * Project Name:SplashActivity
 * File Name:HotelLocationActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-24下午6:12:28
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
 */

package com.guoli.hotel.activity.hotel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.guoli.hotel.R;

/**
 * ClassName:HotelLocationActivity <br/>
 * 
 * @Description: Date: 2013-1-24 下午6:12:28 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class HotelLocationActivity extends MapActivity {

	public static final String MAP_KEY = "367475E851403E3CC39168413BB81978F3353E49";
	public static final String KEY_LATITUDE = "latitude";
	public static final String KEY_LONGITUDE = "longitude";
	public static final String KEY_HOTLE_ADR = "hotelAdr";
	public static final String KEY_HOTEL_NAME = "hotelName";
	public static final int ZOOM_LEVEL = 14;

	private MapController controller;
	private GeoPoint geoPoint;
	private MapView mapView;
	private double lat;
	private double lng;
	private String hotelAdr;
	private String hotelName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hotel_location_map);
		initView();
		mapOVerlay();
	}

	private void initView() {
		TextView title = (TextView) findViewById(R.id.title_text);
		title.setText(R.string.hotel_location_map);
		TextView backBtn = (TextView) findViewById(R.id.left_btn);
		backBtn.setVisibility(View.VISIBLE);
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		BMapManager mapManager = new BMapManager(getApplication());
		mapManager.init(MAP_KEY, null);
		super.initMapActivity(mapManager);
		mapManager.start();
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		controller = mapView.getController();
		controller.setZoom(ZOOM_LEVEL);

		lat = getIntent().getDoubleExtra(KEY_LATITUDE, 0);
		lng = getIntent().getDoubleExtra(KEY_LONGITUDE, 0);
		hotelAdr = getIntent().getStringExtra(KEY_HOTLE_ADR);
		hotelName = getIntent().getStringExtra(KEY_HOTEL_NAME);

	}

	private void mapOVerlay() {

		LinearLayout overlay = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.loca_map_dialog, null);
		TextView hotelAdrView = (TextView) overlay.findViewById(R.id.hotel_adr);
		TextView hotelNameView = (TextView) overlay.findViewById(R.id.hotel_name);

		if (!"".equalsIgnoreCase(hotelAdr)) {
			hotelAdrView.setText(hotelAdr);
		}

		if (!"".equalsIgnoreCase(hotelName)) {
			hotelNameView.setText(hotelName);
		}

		if (lat != 0 && lng != 0) {
			geoPoint = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
			mapView.addView(overlay, new MapView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
					null, MapView.LayoutParams.BOTTOM_CENTER));
			MapView.LayoutParams mapviewParams = (MapView.LayoutParams) overlay.getLayoutParams();
			mapviewParams.point = geoPoint;
			mapView.updateViewLayout(overlay, mapviewParams);
			controller.animateTo(geoPoint);
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}

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

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;
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

	protected static final String MAP_KEY = "367475E851403E3CC39168413BB81978F3353E49";
	public static final String KEY_LONGITUDE = "longitude";
	public static final String KEY_LATITUDE = "latitude";
	private static final int ZOOM_LEVEL = 15;

	private MapController controller;
	private GeoPoint geoPoint;
	private MapView mapView;
	private double lng;
	private double lat;

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

		BMapManager mapManager = new BMapManager(getApplication());
		mapManager.init(MAP_KEY, null);
		super.initMapActivity(mapManager);
		mapManager.start();
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(false);
		controller = mapView.getController();
		controller.setZoom(ZOOM_LEVEL);

		lng = getIntent().getDoubleExtra(KEY_LONGITUDE, 0);
		lat = getIntent().getDoubleExtra(KEY_LATITUDE, 0);
	}

	private void mapOVerlay() {
		if (lat != 0 && lng != 0) {
			geoPoint = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
			controller.animateTo(geoPoint);
			Drawable drawable = getResources().getDrawable(R.drawable.iconmarker);
			MarkerOverlay markerOverlay = new MarkerOverlay(drawable, geoPoint);
			mapView.getOverlays().add(markerOverlay);
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public class MarkerOverlay extends ItemizedOverlay<OverlayItem> {

		@SuppressWarnings("unused")
		private GeoPoint geoPoint;
		private OverlayItem item;

		public MarkerOverlay(Drawable drawable, GeoPoint geoPoint) {
			super(boundCenterBottom(drawable));
			this.geoPoint = geoPoint;
			item = new OverlayItem(geoPoint, "", "");
			updateOverlay();
		}

		public void updateOverlay() {
			populate();
		}

		@Override
		public void draw(Canvas canvas, MapView mapView, boolean b) {
			super.draw(canvas, mapView, b);
		}

		@Override
		protected OverlayItem createItem(int i) {
			return item;
		}

		@Override
		public int size() {
			return 1;
		}

		@Override
		protected boolean onTap(int i) {
			return super.onTap(i);
		}

		@Override
		public boolean onTap(GeoPoint geoPoint, MapView mapView) {
			return super.onTap(geoPoint, mapView);
		}

	}

}

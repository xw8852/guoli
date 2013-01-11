package com.guoli.hotel.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.widget.BottomTabbar;

public class SearchHotelActivity extends TitleBarActivity {

	public SearchHotelActivity() {
		mLayoutId = R.layout.search_hotel;
		mRightDrawableId = R.drawable.btn_top_phone;
		mTitleTextId = R.string.search_hotel_title;
	}
	
	public SearchHotelActivity(int r1,int r2,int r3){
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showRightBtn();
		new BottomTabbar(this, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_hotel, menu);
		return true;
	}

	@Override
	protected void findViews() {
		Button leaveBtn = (Button) findViewById(R.id.leave_date_btn);
		TextView searchBtn = (TextView) findViewById(R.id.search_btn);
		Button occupancyBtn = (Button) findViewById(R.id.occupancy_date_btn);
		Spinner citySpinner = (Spinner) findViewById(R.id.city_list);
		Spinner priceSpinner = (Spinner) findViewById(R.id.price_list);
		Spinner starSpinner = (Spinner) findViewById(R.id.star_list);
		Spinner areaSpinner = (Spinner) findViewById(R.id.area_list);

		occupancyBtn.setOnClickListener(this);
		leaveBtn.setOnClickListener(this);
		searchBtn.setOnClickListener(this);
	}

	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.city_list:
			// 入住城市
			break;
		case R.id.occupancy_date_btn:
			// 入住日期
			break;
		case R.id.leave_date_btn:
			// 离店时间
			break;
		case R.id.price_list:
			// 价格
			break;
		case R.id.star_list:
			// 星级
			break;
		case R.id.area_list:
			// 区域
			break;
		default:
			break;
		}

	}

}

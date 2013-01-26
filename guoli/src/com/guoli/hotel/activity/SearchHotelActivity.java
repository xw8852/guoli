package com.guoli.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.widget.BottomTabbar;

public class SearchHotelActivity extends BaseActivity implements OnItemSelectedListener {
    
    /**城市标识*/
    public static final int PAGE_CITY = 0;
    /**入住时间标识*/
    public static final int PAGE_OCCUPANCY_DATE = 1;
    /**离店时间标识*/
    public static final int PAGE_LEAVE_DATE = 2;
    
    /**入住城市*/
    private TextView mCityView;
    /**入住日期*/
    private TextView mOccupancyView;
    /**离店日期*/
    private TextView mLeaveyView;
    
    public SearchHotelActivity() {
        mLayoutId = R.layout.search_hotel;
        mRightDrawableId = R.drawable.btn_top_phone;
        mTitleTextId = R.string.search_hotel_title;
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
        mLeaveyView = (TextView) findViewById(R.id.leave_date_btn);
        TextView searchBtn = (TextView) findViewById(R.id.search_btn);
        mOccupancyView = (TextView) findViewById(R.id.occupancy_date_btn);
        mCityView = (TextView) findViewById(R.id.cityName);
        Spinner priceSpinner = (Spinner) findViewById(R.id.price_list);
        Spinner starSpinner = (Spinner) findViewById(R.id.star_list);
        Spinner areaSpinner = (Spinner) findViewById(R.id.area_list);
        
        mOccupancyView.setOnClickListener(this);
        mLeaveyView.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        mCityView.setOnClickListener(this);
        starSpinner.setOnItemSelectedListener(this);
        areaSpinner.setOnItemSelectedListener(this);
        priceSpinner.setOnItemSelectedListener(this);
    }

    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        switch (v.getId()) {
        case R.id.cityName:
            //入住城市
            intent = new Intent();
            intent.setClass(this, CitySelectActivity.class);
            startActivityForResult(intent, PAGE_CITY);
            break;
        case R.id.occupancy_date_btn:
            //入住日期
            intent = new Intent();
            intent.setClass(this, OccupancyDateSelectActivity.class);
            startActivityForResult(intent, PAGE_OCCUPANCY_DATE);
            break;
        case R.id.leave_date_btn:
            //离店时间
            intent = new Intent();
            intent.setClass(this, LeaveDateSelectActivity.class);
            startActivityForResult(intent, PAGE_LEAVE_DATE);
            break;
        case R.id.price_list:
            //价格
            break;
        case R.id.star_list:
            //星级
            break;
        case R.id.area_list:
            //区域
            break;
        case R.id.search_btn:
            //搜索
            enterHotelSearchResultActivity();
            break;
        default:
            break;
        }
    }
    
    /**
     * 
     * enterHotelSearchResultActivity:跳转到酒店查询结果页�? <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void enterHotelSearchResultActivity() {
        //跳转到酒店搜索结果页�?        
        Intent intent = new Intent();
        intent.setClass(this, HotelSearchResultActivity.class);
        startActivity(intent);
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        
        
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        
    }

    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
        case PAGE_CITY:
            String cityName = data == null ? "" : data.getStringExtra(CitySelectActivity.KEY_CITY_NAME);
            setViewText(mCityView, cityName);
            break;
        case PAGE_OCCUPANCY_DATE:
            String occupancyDate = data == null ? "" : data.getStringExtra(DateSelectActivity.KEY_OCCUPANCY_DATE);
            setViewText(mOccupancyView, occupancyDate);
            break;
        default:
            break;
        }
    }
    
    /**
     * 
     * setViewText:设置textView的值. <br/>
     * @author maple
     * @param view
     * @param text
     * @since JDK 1.6
     */
    private void setViewText(TextView view, String text){
        if (view == null) {
            return;
        }
        view.setText(text == null ? "" : text);
    }
}

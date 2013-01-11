package com.guoli.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.guoli.hotel.R;

public class SearchHotelActivity extends BaseActivity implements OnItemSelectedListener {
    
    public SearchHotelActivity() {
        mLayoutId = R.layout.search_hotel;
        mRightDrawableId = R.drawable.btn_top_phone;
        mTitleTextId = R.string.search_hotel_title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showRightBtn();
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
        citySpinner.setOnItemSelectedListener(this);
        starSpinner.setOnItemSelectedListener(this);
        areaSpinner.setOnItemSelectedListener(this);
        priceSpinner.setOnItemSelectedListener(this);
    }

    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
        case R.id.city_list:
            //入住城市
            break;
        case R.id.occupancy_date_btn:
            //入住日期
            break;
        case R.id.leave_date_btn:
            //离店时间
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
        //跳转到酒店搜索结果页�?        Intent intent = new Intent();
        intent.setClass(this, HotelSearchResultActivity.class);
        startActivity(intent);
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        
        
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        
    }

}
>>>>>>> maple

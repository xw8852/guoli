package com.guoli.hotel.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.guoli.hotel.R;

public class SearchHotelActivity extends BaseActivity implements OnClickListener {
    
    public SearchHotelActivity() {
        mLayoutId = R.layout.search_hotel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_hotel, menu);
        return true;
    }

    @Override
    protected void findViews() {
        Spinner citySpinner = (Spinner) findViewById(R.id.city_list);
        Button occupancyBtn = (Button) findViewById(R.id.occupancy_date_btn);
        Button leaveBtn = (Button) findViewById(R.id.leave_date_btn);
        Spinner priceSpinner = (Spinner) findViewById(R.id.price_list);
        Spinner starSpinner = (Spinner) findViewById(R.id.star_list);
        Spinner areaSpinner = (Spinner) findViewById(R.id.area_list);
        TextView searchBtn = (TextView) findViewById(R.id.search_btn);
        
        citySpinner.setOnClickListener(this);
        occupancyBtn.setOnClickListener(this);
        leaveBtn.setOnClickListener(this);
        priceSpinner.setOnClickListener(this);
        starSpinner.setOnClickListener(this);
        areaSpinner.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
    }
    
    /**
     * 
     * initTitleBar:初始化标题栏. <br/>
     * @author maple
     * @since JDK 1.6
     */
    private void initTitleBar(){
        TextView rightBtn = getRightButton();
        if (rightBtn != null) {
            rightBtn.setVisibility(View.VISIBLE);
            rightBtn.setOnClickListener(this);
            rightBtn.setBackgroundResource(R.drawable.btn_top_phone);
        }
        TextView titleView = getTitleView();
        if (titleView != null) {
            titleView.setText(R.string.search_hotel_title);
        }
    }

    public void onClick(View v) {
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
        case R.id.right_btn:
            //拨号
            break;

        default:
            break;
        }
        
    }

}

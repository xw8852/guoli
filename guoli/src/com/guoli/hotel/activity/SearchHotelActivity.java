package com.guoli.hotel.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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
            rightBtn.setBackgroundResource(R.drawable.btn_top_phone);
        }
        TextView titleView = getTitleView();
        if (titleView != null) {
            titleView.setText(R.string.search_hotel_title);
        }
    }

    public void onClick(View v) {
        
        // TODO Auto-generated method stub
        
    }

}

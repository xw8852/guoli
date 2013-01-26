package com.guoli.hotel.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.AccountActivity;
import com.guoli.hotel.activity.HotelListActivity;
import com.guoli.hotel.activity.MoreActivity;
import com.guoli.hotel.activity.OrderAuthenticActivity;
import com.guoli.hotel.activity.SearchHotelActivity;

/**
 * 
 * @author Josn
 * 
 */
public class BottomTabbar {
	LinearLayout root;

	/**
	 * 
	 */
	public BottomTabbar(Activity activity, int activeIndex) {
		this(activity.findViewById(R.id.bottomTabBar), activeIndex);
	}

	public BottomTabbar(Fragment fragment, int activeIndex) {
		this(fragment.getView().findViewById(R.id.bottomTabBar), activeIndex);
	}

	public BottomTabbar(View view, int activeIndex) {
		if (view == null)
			throw new IllegalArgumentException(
					"you's activity (or fragment) must be contain the layout file of bottom_tabbar");
		if (view.getId() == R.id.bottomTabBar)
			root = (LinearLayout) view;
		else
			root = (LinearLayout) view.findViewById(R.id.bottomTabBar);
		for (int i = 0; i < root.getChildCount(); i++) {
			if (i == activeIndex) {
				root.getChildAt(i).setSelected(true);
			}
			root.getChildAt(i).setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
				    if(v.isSelected())return;
					for (int i = 0; i < root.getChildCount(); i++) {
						root.getChildAt(i).setSelected(false);						
					}
					switch (v.getId()) {
                    case R.id.layout1:onClickListener_1.onClick(v); break;
                    case R.id.layout2:onClickListener_2.onClick(v); break;
                    case R.id.layout3:onClickListener_3.onClick(v); break;
                    case R.id.layout4:onClickListener_4.onClick(v); break;
                    case R.id.layout5:onClickListener_5.onClick(v); break;
                    }
					v.setSelected(true);
					
				}
			});
		}
	}
	/**
	 * “查找”项的点击
	 */
	View.OnClickListener onClickListener_1=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            startActivity(v.getContext(), SearchHotelActivity.class);
        }
    };
    /**
     * “推荐”项的点击
     */
    View.OnClickListener onClickListener_2=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            startActivity(v.getContext(), HotelListActivity.class);
        }
    };
    /**
     * “订单”项的点击
     */
    View.OnClickListener onClickListener_3=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            startActivity(v.getContext(), OrderAuthenticActivity.class);
        }
    };
    /**
     * “用户”项的点击
     */
    View.OnClickListener onClickListener_4=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            startActivity(v.getContext(), AccountActivity.class);
        }
    };
    /**
     * “更多”项的点击
     */
    View.OnClickListener onClickListener_5=new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            startActivity(v.getContext(), MoreActivity.class);
        }
    };
    
    private void startActivity(Context c,Class<? extends Activity> cls){
        c.startActivity(new Intent(c, cls));
        if(c instanceof Activity){
            ((Activity)c).finish();
        }
    }
    
    public void activeIndex(int index){
        View v=root.getChildAt(index);
        switch (index) {
        case 0:onClickListener_1.onClick(v); break;
        case 1:onClickListener_2.onClick(v); break;
        case 2:onClickListener_3.onClick(v); break;
        case 3:onClickListener_4.onClick(v); break;
        case 4:onClickListener_5.onClick(v); break;
        }
    }
}

package com.guoli.hotel.widget;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.guoli.hotel.R;

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
			root = (LinearLayout)view;
		else
			root = (LinearLayout)view.findViewById(R.id.bottomTabBar);
		for (int i = 0; i < root.getChildCount(); i++) {
			root.getChildAt(i).setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					for (int i = 0; i < root.getChildCount(); i++) {
						root.getChildAt(i).setSelected(false);
					}
					v.setSelected(true);
				}
			});
		}
	}
}

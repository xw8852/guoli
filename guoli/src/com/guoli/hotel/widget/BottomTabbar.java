package com.guoli.hotel.widget;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import com.guoli.hotel.R;

/**
 * 
 * @author Josn
 * 
 */
public class BottomTabbar {
	View root;

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
			root = view;
		else
			root = view.findViewById(R.id.bottomTabBar);

	}
}

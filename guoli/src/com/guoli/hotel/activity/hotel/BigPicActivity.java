package com.guoli.hotel.activity.hotel;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.activity.hotel.PicGridActivity.PicModel;

public class BigPicActivity extends BaseActivity2 {
    ViewPager mPager;
    ArrayList<PicModel> models;
    ArrayList<View> views = new ArrayList<View>();

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        mPager = (ViewPager) findViewById(R.id.ViewPager1);
        ArrayList<Parcelable> data = getIntent().getParcelableArrayListExtra("data");
        int index = getIntent().getIntExtra("index", 0);
        for (Parcelable parcelable : data) {
            PicModel model=(PicModel)parcelable;
            View view = getLayoutInflater().inflate(R.layout.pic_big_cell, null);
            TextView name = (TextView)view.findViewById(R.id.name);
            name.setText(model.name);
            views.add(view);
        }
        mPager.setAdapter(new PicAdapter());
        mPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                setTitle("第" + (arg0+1) + "张" + "共" + views.size() + "张");
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        mPager.setCurrentItem(index);
    }

    @Override
    public int getContentId() {
        return R.layout.pic_big_activity;
    }

    class PicAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(views.get(position));
            return views.get(position);

        }

    }
}

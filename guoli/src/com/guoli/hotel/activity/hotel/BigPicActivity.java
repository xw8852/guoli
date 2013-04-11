package com.guoli.hotel.activity.hotel;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guoli.hotel.R;
import com.guoli.hotel.activity.BaseActivity2;
import com.guoli.hotel.net.response.bean.PicInfo;
import com.guoli.hotel.utils.ImageUtil;
import com.msx7.core.Controller;

public class BigPicActivity extends BaseActivity2 {
    ViewPager mPager;
    ArrayList<PicInfo> models;
    ArrayList<View> views = new ArrayList<View>();

    @Override
    public void onAfterCreate(Bundle savedInstanceState) {
        showLeftReturnBtn(false, -1);
        mPager = (ViewPager) findViewById(R.id.ViewPager1);
        ArrayList<Parcelable> list = getIntent().getParcelableArrayListExtra("data");
        int size = list.size();
        int index = getIntent().getIntExtra("index", 0);
        String titleRes = getString(R.string.big_pic_title);
        setTitle(String.format(titleRes, index+1, size));
        for (Parcelable parcelable : list) {
            PicInfo info=(PicInfo)parcelable;
            View view = getLayoutInflater().inflate(R.layout.pic_big_cell, null);
            TextView name = (TextView)view.findViewById(R.id.name);
            ImageView imgView = (ImageView) view.findViewById(R.id.icon);
            initImageView(imgView, info);
            name.setText(info.getName());
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
    
    private void initImageView(ImageView imgView, PicInfo info){
        String picPath = getIntent().getStringExtra(PicGridActivity.KEY_PIC_PATH);
        Controller.getApplication().loadImage(ImageUtil.getImageUrl(picPath, info.getPicName()), imgView, R.drawable.default_big_pic);
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

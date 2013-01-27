/**
 * Project Name:SplashActivity
 * File Name:FavoriteActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-26下午4:40:45
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.guoli.hotel.R;
import com.guoli.hotel.adapter.FavoriteAdapter;
import com.guoli.hotel.bean.HotelInfo;

/**
 * ClassName:FavoriteActivity <br/>
 * 
 * @Description: 我的收藏页面 Date: 2013-1-26 下午4:40:45 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class FavoriteActivity extends BaseActivity implements OnItemClickListener {

    private ListView mListView;

    public FavoriteActivity() {
        mLayoutId = R.layout.favorite_layout;
        mTitleTextId = R.string.my_favorite;
        mRightTextId = R.string.exit;
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        showLeftBtn();
        showRightBtn();
        updateListView(getList());
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.favoriteListView);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListView.getAdapter() == null || !(mListView.getAdapter().getCount() > position)) { return; }
    }

    /**
     * 
     * updateListView:更新列表数据. <br/>
     * 
     * @author maple
     * @param list
     * @since JDK 1.6
     */
    private void updateListView(List<HotelInfo> list) {
        if (list == null || list.size() < 1) { return; }
        FavoriteAdapter adapter = (FavoriteAdapter) mListView.getAdapter();
        if (adapter == null) {
            adapter = new FavoriteAdapter(list, this);
            mListView.setAdapter(adapter);
            return;
        }
        adapter.clear();
        adapter.addMore(list);
    }

    // 模拟的数据
    private List<HotelInfo> getList() {
        List<HotelInfo> infoList = new ArrayList<HotelInfo>();
        for (int index = 0; index < 20; index++) {
            HotelInfo info = new HotelInfo();
            info.setName("酒店" + index);
            info.setAddress("地址" + index);
            info.setArea("区域" + index);
            info.setLevel((3 + index) % 5);
            info.setPrice(0 + index);
            info.setDiscount((float) (1.2 + index * 0.1));
            info.setPhoneNumber("10201110" + index);
            infoList.add(info);
        }
        return infoList;
    }
}

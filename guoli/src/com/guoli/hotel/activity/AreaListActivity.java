/**
 * Project Name:SplashActivity
 * File Name:AreaListActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-26下午12:28:56
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.guoli.hotel.R;

/**
 * ClassName:AreaListActivity <br/>
 * @Description:    行政区域选择页面
 * Date:     2013-1-26 下午12:28:56 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AreaListActivity extends BaseActivity implements OnItemClickListener, OnCheckedChangeListener {
    
    private ListView mListView;
    private RadioGroup mTabBar;
    /**行政区数据*/
    private String[] mAreaAdmins = new String[]{"不限","黄浦区","浦东新区","徐汇区","杨浦区","长宁区","虹口区"};
    /**商圈数据*/
    private String[] mAreaShoppings = new String[]{"不限","七浦路商圈","彭浦新村商圈","浦东八佰伴商圈","徐家汇商圈","人民广场"};
    
    private ArrayAdapter<String> mAdapter;
    
    public static final String KEY_AREA = "area";
    
    public AreaListActivity(){
        mTitleTextId = R.string.hotel_area;
        mLayoutId = R.layout.area_list_layout;
    }
    
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showLeftBtn();
        updateListView(mAreaAdmins);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int checkedId = mTabBar.getCheckedRadioButtonId();
        String area = "";
        switch (checkedId) {
        case R.id.areaAdminRadioBtn:
            if (mAreaAdmins == null || !(mAreaAdmins.length > position)) {
                break;
            }
            area = mAreaAdmins[position];
            break;
        case R.id.areaShoppingRadioBtn:
            if (mAreaShoppings == null || !(mAreaShoppings.length > position)) {
                break;
            }
            area = mAreaShoppings[position];
            break;
        default:
            break;
        }
        Intent intent = new Intent();
        intent.putExtra(KEY_AREA, area);
        setResult(SearchHotelActivity.PAGE_AREA, intent);
        finish();
    }

    @Override
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.areaListView);
        mListView.setOnItemClickListener(this);
        mTabBar = (RadioGroup) findViewById(R.id.radioGroup);
        mTabBar.setOnCheckedChangeListener(this);
        mTabBar.check(R.id.areaAdminRadioBtn);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
        case R.id.areaAdminRadioBtn:
            updateListView(mAreaAdmins);
            break;
        case R.id.areaShoppingRadioBtn:
            updateListView(mAreaShoppings);
            break;
        default:
            break;
        }
    }

    /**
     * 
     * updateListView:更新当前页面listView中的数据. <br/>
     * @author maple
     * @param array
     * @since JDK 1.6
     */
    private void updateListView(String[] array){
        if (array == null || array.length < 1) {
            return;
        }
        if (mAdapter == null) {
            List<String> list = new ArrayList<String>();
            list.addAll(Arrays.asList(array));
            mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            mListView.setAdapter(mAdapter);
            return;
        }
        mAdapter.clear();
        for (String str : array) {
            if (TextUtils.isEmpty(str)) {
                continue;
            }
            mAdapter.add(str);
        }
        mAdapter.notifyDataSetChanged();
    }
}


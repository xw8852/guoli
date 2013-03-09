/**
 * Project Name:Guoli
 * File Name:UpdateActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-10下午3:30:27
 * Copyright (c) 2013
 * Company:maple&&json&&abel
 *
*/

package com.guoli.hotel.activity;

import android.os.Bundle;

import com.guoli.hotel.utils.NetUtils;

/**
 * ClassName:UpdateActivity <br/>
 * @Description:    加载数据并显示的视图基类
 * Date:     2013-1-10 下午3:30:27 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class UpdateActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        initPassParams();
        loadData();
    }

    /**
     * 
     * loadData:加载数据. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void loadData(){
        //TODO 有本地缓存的情况下,合理的逻辑应该是先查询本地数据,如果本地数据没有查询到再判断网络是否可用
        //网络如果可以用则查询网络数据
        if (!NetUtils.isNetworkWell(this)) {
            loadLocalData();
            initViews();
            return;
        }
        showLoadingProgressBar();
        loadNetworkData();
    }
    
    /**
     * 
     * showLoadingProgressBar:显示数据加载loading框. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void showLoadingProgressBar(){
        
    }
    
    /**
     * 
     * closeLoadingProgressBar:隐藏数据加载loading框. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void closeLoadingProgressBar(){
        
    }
    
    /***
     * 
     * initPassParams:初始化从其他页面传递过来的参数信息. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void initPassParams();

    /**
     * 
     * initViews:初始化视图信息. <br/>
     * 在数据加载完毕后调用该方法.<br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void initViews();
    /***
     * 
     * loadNetworkData:加载网络数据. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void loadNetworkData();
    /**
     * 
     * loadLocalData:加载本地数据. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected abstract void loadLocalData();
}


/**
 * Project Name:Guoli
 * File Name:UpdateActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-1-10下午3:30:27
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.activity;

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

    /**
     * 
     * loadData:加载数据. <br/>
     * @author maple
     * @since JDK 1.6
     */
    protected void loadData(){
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


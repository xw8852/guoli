/**
 * Project Name:Guoli
 * File Name:CallActivity.java
 * Package Name:com.guoli.hotel.activity
 * Date:2013-2-21下午3:37:10
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.activity;

import com.guoli.hotel.utils.CallUtils;


/**
 * ClassName:CallActivity <br/>
 * @Description: 拨号基类
 * Date:     2013-2-21 下午3:37:10 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class CallActivity extends BaseActivity {

    @Override
    protected void rightBtnClickEvent() {
        super.rightBtnClickEvent();
        CallUtils utils = new CallUtils(this);
        utils.callServer();
    }
}


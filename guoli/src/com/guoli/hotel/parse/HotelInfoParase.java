/**
 * Project Name:Guoli
 * File Name:HotelInfoParase.java
 * Package Name:com.guoli.hotel.parse
 * Date:2013-3-18下午6:21:15
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.parse;

import com.guoli.hotel.net.response.bean.HotelRespInfo;
import com.msx7.core.command.model.Response;

/**
 * ClassName:HotelInfoParase <br/>
 * @Description:
 * Date:     2013-3-18 下午6:21:15 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelInfoParase extends BaseParse<HotelRespInfo> {

    /**
     * 
     * parseResponse:把json response 转换为一个HotelRespInfo对象. <br/>
     * @author maple
     * @param resp
     * @return
     * @since JDK 1.6
     */
    public HotelRespInfo parseResponse(Response resp){
        return parse(resp, HotelRespInfo.class);
    }
}


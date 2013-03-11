/**
 * Project Name:Guoli
 * File Name:HotelListInfoParse.java
 * Package Name:com.guoli.hotel.parse
 * Date:2013-3-11下午10:08:08
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.parse;

import com.guoli.hotel.bean.HotelListInfo;
import com.msx7.core.command.model.Response;

/**
 * ClassName:HotelListInfoParse <br/>
 * @Description:
 * Date:     2013-3-11 下午10:08:08 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelListInfoParse extends BaseParse<HotelListInfo> {

    /**
     * 
     * parseResponse:把json response 转换为一个HotelListInfo对象. <br/>
     * @author maple
     * @param resp
     * @return
     * @since JDK 1.6
     */
    public HotelListInfo parseResponse(Response resp){
        return parse(resp, HotelListInfo.class);
    }
}


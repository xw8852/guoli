/**
 * Project Name:Guoli
 * File Name:HotelPicInfoParse.java
 * Package Name:com.guoli.hotel.parse
 * Date:2013-4-2下午5:14:06
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.parse;

import com.guoli.hotel.bean.HotelPicInfo;
import com.msx7.core.command.model.Response;

/**
 * ClassName:HotelPicInfoParse <br/>
 * @Description:
 * Date:     2013-4-2 下午5:14:06 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelPicInfoParse extends BaseParse<HotelPicInfo> {

    /**
     * 
     * parseResponse:把json response 转换为一个HotelPicInfo对象. <br/>
     * @author maple
     * @param resp
     * @return
     * @since JDK 1.6
     */
    public HotelPicInfo parseResponse(Response resp){
        return parse(resp, HotelPicInfo.class);
    }
}


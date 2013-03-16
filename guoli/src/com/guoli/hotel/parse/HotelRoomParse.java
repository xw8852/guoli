/**
 * Project Name:Guoli
 * File Name:HotelDetailParse.java
 * Package Name:com.guoli.hotel.parse
 * Date:2013-3-14下午8:48:40
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.parse;

import com.guoli.hotel.net.response.bean.RoomRespInfo;
import com.msx7.core.command.model.Response;

/**
 * ClassName:HotelDetailParse <br/>
 * @Description:
 * Date:     2013-3-14 下午8:48:40 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HotelRoomParse extends BaseParse<RoomRespInfo> {

    /**
     * 
     * parseResponse:解析hotel_room接口的返回对象. <br/>
     * @author maple
     * @param resp
     * @return
     * @since JDK 1.6
     */
    public RoomRespInfo parseResponse(Response resp){
        return parse(resp, RoomRespInfo.class);
    }
}


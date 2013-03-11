/**
 * Project Name:Guoli
 * File Name:RecommandHotelParse.java
 * Package Name:com.guoli.hotel.parse
 * Date:2013-3-9下午3:49:27
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.parse;

import com.guoli.hotel.bean.RecommendsInfo;
import com.msx7.core.command.model.Response;

/**
 * ClassName:RecommandHotelParse <br/>
 * @Description:    酒店推荐接口解析器
 * Date:     2013-3-9 下午3:49:27 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RecommandHotelParse extends BaseParse<RecommendsInfo> {

    /**
     * 
     * parseResponse:把json response 转换为一个RecommendHotelInfo对象. <br/>
     * @author maple
     * @param resp
     * @return
     * @since JDK 1.6
     */
    public RecommendsInfo parseResponse(Response resp){
        return parse(resp, RecommendsInfo.class);
    }
}


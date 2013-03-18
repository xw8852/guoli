/**
 * Project Name:Guoli
 * File Name:RecommendInfoParase.java
 * Package Name:com.guoli.hotel.parse
 * Date:2013-3-18上午9:37:56
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.parse;

import com.guoli.hotel.net.response.bean.RecommendRespInfo;
import com.msx7.core.command.model.Response;

/**
 * ClassName:RecommendInfoParase <br/>
 * @Description:解析hotel_rechotel接口返回的对象
 * Date:     2013-3-18 上午9:37:56 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RecommendRespParase extends BaseParse<RecommendRespInfo>{

    /**
     * 
     * parseResponse:把json response 转换为一个RecommendRespInfo对象. <br/>
     * @author maple
     * @param resp
     * @return
     * @since JDK 1.6
     */
    public RecommendRespInfo parseResponse(Response resp){
        return parse(resp, RecommendRespInfo.class);
    }
}


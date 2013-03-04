/**
 * Project Name:Guoli
 * File Name:CitysParse.java
 * Package Name:com.guoli.hotel.parse
 * Date:2013-3-4下午7:52:30
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.parse;

import com.guoli.hotel.net.response.bean.CityResponseParams;
import com.msx7.core.command.model.Response;

/**
 * ClassName:CitysParse <br/>
 * @Description:    城市选择接口解析
 * Date:     2013-3-4 下午7:52:30 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CitysParse extends BaseParse<CityResponseParams> {

    /**
     * 
     * parseResponse:把json response 转换为一个CityResponseParams对象. <br/>
     * @author maple
     * @param resp
     * @return
     * @since JDK 1.6
     */
    public CityResponseParams parseResponse(Response resp){
        return parse(resp, CityResponseParams.class);
    }
}


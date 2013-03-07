/**
 * Project Name:Guoli
 * File Name:AreaParase.java
 * Package Name:com.guoli.hotel.parse
 * Date:2013-3-5下午9:35:10
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.parse;

import com.guoli.hotel.net.response.bean.LocationResponse;
import com.msx7.core.command.model.Response;

/**
 * ClassName:AreaParase <br/>
 * @Description:
 * Date:     2013-3-5 下午9:35:10 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AreaParase extends BaseParse<LocationResponse> {

    /**
     * 
     * parseResponse:把json response 转换为一个LocationResponse对象. <br/>
     * @author maple
     * @param resp
     * @return
     * @since JDK 1.6
     */
    public LocationResponse parseResponse(Response resp){
        return parse(resp, LocationResponse.class);
    }
}


/**
 * Project Name:Guoli
 * File Name:BaseParse.java
 * Package Name:com.guoli.hotel.parse
 * Date:2013-3-4下午8:49:20
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.parse;

import com.google.gson.Gson;
import com.msx7.core.command.model.Response;

/**
 * ClassName:BaseParse <br/>
 * @Description: Json response 解析基类
 * Date:     2013-3-4 下午8:49:20 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BaseParse<T> {

    /**
     * 
     * parse:把json Response转换为一个对应的类对象. <br/>
     * @author maple
     * @param resp
     * @param cls
     * @return
     * @since JDK 1.6
     */
    protected T parse(Response resp, Class<T> cls){
        if (resp == null || cls == null) {
            return null;
        }
        Gson gson = new Gson();
        Object obj = resp.result;
        if (!(obj instanceof String)) {
            return null;
        }
        String json = (String) obj;
        return gson.fromJson(json, cls);
    }
}


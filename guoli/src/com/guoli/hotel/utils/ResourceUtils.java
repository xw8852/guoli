/**
 * Project Name:Guoli
 * File Name:ResourceUtils.java
 * Package Name:com.guoli.hotel.utils
 * Date:2013-3-19下午8:16:03
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.guoli.hotel.bean.MapItemInfo;

/**
 * ClassName:ResourceUtils <br/>
 * @Description:
 * Date:     2013-3-19 下午8:16:03 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ResourceUtils {
    
    private Context mCtx;
    
    private static ResourceUtils mInstance = null;
    
    private ResourceUtils(Context ctx) {
        mCtx = ctx;
    }
    
    public synchronized static ResourceUtils getInstance(Context ctx){
        mInstance = mInstance == null ? new ResourceUtils(ctx) : mInstance;
        return mInstance;
    }

    /**
     * 
     * convertToList:把指定ID的key/value数组转换为MapItemInfo list. <br/>
     * @author maple
     * @param keyId
     * @param valueId
     * @return
     * @since JDK 1.6
     */
    public List<MapItemInfo> convertToList(int keyId, int valueId) {
        String[] keys = mCtx.getResources().getStringArray(keyId);
        String[] values = mCtx.getResources().getStringArray(valueId);
        int length = values.length;
        List<MapItemInfo> list = new ArrayList<MapItemInfo>();
        for (int index = 0; index < length; index++) {
            MapItemInfo info = new MapItemInfo();
            info.setKey(keys[index]);
            info.setValue(values[index]);
            list.add(info);
        }
        return list;
    }
    
    /***
     * 
     * getKey:获取指定value对应的key. <br/>
     * @author maple
     * @param keyArrayId
     * @param valueArrayId
     * @param value
     * @return
     * @since JDK 1.6
     */
    public String getKey(int keyArrayId, int valueArrayId, String value){
        String[] values = mCtx.getResources().getStringArray(valueArrayId);
        String[] keys = mCtx.getResources().getStringArray(keyArrayId);
        if (values == null || keys == null) {
            return "";
        }
        int index = searchAtIndex(values, value);
        if (index < 0) {
            return "";
        }
        if (index < keys.length) {
            return keys[index];
        }
        return "";
    }
    /**
     * 
     * getValue:获取指定key对应的value. <br/>
     * @author maple
     * @param keyArrayId
     * @param valueArrayId
     * @param key
     * @return
     * @since JDK 1.6
     */
    public String getValue(int keyArrayId, int valueArrayId, String key){
        String[] values = mCtx.getResources().getStringArray(valueArrayId);
        String[] keys = mCtx.getResources().getStringArray(keyArrayId);
        if (values == null || keys == null) {
            return "";
        }
        int index = searchAtIndex(keys, key);
        if (index < 0) {
            return "";
        }
        if  (index < values.length) {
            return values[index];
        }
        return "";
    }
    
    private int searchAtIndex(String[] values, String value){
        if (value == null || values == null) {
            return -1;
        }
        int length = values.length;
        for (int index = 0 ; index < length ; index++) {
            if (value.trim().equals(values[index].trim())) {
                return index;
            }
        }
        return -1;
    }
}


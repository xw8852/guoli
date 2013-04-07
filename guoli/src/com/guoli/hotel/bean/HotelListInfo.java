/**
 * Project Name:Guoli
 * File Name:HotelListInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-11下午10:05:13
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
 */

package com.guoli.hotel.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:HotelListInfo <br/>
 * 
 * @Description: 酒店列表对象 Date: 2013-3-11 下午10:05:13 <br/>
 * @author maple
 * @version
 * @since JDK 1.6
 * @see
 */
public class HotelListInfo {
    
    @SerializedName("resulttotal")
    private String total;
    
    @SerializedName("pageno")
    private String pageNum;
    
    @SerializedName("pagecount")
    private String pageCount;
    
    @SerializedName("pagesize")
    private String pageSize;

    @SerializedName("result")
    private List<HotelInfo> list;

    public List<HotelInfo> getList() {
        return list;
    }

    public void setList(List<HotelInfo> list) {
        this.list = list;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}

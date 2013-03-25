/**
 * Project Name:Guoli
 * File Name:OrderSubmitInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-25下午10:40:42
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:OrderSubmitInfo <br/>
 * @Description:    提交订单对象
 * Date:     2013-3-25 下午10:40:42 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class OrderSubmitInfo {

    /**酒店ID*/
    @SerializedName("shopid")
    private String hotelId;
    
    /**房型ID*/
    @SerializedName("pid")
    private String roomId;
    
    /**入住时间*/
    @SerializedName("indate")
    private String startDate;
    
    /**离店时间*/
    @SerializedName("outdate")
    private String endDate;
    
    /**房间数*/
    @SerializedName("num")
    private int count;
    
    /**其他要求*/
    @SerializedName("meno")
    private String moreRequire;
    
    /**入住人*/
    @SerializedName("inpeople")
    private String inPeople;
    
    /**联系人*/
    @SerializedName("linkman")
    private String contactName;
    
    /**联系电话*/
    @SerializedName("mobile")
    private String contactPhone;
    
    /***/
    @SerializedName("")
    private String uid;
    
    /**是否需要发票*/
    @SerializedName("isinvoice")
    private String isInvoice;
    
    /**发票抬头*/
    @SerializedName("invoicetitle")
    private String invoiceTitle;
    
    /**收件人*/
    @SerializedName("writename")
    private String recipientName;
    
    /**收件人电话*/
//    @SerializedName("")
//    private String recipientPhone;
    
    /**收件人地址*/
    @SerializedName("address")
    private String recipientAddress;
    
    /**收件人邮件*/
    @SerializedName("postcode")
    private String recipientPostCode;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMoreRequire() {
        return moreRequire;
    }

    public void setMoreRequire(String moreRequire) {
        this.moreRequire = moreRequire;
    }

    public String getInPeople() {
        return inPeople;
    }

    public void setInPeople(String inPeople) {
        this.inPeople = inPeople;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientPostCode() {
        return recipientPostCode;
    }

    public void setRecipientPostCode(String recipientPostCode) {
        this.recipientPostCode = recipientPostCode;
    }
}


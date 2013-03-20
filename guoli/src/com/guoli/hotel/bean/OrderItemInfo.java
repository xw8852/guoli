package com.guoli.hotel.bean;

/**
 * 订单列表的item
 * 
 * @author Josn
 * 
 */
public class OrderItemInfo {
	/**
	 * orderno": "13582316683972600",
            "shopid": "79247",
            "shopname": "上海万豪虹桥大酒店",
            "pid": "46056",
            "name": "豪华房",
            "paymentmoney": "605.00",
            "checkin": "2013-01-15",
            "checkout": "2013-01-16",
            "tradestatus": "8",
            "sucflag": "1",
            "ispay": "0",
            "creation": "2013-01-15"
	 */
	public String shopid;
	public String shopname;
	public String name;
	public String orderno;
	public String ispay;
	/**
	 * 0,处理中； 1，成功；2传真
	 */
	public String sucflag;
	public String checkin;
	public String creation;
	public String paymentmoney;
	public String pid;
	public String checkout;
	/**
	 * 0-未付款（未成交） 1-已付款（成交） 2-取消（放弃） 3-交易关闭4-已退款，6-退款中，8-交易成功)
	 */
	public String tradestatus;

	
}

package com.guoli.hotel.bean;

/**
 * 订单列表的item
 * 
 * @author Josn
 * 
 */
public class OrderItemInfo {
	public String shopid;
	public String shopname;
	public String name;
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

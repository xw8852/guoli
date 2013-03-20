package com.guoli.hotel.bean;

public class OrderInfo {
/**
 * "orderinfo": {
        "orderno": "13633281542487320",
        "paymentmoney": "6600.00",
        "checkintime": "2013-03-17",
        "buynum": "3",
        "linkman": "汪辉",
        "mobile": "13004117304",
        "shopid": "89853",
        "shopname": "上海虹口三至喜来登酒店",
        "address": "四平路59号",
        "phone": "021-26010088",
        "productcode": "31000001000601",
        "name": "豪华房",
        "tradestatus": "0",
        "sucflag": "1",
        "creationdate": "1363328154"
    }
 */
	
	public String orderno;
	public String paymentmoney;
	public String checkintime;
	public String buynum;
	public String linkman;
	public String mobile;
	public String ispay;
	public String shopid;
	public String shopname;
	public String address;
	public String phone;
	public String productcode;
	public String name;
	public String checkoutime;
	/**
	 * 0-未付款（未成交） 1-已付款（成交） 2-取消（放弃） 3-交易关闭4-已退款，6-退款中，8-交易成功)
	 */
	public String tradestatus;
	/**
	 * 0,处理中； 1，成功；2传真
	 */
	public String sucflag;
	public String creationdate;
	
}

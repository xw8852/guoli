package com.guoli.hotel.net.request.bean;

public class OrderDetailBean {
	public String orderno;
	public String uid;
	public String mobile;

	public OrderDetailBean(String orderno, String uid) {
		super();
		this.orderno = orderno;
		this.uid = uid;
	}

	public static final OrderDetailBean buildBean(String orderno, String mobile) {
		OrderDetailBean bean = new OrderDetailBean(orderno, "0");
		bean.mobile = mobile;
		return bean;
	}

}

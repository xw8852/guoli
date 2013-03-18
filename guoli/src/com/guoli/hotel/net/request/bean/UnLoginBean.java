package com.guoli.hotel.net.request.bean;

public class UnLoginBean {
	/**
	 * 需要查询订单的用户手机号码
	 */
	public String mobile;
	/**
	 * 页码，是从1开始
	 */
	public String pageno;

	public UnLoginBean(String mobile, int pageno) {
		super();
		this.mobile = mobile;
		this.pageno = String.valueOf(Math.max(1, pageno));
	}

	public UnLoginBean(String mobile) {
		super();
		this.mobile = mobile;
	}

}

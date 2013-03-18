package com.guoli.hotel.net.request.bean;

public class UserOderListBean {
	/**
	 * 用户的uuid
	 */
	public String uid;
	/**
	 * 页码，是从1开始
	 */
	public String pageno;

	public UserOderListBean(String uid, int pageno) {
		super();
		this.uid = uid;
		this.pageno = String.valueOf(Math.max(1, pageno));
	}

	public UserOderListBean(String uid) {
		super();
		this.uid = uid;
	}
}

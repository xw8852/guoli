package com.guoli.hotel.net.request.bean;

public class GetIdentifyBean {

	// 手机号码
	public String mobile;
	// 防止非法访问
	public String glkey;

	public GetIdentifyBean(String mobile, String glkey) {
		super();
		this.mobile = mobile;
		this.glkey = glkey;
	}

}

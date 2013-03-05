package com.guoli.hotel.net.request.bean;

public class UserRegisterBean {

	// 用户名
	public String mobile;
	// 密码
	public String password;
	// 短信验证码
	public String checkcode;

	public UserRegisterBean(String mobile, String password, String checkcode) {
		super();
		this.mobile = mobile;
		this.password = password;
		this.checkcode = checkcode;
	}

}

package com.guoli.hotel.net.request.bean;

public class UserRegisterBean {

	// 用户名
	public String username;
	// 密码
	public String password;
	// 短信验证码
	public String authcode;

	public UserRegisterBean(String username, String password, String authcode) {
		super();
		this.username = username;
		this.password = password;
		this.authcode = authcode;
	}

}

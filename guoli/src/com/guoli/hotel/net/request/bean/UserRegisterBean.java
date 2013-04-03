package com.guoli.hotel.net.request.bean;

public class UserRegisterBean {

	// 手机号
	public String mobile;
	// 用户名
	public String username;
	// 密码
	public String password;
	// 短信验证码
	public String authcode;

	/**
	 * UserRegisterBean
	 * 
	 * @Description: 注册Bean
	 * @param @param username
	 * @param @param password
	 * @param @param authcode
	 */
	public UserRegisterBean(String username, String password, String authcode) {
		super();
		this.username = username;
		this.password = password;
		this.authcode = authcode;
	}

	/**
	 * UserRegisterBean
	 * 
	 * @Description: 验证码校验Bean
	 * @param @param mobile
	 * @param @param authcode
	 */
	public UserRegisterBean(String mobile, String authcode) {
		super();
		this.mobile = mobile;
		this.authcode = authcode;
	}

}

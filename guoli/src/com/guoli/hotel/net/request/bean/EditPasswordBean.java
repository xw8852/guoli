package com.guoli.hotel.net.request.bean;

public class EditPasswordBean {

	// 用户uid
	public String uid;
	// 用户电话号码
	public String mobile;
	// 用户旧密码
	public String password;
	// 用户新密码
	public String newpassword;

	/**
	 * EditPasswordBean
	 * 
	 * @Description: TODO 登陆后修改密码
	 * @param @param uid
	 * @param @param mobile
	 * @param @param password
	 * @param @param newpassword
	 */
	public EditPasswordBean(String uid, String mobile, String password, String newpassword) {
		super();
		this.uid = uid;
		this.mobile = mobile;
		this.password = password;
		this.newpassword = newpassword;
	}

	/**
	 * EditPasswordBean
	 * 
	 * @Description: 忘记密码修改密码
	 * @param @param mobile
	 * @param @param password
	 */
	public EditPasswordBean(String mobile, String password) {
		super();
		this.mobile = mobile;
		this.password = password;
	}

}

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

	public EditPasswordBean(String uid, String mobile, String password, String newpassword) {
		super();
		this.uid = uid;
		this.mobile = mobile;
		this.password = password;
		this.newpassword = newpassword;
	}

}

package com.guoli.hotel.activity.user;

import com.google.gson.annotations.SerializedName;

public class RegisterUserInfo {

	@SerializedName("userid")
	public String userid;

	@SerializedName("username")
	public String username;

	@SerializedName("email")
	public String email;

	@SerializedName("mobile")
	public String mobile;

	@SerializedName("type")
	public String type;

	@SerializedName("regdate")
	public String regdate;

	@SerializedName("lastdate")
	public String lastdate;

	@SerializedName("regip")
	public String regip;

	@SerializedName("lastip")
	public String lastip;

}

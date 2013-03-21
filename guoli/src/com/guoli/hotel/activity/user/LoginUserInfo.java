package com.guoli.hotel.activity.user;

import com.google.gson.annotations.SerializedName;

public class LoginUserInfo {

	@SerializedName("uid")
	public String uid;

	@SerializedName("username")
	public String username;

	@SerializedName("mobile")
	public String mobile;

	@SerializedName("email")
	public String email;

}

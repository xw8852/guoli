package com.guoli.hotel.activity.user;

import com.google.gson.annotations.SerializedName;

public class Register {

	@SerializedName("param")
	private String param;

	@SerializedName("authcode")
	private String authcode;

	@SerializedName("success")
	private String success;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}

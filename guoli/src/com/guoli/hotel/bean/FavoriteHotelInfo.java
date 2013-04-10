package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/***
 * 
 * @ClassName: FavoriteHotelInfo
 * @author Abel
 * @Description: 用户收藏
 * @date 2013-4-1 上午10:40:42
 * 
 */

public class FavoriteHotelInfo {

	@SerializedName("id")
	public String id;

	@SerializedName("userid")
	public String userid;

	@SerializedName("shopid")
	public String shopid;

	@SerializedName("shopname")
	public String shopname;

	@SerializedName("address")
	public String address;

	@SerializedName("phone")
	public String phone;

	@SerializedName("creationdate")
	public String creationdate;

	@SerializedName("indate")
	public String startDate;

	@SerializedName("outdate")
	public String endDate;

	@SerializedName("picpath")
	public String picpath;

	@SerializedName("filename")
	public String filename;
}

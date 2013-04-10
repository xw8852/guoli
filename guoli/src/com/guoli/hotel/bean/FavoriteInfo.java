package com.guoli.hotel.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class FavoriteInfo {

	@SerializedName("pageno")
	public String pageno;

	@SerializedName("pagesize")
	public String pagesize;

	@SerializedName("pagecount")
	public String pagecount;

	@SerializedName("result")
	public List<FavoriteHotelInfo> hotelInfos;

	public String getPageno() {
		return pageno;
	}

	public void setPageno(String pageno) {
		this.pageno = pageno;
	}

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	public String getPagecount() {
		return pagecount;
	}

	public void setPagecount(String pagecount) {
		this.pagecount = pagecount;
	}

	public List<FavoriteHotelInfo> getHotelInfos() {
		return hotelInfos;
	}

	public void setHotelInfos(List<FavoriteHotelInfo> hotelInfos) {
		this.hotelInfos = hotelInfos;
	}

}

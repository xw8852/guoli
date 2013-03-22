package com.guoli.hotel.net.request.bean;

public class FavoriteUserBean {

	/**
	 * user uid
	 */
	public String uid;

	/**
	 * 页码, 1开始
	 */
	public String pageno;

	/**
	 * 入住人姓名
	 */
	public String pname;

	/**
	 * 常用用户id
	 */
	public String id;

	/**
	 * 
	 * @Description: 获取常用入住人bean
	 * @param uid
	 * @param pageno
	 * @return
	 */
	public FavoriteUserBean(String uid, int pageno) {
		super();
		this.uid = uid;
		this.pageno = String.valueOf(Math.max(1, pageno));
	}

	/**
	 * 
	 * @Description: 新增/删除常用入住人bean
	 * @param uid
	 * @param pname
	 * @return
	 */
	public FavoriteUserBean(String uid, String pname, String id) {
		super();
		this.id = id;
		this.uid = uid;
		this.pname = pname;
	}

}

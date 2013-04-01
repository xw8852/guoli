package com.guoli.hotel.utils;

import java.text.DecimalFormat;

public class DiscountUtils {

	/**
	 * 
	 * formatDiscount:格式化折扣. <br/>
	 * 
	 * @param desc
	 *            R.String.discount_desc
	 * @param discount
	 *            String 折扣
	 * @return String
	 */
	public static String formatDiscount(String desc, String discount) {
		double _discount = Double.parseDouble(discount);
		String dis = new DecimalFormat("0.#").format(_discount * 10);
		return String.format(desc, dis);
	}

	/**
	 * 
	 * formatDiscount:格式化折扣. <br/>
	 * 
	 * @param discount
	 *            String 折扣
	 * @return String
	 */
	public static String formatDiscount(String discount) {
		double _discount = Double.parseDouble(discount);
		String dis = new DecimalFormat("0.#").format(_discount * 10);
		return dis;
	}

}

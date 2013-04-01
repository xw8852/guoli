package com.guoli.hotel.utils;

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
//		String dis = new DecimalFormat("0.#").format(_discount * 10);
		return String.format(desc, _discount);
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
//		String dis = new DecimalFormat("0.#").format(_discount * 10);
		return discount+"";
	}

}

package net.lala.CouponCodes.api.coupon;

import java.util.HashMap;

/**
 * RankCoupon.java - Provides an extension of a coupon for rank use
 * @author mike101102
 */
public class RankCoupon extends Coupon {

	private String group;
	
	public RankCoupon(String name, String group, int usetimes, HashMap<String, Boolean> usedplayers) {
		super(name, usetimes, usedplayers);
		this.group = group;
	}
	
	public String getGroup() {
		return group;
	}
}
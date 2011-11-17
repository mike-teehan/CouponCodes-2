package net.lala.CouponCodes.Configuration;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lib.PatPeter.SQLibrary.SQLite;
import net.lala.CouponCodes.Coupon;
import net.lala.CouponCodes.CouponCodes;

import org.bukkit.ChatColor;

public class SQLiteCfg {

	private CouponCodes plugin;
	private SQLite sql;
	public SQLiteCfg(CouponCodes plugin, SQLite sql){
		this.plugin = plugin;
		this.sql = sql;
	}
	// 1: name 2: use times 3:ArrayList of itemids 4:Array of used players
	
	public ArrayList<String> getCouponsFromDatabase(){
		ResultSet rs = sql.query("SELECT name FROM couponcodes;");
		ArrayList<String> cn = new ArrayList<String>();
		
		try {
			while (rs.next()){
				cn.add(rs.getString(1));
			}
			return cn;
		} catch (SQLException e){
			plugin.send("SQLException while getting the coupons from the database");
			e.printStackTrace();
		}
		return null;
	}
	
	public String addCoupon(Coupon coupon){
		if (getCouponsFromDatabase().contains(coupon.getName())){
			return String.valueOf(ChatColor.RED + "Coupon already exists!");
		}
		
		try {
			PreparedStatement ps = sql.getConnection().prepareStatement("INSERT INTO couponcodes VALUES (?, ?, ?, ?)");			
			ps.setString(1, coupon.getName());
			ps.setInt(2, coupon.getUseTimesLeft());
			ps.setString(3, coupon.FormatIDsToDbString());
			ps.setString(4, "mike_101_102,gaylord");
			ps.addBatch();
			sql.getConnection().setAutoCommit(false);
			ps.executeBatch();
			sql.getConnection().setAutoCommit(true);
		} catch (SQLException e){
			plugin.send("Error while using SQLite database to add coupon");
			e.printStackTrace();
			return ChatColor.RED + "Error while using SQLite database to add coupon, coupon creation failed (SQLException)";
		}
		
		try{
			sql.getConnection().close();
			sql.close();
		} catch (SQLException e){
			plugin.send("Error while closing SQLite connections");
			return ChatColor.RED + "Error while closing SQLite connections, coupon creation failed";
		}
		return ChatColor.GREEN + "Coupon "+ChatColor.LIGHT_PURPLE+coupon.getName()+ChatColor.GREEN+" has been created!";
	}
	
	public String removeCoupon(Coupon coupon){
		if (!this.getCouponsFromDatabase().contains(coupon.getName())){
			return ChatColor.RED + "Coupon does not exist!";
		}else{
			sql.query("DELETE FROM couponcodes WHERE name = '"+coupon.getName()+"'");
			return ChatColor.GREEN + "Coupon " + ChatColor.GOLD + coupon.getName() + ChatColor.GREEN + " has been removed!";
		}
	}
}
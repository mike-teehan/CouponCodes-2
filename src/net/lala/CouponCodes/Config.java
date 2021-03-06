package net.lala.CouponCodes;


import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
	
	private FileConfiguration config;
	
	public Config(Plugin plugin) {
		this.config = plugin.getConfig();
		
		if (!(new File("plugins/CouponCodes/config.yml").exists()))
			plugin.saveDefaultConfig();
		if (!config.options().copyDefaults(true).configuration().equals(config))
			plugin.saveConfig();
	}
	
	public boolean getUseThread() {
		return config.getBoolean("use-thread", true);
	}
	
	public boolean getVault() {
		return config.getBoolean("use-vault", false);
	}
	
	public boolean getDebug() {
		return config.getBoolean("debug", false);
	}
	
	public String getSQLValue() {
		return config.getString("sql-type");
	}
	
	public String getHostname() {
		return config.getString("MySQL-options.hostname");
	}
	
	public String getPort() {
		return config.getString("MySQL-options.port");
	}
	
	public String getDatabase() {
		return config.getString("MySQL-options.database");
	}
	
	public String getUsername() {
		return config.getString("MySQL-options.username");
	}
	
	public String getPassword() {
		return config.getString("MySQL-options.password");
	}
}
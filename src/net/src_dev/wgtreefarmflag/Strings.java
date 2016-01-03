package net.src_dev.wgtreefarmflag;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class Strings {
	public final static String noWorldGuard = "[WGTreeFarmFlag] Could not find WorldGuard. Disabling.";
	public final static String noWGCustomFlags = "[WGTreeFarmFlag] Could not find WGCustomFlags. Disabling.";
	public static String[] enableInfo = {
		"[WGTreeFarmFlag] Version " + WGTreeFarmFlag.version,
		"[WGTreeFarmFlag] By S_Ryan", 
		"[WGTreeFarmFlag] Successfully enabled."
	};
	public static String[] disableInfo = {
		"[WGTreeFarmFlag] Disabled."
	};
	public final static String checkingAllRegions = "WGTreeFarmFlag -> Checking all regions for farm flags...";
	public final static String doneCheckingRegions = "WGTreeFarmFlag -> Done checking regions.";
	
	public static List<String> info;
	public static List<String> help;
	public static String commandNonExistant;
	public static String noPermission;
	public static String reloaded;
	public static String cannotBreakSapling;
	public static String cannotBreakMushroom;
	
	public static void loadStrings(FileConfiguration config){
		info = config.getStringList("strings.info");
		help = config.getStringList("strings.help");
		commandNonExistant = config.getString("strings.commandNonExistant");
		noPermission = config.getString("strings.noPermission");
		reloaded = config.getString("strings.reloaded");
		cannotBreakSapling = config.getString("strings.cannotBreakSapling");
		cannotBreakMushroom = config.getString("strings.cannotBreakMushroom");
	}
}

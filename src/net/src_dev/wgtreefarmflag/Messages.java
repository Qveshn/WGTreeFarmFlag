package net.src_dev.wgtreefarmflag;

import net.src_dev.srclibrary.messageapi.Message;
import net.src_dev.srclibrary.messageapi.MessageAPI;
import net.src_dev.srclibrary.messageapi.MultiMessage;

public class Messages extends MessageAPI {
	public Messages(WGTreeFarmFlag plugin){
		super(plugin, plugin.getConfig());
	}
	
	public static MultiMessage enabled;
	public static Message disabled;
	
	public static Message checkingAllRegions; 
	public static Message doneCheckingRegions; 
	public static Message invalidDebugLevel; 
	public static Message noWorldGuard;
	public static Message noWGCustomFlags;
	
	public static MultiMessage info;
	public static MultiMessage help;
	
	public static Message commandNonExistant;
	public static Message noPermission;
	public static Message reloaded;
	public static Message cannotBreakSapling;
	public static Message cannotBreakMushroom;
	public static Message debugHeader;
	
	public void load(){
		setKeyPrefix("strings.");
		
		enabled = createMultiMessage(new String[]{
				"[WGTreeFarmFlag] Version " + WGTreeFarmFlag.version, 
				"[WGTreeFarmFlag] By S_Ryan", 
				"[WGTreeFarmFlag] Successfully enabled."
				});
		disabled = createMessage("[WGTreeFarmFlag] Disabled.");
		
		checkingAllRegions = createMessage("[WGTreeFarmFlag] Gathering farm regions and information..");
		doneCheckingRegions = createMessage("[WGTreeFarmFlag] Done checking regions.");
		invalidDebugLevel = createMessage("[WGTreeFarmFlag] Invalid debug level (must be 1-4). Using default.");
		noWorldGuard = createMessage("[WGTreeFarmFlag] Could not find WorldGuard. Disabling.");
		noWGCustomFlags = createMessage("[WGTreeFarmFlag] Could not find WGCustomFlags. Disabling.");
		
		info = getConfigMultiMessage("info").color().replace("%version%", WGTreeFarmFlag.version);
		help = getConfigMultiMessage("help").color();
		
		commandNonExistant = getConfigMessage("commandNonExistant").color();
		noPermission = getConfigMessage("noPermission").color();
		reloaded = getConfigMessage("reloaded").color();
		cannotBreakSapling = getConfigMessage("cannotBreakSapling").color();
		cannotBreakMushroom = getConfigMessage("cannotBreakMushroom").color();
		debugHeader = getConfigMessage("debug-header", false).color();
	}
}

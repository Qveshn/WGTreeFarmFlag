package net.src_dev.wgtreefarmflag;

import net.src_dev.wgtreefarmflag.library.messageapi.Message;
import net.src_dev.wgtreefarmflag.library.messageapi.MessageHandler;
import net.src_dev.wgtreefarmflag.library.messageapi.MultiMessage;

public class Messages extends MessageHandler {
	public Messages(WGTreeFarmFlag plugin){
		super(plugin.getConfig());
		options().setKeyPrefix("strings.");
		load();
	}
	
	public static MultiMessage enabled = new MultiMessage(new String[]{
			"Version " + WGTreeFarmFlag.version, 
			"By S_Ryan", 
			"Successfully enabled."
			});
	public static Message disabled = new Message("Disabled.");
	
	public static Message checkingAllRegions = new Message("Gathering farm regions and information.."); 
	public static Message doneCheckingRegions = new Message("Done checking regions."); 
	public static Message invalidDebugLevel = new Message("Invalid debug level (must be 1-4). Using default.");
	public static Message noWorldGuard = new Message("Could not find WorldGuard. Disabling.");
	public static Message noWGCustomFlags = new Message("Could not find WGCustomFlags. Disabling.");
	
	public static MultiMessage info;
	public static MultiMessage help;
	
	public static Message commandNonExistant;
	public static Message noPermission;
	public static Message reloaded;
	public static Message cannotBreakSapling;
	public static Message cannotBreakMushroom;
	public static Message debugHeader;
	
	public void load(){
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

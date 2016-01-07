package net.src_dev.wgtreefarmflag;

import org.bukkit.configuration.file.FileConfiguration;

import net.src_dev.srclibrary.messageapi.Message;
import net.src_dev.srclibrary.messageapi.MessageAPI;
import net.src_dev.srclibrary.messageapi.MultiMessage;

public class Messages extends MessageAPI {
	public Messages(FileConfiguration config){
		super(config);
	}
	
	public static Message checkingAllRegions = new Message("[WGTreeFarmFlag] Gathering farm regions and information..");
	public static Message doneCheckingRegions = new Message("[WGTreeFarmFlag] Done checking regions.");
	public static Message invalidDebugLevel = new Message("[WGTreeFarmFlag] Invalid debug level (must be 1-4). Using default.");
	
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
		
		info = getConfigMultiMessage("info").color();
		help = getConfigMultiMessage("help").color();
		
		commandNonExistant = getConfigMessage("commandNonExistant").color();
		noPermission = getConfigMessage("noPermission").color();
		reloaded = getConfigMessage("reloaded").color();
		cannotBreakSapling = getConfigMessage("cannotBreakSapling").color();
		cannotBreakMushroom = getConfigMessage("cannotBreakMushroom").color();
		debugHeader = getConfigMessage("debugHeader", false).color();
	}
}

package net.src_dev.wgtreefarmflag;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import net.src_dev.wgtreefarmflag.listeners.BlockListener;

public final class WGTreeFarmFlag extends JavaPlugin{
	public final static String version = "1.1.23";
	public final static int configVersion = 2;
	
	private boolean debug;
	private int debugLevel;
	private int defaultDebugLevel = 2;
	
	private WorldGuardPlugin worldGuard;
	private WGCustomFlagsPlugin wgCustomFlags;
	
	public static final StateFlag TREE_FARM = new StateFlag("tree-farm", false);
	public static final StateFlag MUSHROOM_FARM = new StateFlag("mushroom-farm", false);
	
	public HashMap<World, ProtectedRegion> treeFarms; //unused at the moment
	public HashMap<World, ProtectedRegion> mushroomFarms;
	
	public HashMap<ProtectedRegion, List<Block>> farmSaplings; //unused at the moment
	public HashMap<ProtectedRegion, List<Block>> farmMushrooms;
	
	
	@Override
	public void onEnable(){	
		saveDefaultConfig();
		debug = getConfig().getBoolean("debug");
		debugLevel = getConfig().getInt("debug-level");
		
		logDebug("Starting onEnable.", 2);
		
		if(debugLevel < 1 || debugLevel > 4){
			logWarning(Strings.invalidDebugLevel);
			debugLevel = defaultDebugLevel;
		}
		
		worldGuard = getWorldGuardPlugin();
		if(worldGuard == null){
			logWarning(Strings.noWorldGuard);
			getServer().getPluginManager().disablePlugin(this);
		}
		wgCustomFlags = getWGCustomFlagsPlugin();
		if(wgCustomFlags == null){
			logWarning(Strings.noWGCustomFlags);
			getServer().getPluginManager().disablePlugin(this);
		}
		
		wgCustomFlags.addCustomFlag(TREE_FARM);
		wgCustomFlags.addCustomFlag(MUSHROOM_FARM);
	
		File configFile = new File(getDataFolder() + "config.yml");
		if(!(getConfig().getInt("config-version") == configVersion)){
			saveNewConfig(configFile);
		}
		Strings.loadStrings(getConfig());
		
		logWarning(Strings.checkingAllRegions);
		treeFarms = new HashMap<World, ProtectedRegion>();
		mushroomFarms = new HashMap<World, ProtectedRegion>();
		for(World w:getServer().getWorlds()){
			logDebug("Checking regions within world " + w.getName() + ".", 3);
			for(Entry<String, ProtectedRegion> entry:getWorldGuard().getRegionManager(w).getRegions().entrySet()){
				ProtectedRegion r = entry.getValue();
				logDebug("Checking region " + r.getId() + " for farm flags.", 4);
				if(r.getFlag(WGTreeFarmFlag.TREE_FARM) == StateFlag.State.ALLOW){
					logDebug("Adding region " + r.getId() + " to tree farm list.", 3);
					treeFarms.put(w, r);
				}
				if(r.getFlag(WGTreeFarmFlag.MUSHROOM_FARM) == StateFlag.State.ALLOW){
					logDebug("Adding region " + r.getId() + " to mushroom farm list.", 3);
					mushroomFarms.put(w, r);
				}
			}
		}
		farmSaplings = new HashMap<ProtectedRegion, List<Block>>();
		farmMushrooms = new HashMap<ProtectedRegion, List<Block>>();
		for(Entry<World, ProtectedRegion> entry:treeFarms.entrySet()){
			World w = entry.getKey();
			ProtectedRegion r = entry.getValue();
			List<Integer> coords;
			List<Block> blocks;
			List<Block> saplings;
			Block block;
			coords = new ArrayList<Integer>();
			coords.add(r.getMinimumPoint().getBlockX());
			coords.add(r.getMinimumPoint().getBlockY());
			coords.add(r.getMinimumPoint().getBlockZ());
			coords.add(r.getMaximumPoint().getBlockX());
			coords.add(r.getMaximumPoint().getBlockY());
			coords.add(r.getMaximumPoint().getBlockZ());
			blocks = new ArrayList<Block>();
			for (int x = Math.min(coords.get(0), coords.get(3)); x <= Math.max(coords.get(0), coords.get(3)); x++) {
	            for (int y = Math.min(coords.get(1), coords.get(4)); y <= Math.max(coords.get(1), coords.get(4)); y++) {
	                for (int z = Math.min(coords.get(2), coords.get(5)); z <= Math.max(coords.get(2), coords.get(5)); z++) {
	                    block = w.getBlockAt(x, y, z);
	                    blocks.add(block);
	                }
	            }
	        }
			saplings = new ArrayList<Block>();
			for(Block b:blocks){
				if(b.getType() == Material.SAPLING){
					saplings.add(b);
				}
			}
			farmSaplings.put(r, saplings);
		}
		for(Entry<World, ProtectedRegion> entry:mushroomFarms.entrySet()){
			World w = entry.getKey();
			ProtectedRegion r = entry.getValue();
			List<Integer> coords;
			List<Block> blocks;
			List<Block> mushrooms;
			Block block;
			coords = new ArrayList<Integer>();
			coords.add(r.getMinimumPoint().getBlockX());
			coords.add(r.getMinimumPoint().getBlockY());
			coords.add(r.getMinimumPoint().getBlockZ());
			coords.add(r.getMaximumPoint().getBlockX());
			coords.add(r.getMaximumPoint().getBlockY());
			coords.add(r.getMaximumPoint().getBlockZ());
			blocks = new ArrayList<Block>();
			for (int x = Math.min(coords.get(0), coords.get(3)); x <= Math.max(coords.get(0), coords.get(3)); x++) {
	            for (int y = Math.min(coords.get(1), coords.get(4)); y <= Math.max(coords.get(1), coords.get(4)); y++) {
	                for (int z = Math.min(coords.get(2), coords.get(5)); z <= Math.max(coords.get(2), coords.get(5)); z++) {
	                    block = w.getBlockAt(x, y, z);
	                    blocks.add(block);
	                }
	            }
	        }
			mushrooms = new ArrayList<Block>();
			for(Block b:blocks){
				if(b.getType() == Material.RED_MUSHROOM || b.getType() == Material.BROWN_MUSHROOM){
					mushrooms.add(b);
				}
			}
			farmMushrooms.put(r, mushrooms);
		}
		logWarning(Strings.doneCheckingRegions);
		
		@SuppressWarnings("unused")
		int saplingGrowthChance = getConfig().getInt("sapling-growth-chance"); //unused at the moment
		int mushroomGrowthChance = getConfig().getInt("mushroom-growth-chance");
		@SuppressWarnings("unused")
		int saplingGrowthInterval = getConfig().getInt("sapling-growth-interval") * 20; //unused at the moment
		int mushroomGrowthInterval = getConfig().getInt("mushroom-growth-interval") * 20;
		
		if(getConfig().getBoolean("enable-sapling-interval-growth")) ;//unused at the moment
		if(getConfig().getBoolean("enable-mushroom-interval-growth")) getServer().getScheduler().scheduleSyncRepeatingTask(this, new MushroomIntervalGrower(this, mushroomGrowthChance), mushroomGrowthInterval, mushroomGrowthInterval);
		
		getServer().getPluginManager().registerEvents(new BlockListener(this), this);
		
		logInfo(Strings.enableInfo);
	}
	@Override
	public void onDisable(){
		reloadConfig();
		
		getServer().getScheduler().cancelTasks(this);
		
		HandlerList.unregisterAll(this);
		
		logInfo(Strings.disableInfo);
	}
	public void reload(){
		onDisable();
		onEnable();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("wgtreefarmflag")){
			if(args.length == 0){
				for(String s:Strings.info){
					sendMessage(sender, s.replace("%version%", version));
				}
				return true;
			}
			if(args[0].equalsIgnoreCase("help")){
				if(!sender.hasPermission("wgtreefarmflag.help")){
					sender.sendMessage(Strings.noPermission);
					return true;
				}
				for(String s:Strings.help){
					sendMessage(sender, s);
				}
				return true;
			}
			if(args[0].equalsIgnoreCase("reload")){
				if(!sender.hasPermission("wgtreefarmflag.reload")){
					sender.sendMessage(Strings.noPermission);
					return true;
				}
				reload();
				sendMessage(sender, Strings.reloaded);
				return true;
			}
			sendMessage(sender, Strings.commandNonExistant);
			return true;
		}
		return false;
	}
	
	public void saveNewConfig(File currentConfig){
		File oldConfig = new File(getDataFolder() + "config.yml.old");
		if(oldConfig.exists()){
			oldConfig.delete();
		}
		try{
			FileUtils.copyFile(currentConfig, oldConfig);
		}catch(IOException e){}
		currentConfig.delete();
		saveDefaultConfig();
	}
	
	public void sendMessage(CommandSender sender, String msg){
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	public void sendMessage(Player player, String msg){
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	public void logInfo(String info){
		getLogger().info(info);
	}
	public void logInfo(String[] info){
		for(String s:info) getLogger().info(s);
	}
	public void logWarning(String warning){
		getLogger().warning(warning);
	}
	public void logDebug(String debugInfo, int level){
		if(debug && level <= debugLevel) logWarning(Strings.debugHeader + debugInfo);
	}
	
	public WorldGuardPlugin getWorldGuard(){
		return worldGuard;
	}
	public WGCustomFlagsPlugin getWGCustomFlags(){
		return wgCustomFlags;
	}
	
	private WorldGuardPlugin getWorldGuardPlugin() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
        }
        return (WorldGuardPlugin) plugin;
    }
	private WGCustomFlagsPlugin getWGCustomFlagsPlugin(){
		Plugin plugin = getServer().getPluginManager().getPlugin("WGCustomFlags");
		if(plugin == null || !(plugin instanceof WGCustomFlagsPlugin)){
			return null;
		}
		return (WGCustomFlagsPlugin) plugin;
	}
}

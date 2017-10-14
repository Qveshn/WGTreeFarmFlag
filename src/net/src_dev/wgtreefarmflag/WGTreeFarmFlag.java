package net.src_dev.wgtreefarmflag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

//import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import net.src_dev.wgtreefarmflag.listeners.BlockListener;
import net.srv_dev.wgtreefarmflag.library.RegionFunctions;

public final class WGTreeFarmFlag extends JavaPlugin{
	public final static String version = "1.2.15";
		
	private boolean debug;
	private int debugLevel;
	private int defaultDebugLevel = 2;
	
	private WorldGuardPlugin worldGuard;
	//private WGCustomFlagsPlugin wgCustomFlags;
	
	public static final StateFlag TREE_FARM = new StateFlag("tree-farm", false);
	public static final StateFlag MUSHROOM_FARM = new StateFlag("mushroom-farm", false);
	
	public HashMap<World, ProtectedRegion> treeFarms; 
	public HashMap<World, ProtectedRegion> mushroomFarms;

	public WGTreeFarmFlag(){
		FlagRegistry flagRegistry = WorldGuardPlugin.inst().getFlagRegistry();
		flagRegistry.register(TREE_FARM);
		flagRegistry.register(MUSHROOM_FARM);
	}
	
	@Override
	public void onEnable(){	
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		new Messages(this);
		
		debug = getConfig().getBoolean("debug");
		debugLevel = getConfig().getInt("debug-level");
		if(debugLevel < 1 || debugLevel > 4){
			Messages.invalidDebugLevel.logAsWarning();
			debugLevel = defaultDebugLevel;
		}
		
		worldGuard = getWorldGuardPlugin();
		if(worldGuard == null){
			Messages.noWorldGuard.logAsSevere();
			getServer().getPluginManager().disablePlugin(this);
		}
//		wgCustomFlags = getWGCustomFlagsPlugin();
//		if(wgCustomFlags == null){
//			Messages.noWGCustomFlags.logAsSevere();
//			getServer().getPluginManager().disablePlugin(this);
//		}
		
//		wgCustomFlags.addCustomFlag(TREE_FARM);
//		wgCustomFlags.addCustomFlag(MUSHROOM_FARM);
		
		Messages.checkingAllRegions.logAsWarning();
		treeFarms = new HashMap<World, ProtectedRegion>();
		mushroomFarms = new HashMap<World, ProtectedRegion>();
		for(World w:getServer().getWorlds()){
			for(Entry<String, ProtectedRegion> entry:getWorldGuard().getRegionManager(w).getRegions().entrySet()){
				ProtectedRegion r = entry.getValue();
				if(r.getFlag(WGTreeFarmFlag.TREE_FARM) == StateFlag.State.ALLOW){
					treeFarms.put(w, r);
				}
				if(r.getFlag(WGTreeFarmFlag.MUSHROOM_FARM) == StateFlag.State.ALLOW){
					mushroomFarms.put(w, r);
				}
			}
		}
		Messages.doneCheckingRegions.logAsWarning();
		
		int saplingGrowthChance = getConfig().getInt("sapling-growth-chance");
		int mushroomGrowthChance = getConfig().getInt("mushroom-growth-chance");
		int saplingGrowthInterval = getConfig().getInt("sapling-growth-interval") * 20;
		int mushroomGrowthInterval = getConfig().getInt("mushroom-growth-interval") * 20;
		
		if(getConfig().getBoolean("enable-sapling-interval-growth")) getServer().getScheduler().scheduleSyncRepeatingTask(this, new SaplingIntervalGrower(this, saplingGrowthChance), saplingGrowthInterval, saplingGrowthInterval);
		if(getConfig().getBoolean("enable-mushroom-interval-growth")) getServer().getScheduler().scheduleSyncRepeatingTask(this, new MushroomIntervalGrower(this, mushroomGrowthChance), mushroomGrowthInterval, mushroomGrowthInterval);
		
		getServer().getPluginManager().registerEvents(new BlockListener(this), this);
		
		Messages.enabled.logAsWarning();
	}
	@Override
	public void onDisable(){
		reloadConfig();
		
		getServer().getScheduler().cancelTasks(this);
		
		HandlerList.unregisterAll(this);
		
		Messages.disabled.logAsWarning();
	}
	public void reload(){
		onDisable();
		onEnable();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("wgtreefarmflag")){
			if(args.length == 0){
				Messages.info.send(sender);
				return true;
			}
			if(args[0].equalsIgnoreCase("help")){
				if(!sender.hasPermission("wgtreefarmflag.help")){
					Messages.noPermission.send(sender);
					return true;
				}
				Messages.help.send(sender);
				return true;
			}
			if(args[0].equalsIgnoreCase("reload")){
				if(!sender.hasPermission("wgtreefarmflag.reload")){
					Messages.noPermission.send(sender);
					return true;
				}
				reload();
				Messages.reloaded.send(sender);
				return true;
			}
			Messages.commandNonExistant.send(sender);
			return true;
		}
		return false;
	}
	public void logDebug(String debugInfo, int level){
		if(debug && level <= debugLevel) Messages.debugHeader.copy().append(debugInfo).color().sendToConsole();
	}
	
	public WorldGuardPlugin getWorldGuard(){
		return worldGuard;
	}
//	public WGCustomFlagsPlugin getWGCustomFlags(){
//		return wgCustomFlags;
//	}
	
	private WorldGuardPlugin getWorldGuardPlugin() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
        }
        return (WorldGuardPlugin) plugin;
    }
//	private WGCustomFlagsPlugin getWGCustomFlagsPlugin(){
//		Plugin plugin = getServer().getPluginManager().getPlugin("WGCustomFlags");
//		if(plugin == null || !(plugin instanceof WGCustomFlagsPlugin)){
//			return null;
//		}
//		return (WGCustomFlagsPlugin) plugin;
//	}
	public HashMap<ProtectedRegion, List<Block>> getFarmSaplings(){
		HashMap<ProtectedRegion, List<Block>> farmSaplings = new HashMap<ProtectedRegion, List<Block>>();
		for(Entry<World, ProtectedRegion> entry:treeFarms.entrySet()){
			World w = entry.getKey();
			ProtectedRegion r = entry.getValue();
			List<Block> saplings;
			List<Block> blocks = RegionFunctions.getBlocksInRegion(w, r);
			saplings = new ArrayList<Block>();
			for(Block b:blocks){
				if(b.getType() == Material.SAPLING){
					saplings.add(b);
				}
			}
			farmSaplings.put(r, saplings);
		}
		return farmSaplings;
	}
	public HashMap<ProtectedRegion, List<Block>> getFarmMushrooms(){
		HashMap<ProtectedRegion, List<Block>> farmMushrooms = new HashMap<ProtectedRegion, List<Block>>();
		for(Entry<World, ProtectedRegion> entry:mushroomFarms.entrySet()){
			World w = entry.getKey();
			ProtectedRegion r = entry.getValue();
			List<Block> mushrooms;
			List<Block> blocks = RegionFunctions.getBlocksInRegion(w, r);
			mushrooms = new ArrayList<Block>();
			for(Block b:blocks){
				if(b.getType() == Material.RED_MUSHROOM || b.getType() == Material.BROWN_MUSHROOM){
					mushrooms.add(b);
				}
			}
			farmMushrooms.put(r, mushrooms);
		}
		return farmMushrooms;
	}
}

package net.src_dev.wgtreefarmflag;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.StateFlag;

import net.src_dev.wgtreefarmflag.listeners.BlockListener;

public final class WGTreeFarmFlag extends JavaPlugin{
	private WorldGuardPlugin worldGuard;
	private WGCustomFlagsPlugin wgCustomFlags;
	
	public static final StateFlag TREE_FARM = new StateFlag("tree-farm", false);
	
	@Override
	public void onEnable(){
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
		
		saveDefaultConfig();
		Strings.loadStrings(getConfig());
		
		getServer().getPluginManager().registerEvents(new BlockListener(this), this);
		
		for(String s:Strings.enableInfo){
			logInfo(s);
		}
	}
	@Override
	public void onDisable(){
		reloadConfig();
		
		HandlerList.unregisterAll(this);
		
		for(String s:Strings.disableInfo){
			logInfo(s);
		}
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
					sendMessage(sender, s.replace("%version%", Strings.version));
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
	
	public void sendMessage(CommandSender sender, String msg){
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	public void sendMessage(Player player, String msg){
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	public void logInfo(String info){
		getLogger().info(info);
	}
	public void logWarning(String warning){
		getLogger().warning(warning);
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

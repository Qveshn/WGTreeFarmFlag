package net.src_dev.srclibrary;

import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public abstract class JavaPlugin extends org.bukkit.plugin.java.JavaPlugin{
	public abstract void reload();
	public void logInfo(String info){
		getLogger().info(info);
	}
	public void logInfo(String[] info){
		for(String i:info){
			getLogger().info(i);
		}
	}
	public void logWarning(String warning){
		getLogger().warning(warning);
	}
	public void logWarning(String[] warning){
		for(String w:warning){
			getLogger().warning(w);
		}
	}
	public void messageConsole(String message, boolean translateAlternateColorCodes){
		CommandSender console = getServer().getConsoleSender();
		if(translateAlternateColorCodes){
			console.sendMessage(ChatColor.translateAlternateColorCodes(ChatFunctions.getAlternateColorCodeChar(), message));
		}
		else{
			console.sendMessage(message);
		}
	}
	public void messageConsole(String[] messages, boolean translateAlternateColorCodes){
		CommandSender console = getServer().getConsoleSender();
		if(translateAlternateColorCodes){
			for(String m:messages){
				console.sendMessage(ChatColor.translateAlternateColorCodes(ChatFunctions.getAlternateColorCodeChar(), m));
			}
		}
		else{
			for(String m:messages){
				console.sendMessage(m);
			}
		}
	}
}

package net.src_dev.srclibrary.messageapi;

import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MessageAPI {
	private JavaPlugin plugin;
	private FileConfiguration config;
	private String keyPrefix = "";
	public MessageAPI(JavaPlugin plugin, FileConfiguration config){
		this.plugin = plugin;
		this.config = config;
		load();
	}
	
	public abstract void load();
	
	public void setKeyPrefix(String prefix){
		keyPrefix = prefix;
	}
	public String getKeyPrefix(){
		return keyPrefix;
	}
	
	public Message getConfigMessage(String key){
		return new Message(config.getString(getKeyPrefix() + key)).setPlugin(plugin);
	}
	public MultiMessage getConfigMultiMessage(String key){
		return new MultiMessage(config.getStringList(getKeyPrefix() + key)).setPlugin(plugin);
	}
	
	public Message getConfigMessage(String key, boolean useKeyPrefix){
		if(useKeyPrefix) return new Message(config.getString(getKeyPrefix() + key)).setPlugin(plugin);
		else return new Message(config.getString(key)).setPlugin(plugin);
	}
	public MultiMessage getConfigMultiMessage(String key, boolean useKeyPrefix){
		if(useKeyPrefix) return new MultiMessage(config.getStringList(getKeyPrefix() + key)).setPlugin(plugin);
		else return new MultiMessage(config.getStringList(key)).setPlugin(plugin);
	}
	
	public Message createMessage(String text){
		return new Message(text).setPlugin(plugin);
	}
	public MultiMessage createMultiMessage(List<String> text){
		return new MultiMessage(text).setPlugin(plugin);
	}
	public MultiMessage createMultiMessage(String[] text){
		return new MultiMessage(Arrays.asList(text)).setPlugin(plugin);
	}
}
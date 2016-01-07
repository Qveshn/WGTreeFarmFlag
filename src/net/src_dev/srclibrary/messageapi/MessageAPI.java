package net.src_dev.srclibrary.messageapi;

import org.bukkit.configuration.file.FileConfiguration;

public abstract class MessageAPI {
	private FileConfiguration config;
	private String keyPrefix = "";
	public MessageAPI(FileConfiguration config){
		this.config = config;
		load();
	}
	
	public abstract void load();
	
	public void setKeyPrefix(String prefix){
		keyPrefix = prefix;
	}
	
	public Message getConfigMessage(String key){
		return new Message(config.getString(keyPrefix + key));
	}
	public MultiMessage getConfigMultiMessage(String key){
		return new MultiMessage(config.getStringList(keyPrefix + key));
	}
	
	public Message getConfigMessage(String key, boolean useKeyPrefix){
		if(useKeyPrefix) return new Message(config.getString(keyPrefix + key));
		else return new Message(config.getString(key));
	}
	public MultiMessage getConfigMultiMessage(String key, boolean useKeyPrefix){
		if(useKeyPrefix) return new MultiMessage(config.getStringList(keyPrefix + key));
		else return new MultiMessage(config.getStringList(key));
	}
}

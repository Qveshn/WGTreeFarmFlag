/**
 * Copyright 2016 src-dev.net
 * All rights reserved.
 */

package net.srv_dev.wgtreefarmflag.library;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The MessageHandler object class.
 * Class is abstract. Must be subclassed.
 * A subclass of this is used to create and control Messages and MultiMessages.
 */
public abstract class MessageHandler {

	private JavaPlugin plugin;
	private FileConfiguration config;
	
	
	private Options options = new Options();
	
	/**
	 * Only constructor. This is used to set the plugin and config variables.
	 * @param plugin Should be an instance of JavaPlugin.
	 * @param config Can be any FileConfiguration instance. Used to get messages from config.
	 */
	public MessageHandler(JavaPlugin plugin, FileConfiguration config) {
		this.plugin = plugin;
		this.config = config;		
	}
	
	/**
	 * Loads all of the messages. Should only be called by child classes.
	 */
	public abstract void load();
	
	/**
	 * Sends a Message to the console.
	 * @param message The Message to send.
	 */
	public void sendToConsole(Message message){
		plugin.getServer().getConsoleSender().sendMessage(message.toString());
	}
	
	/**
	 * Sends a MultiMessage to the console.
	 * @param message The MultiMessage to send.
	 */
	public void sendToConsole(MultiMessage message){
		for(String s : message.toStringList()){
			plugin.getServer().getConsoleSender().sendMessage(s);
		}
	}
	
	/**
	 * Logs a Message as info.
	 * @param message The Message to log.
	 */
	public void logAsInfo(Message message){
		plugin.getServer().getLogger().info(message.toString());
	}
	
	/**
	 * Logs a MultiMessage as info.
	 * @param message The MultiMessage to log.
	 */
	public void logAsInfo(MultiMessage message){
		for(String s : message.toStringList()){
			plugin.getServer().getLogger().info(s);
		}
	}
	
	/**
	 * Logs a Message as warning.
	 * @param message The Message to log.
	 */
	public void logAsWarning(Message message){
		plugin.getServer().getLogger().warning(message.toString());
	}
	
	/**
	 * Logs a MultiMessage as warning.
	 * @param message The MultiMessage to log.
	 */
	public void logAsWarning(MultiMessage message){
		for(String s : message.toStringList()){
			plugin.getServer().getLogger().warning(s);
		}
	}
	
	/**
	 * Logs a Message as severe.
	 * @param message The Message to log.
	 */
	public void logAsSevere(Message message){
		plugin.getServer().getLogger().severe(message.toString());
	}
	
	/**
	 * Logs a MultiMessage as severe.
	 * @param message The MultiMessage to log.
	 */
	public void logAsSevere(MultiMessage message){
		for(String s : message.toStringList()){
			plugin.getServer().getLogger().severe(s);
		}
	}
	
	/**
	 * Gets the Options instance for this handler instance.
	 * @return Returns the options variable.
	 */
	public Options options() {
		return options;
	}
	
	/**
	 * Retrieves a message from the config with assigned handler.
	 * @param key This is the yml key.
	 * @return Returns a new Message instance.
	 */
	public Message getConfigMessage(String key) {
		return new Message(config.getString(options.getKeyPrefix() + key));
	}
	
	/**
	 * Retrieves a multimessage from the config with assigned handler.
	 * @param key This is the yml key.
	 * @return Returns a new MultiMessage instance.
	 */
	public MultiMessage getConfigMultiMessage(String key) {
		return new MultiMessage(config.getStringList(options.getKeyPrefix() + key));
	}
	
	/**
	 * Identical to the getConfigMessage method except it has an option to not use the keyPrefix.
	 * @param key This is the yml key.
	 * @param useKeyPrefix Should the keyPrefix be used?
	 * @return Returns a new Message.
	 */
	public Message getConfigMessage(String key, boolean useKeyPrefix) {
		if(useKeyPrefix) return new Message(config.getString(options.getKeyPrefix() + key));
		else return new Message(config.getString(key));
	}
	
	/**
	 * Identical to the getConfigMultiMessage method except it has an option to not use the keyPrefix.
	 * @param key This is the yml key.
	 * @param useKeyPrefix Should the keyPrefix be used?
	 * @return Returns a new MultiMessage.
	 */
	public MultiMessage getConfigMultiMessage(String key, boolean useKeyPrefix) {
		if(useKeyPrefix) return new MultiMessage(config.getStringList(options.getKeyPrefix() + key));
		else return new MultiMessage(config.getStringList(key));
	}
	
	/**
	 * Inner Options class. Used to get/set handler options.
	 */
	public class Options{
		
		private String keyPrefix = "";
		
		/**
		 * Sets the keyPrefix.
		 * @param keyPrefix String which the keyPrefix will be set to.
		 */
		public void setKeyPrefix(String keyPrefix) {
			this.keyPrefix = keyPrefix;
		}
		
		/**
		 * Gets the keyPrefix. 
		 * @return Returns the keyPrefix.
		 */
		public String getKeyPrefix() {
			return keyPrefix;
		}
		
	}
}
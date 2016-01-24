/**
 * Copyright 2016 src-dev.net
 * All rights reserved.
 */

package net.src_dev.wgtreefarmflag.library.messageapi;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * The MessageHandler object class.
 * Class is abstract. Must be subclassed.
 * A subclass of this is used to create and control Messages and MultiMessages.
 */
public class MessageHandler {

	private FileConfiguration config;
	
	private Options options = new Options();
	
	/**
	 * Only constructor. This is used to set the plugin and config variables.
	 * @param plugin Should be an instance of JavaPlugin.
	 * @param config Can be any FileConfiguration instance. Used to get messages from config.
	 */
	public MessageHandler(FileConfiguration config) {
		this.config = config;		
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
		private String getKeyPrefix() {
			return keyPrefix;
		}
		
	}
}
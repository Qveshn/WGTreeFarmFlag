/**
 * Copyright 2016 src-dev.net
 * All rights reserved.
 */

package net.src_dev.wgtreefarmflag.library.messageapi;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The Message object class.
 * This class is for single-line messages.
 */
public class Message {
	
	protected String text;
	
	/**
	 * Creates a new Message with a starting text.
	 * @param text Starting text. Used to initialize and fill the text variable.
	 */
	public Message(String text) {
		this.text = text;
	}
	
	/**
	 * Creates a new Message from an existing Message. Essentially creates a copy.
	 * @param message The old message which is to be copied.
	 */
	protected Message(Message oldMessage) {
		this.text = oldMessage.text;
	}
	
	/**
	 * Sets the text of the Message. 
	 * @param text The text of the Message will be set to this.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message setText(String text) {
		this.text = text;
		return this;
	}
	
	/**
	 * Translates color codes in the text.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message color() {
		text = ChatColor.translateAlternateColorCodes('&', text);
		return this;
	}
	
	/**
	 * Translates color codes in the text using specified char.
	 * @param alternateColorCodeChar Char to be translated.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message color(char alternateColorCodeChar) {
		text = ChatColor.translateAlternateColorCodes(alternateColorCodeChar, text);
		return this;
	}
	
	/**
	 * Replaces placeholders using a Map<placeholder, replacement>.
	 * @param replacementMap Map which holds the placeholders and corresponding replacements.
	 * @return  Returns the Message instance (for in-line use).
	 */
	public Message replace(Map<String, String> replacementMap) {
		for(Entry<String, String> entry : replacementMap.entrySet()) {
			text = text.replace(entry.getKey(), entry.getValue());
		}
		return this;
	}
	
	/**
	 * Sends the message to a Sender.
	 * @param sender The Sender instance to have the message sent to.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message send(CommandSender sender) {
		sender.sendMessage(text);
		return this;
	}
	
	/**
	 * Sends the message to a Player.
	 * @param player The Player instance to have the message sent to.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message send(Player player) {
		player.sendMessage(text);
		return this;
	}
	
	/**
	 * Sends the message to the ConsoleSender.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message sendToConsole() {
		Bukkit.getServer().getConsoleSender().sendMessage(text);
		return this;
	}
	
	/**
	 * Logs the message as info using the statically referenced logger.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message logAsInfo() {
		Bukkit.getServer().getLogger().info(text);
		return this;
	}
	
	/**
	 * Logs the message as warning using the statically referenced logger.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message logAsWarning() {
		Bukkit.getServer().getLogger().warning(text);
		return this;
	}
	
	/**
	 * Logs the message as severe using the statically referenced logger.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message logAsSevere() {
		Bukkit.getServer().getLogger().severe(text);
		return this;
	}
	
	/**
	 * Appends a String to the text.
	 * @param suffix The String to be appended.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message append(String suffix) {
		text = text + suffix;
		return this;
	}
	
	/**
	 * Appends a Message to the text.
	 * @param suffix The Message to be appended.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message append(Message suffix) {
		text = text + suffix.toString();
		return this;
	}
	
	/**
	 * Prepends a String to the text.
	 * @param prefix The String to be prepended.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message prepend(String prefix) {
		text = prefix + text;
		return this;
	}
	
	/**
	 * Prepends a Message to the Text.
	 * @param prefix The Message to be prepended.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message prepend(Message prefix) {
		text = prefix.toString() + text;
		return this;
	}
	
	/**
	 * Returns a copy of the Message instance. This is a shortcut for new Message(oldMessage).
	 * @return Returns the new Message instance.
	 */
	public Message copy() {
		return new Message(this);
	}
	
	/**
	 * Strips all the color codes from the text.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message stripColor() {
		text = ChatColor.stripColor(text);
		return this;
	}
	
	/**
	 * Replaces a char with another.
	 * @param oldChar The char to be replaced.
	 * @param newChar The replacement.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message replace(char oldChar, char newChar) {
		text = text.replace(oldChar, newChar);
		return this;
	}
	
	/**
	 * Replaces a CharSequence with another.
	 * @param target The CharSequence to be replaced.
	 * @param replacement The replacement.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message replace(CharSequence target, CharSequence replacement) {
		text = text.replace(target, replacement);
		return this;
	}
	
	/**
	 * Replaces using a regex String.
	 * @param regex The regex String.
	 * @param replacement The replacement.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message replaceAll(String regex, String replacement) {
		text = text.replaceAll(regex, replacement);
		return this;
	}	
	
	/**
	 * Returns the String form of the Message (the text).
	 * @return Returns the text.
	 */
	public String toString() {
		return text;
	}
	
	/**
	 * Trims the text. Added for thoroughness.
	 * @return Returns the Message instance (for in-line use).
	 */
	public Message trim() {
		text = text.trim();
		return this;
	}
	
}

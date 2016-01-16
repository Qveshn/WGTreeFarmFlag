/**
 * Copyright 2016 src-dev.net
 * All rights reserved.
 */

package net.srv_dev.wgtreefarmflag.library;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The MultiMessage object class.
 * This class is for multi-line messages.
 */
public class MultiMessage {
	
	private List<String> text;
	
	/**
	 * Creates a new MultiMessage with a starting list.
	 * @param text Starting list. Used to initialize and fill the text variable.
	 */
	public MultiMessage(List<String> text) {
		setText(text);
	}
	
	/**
	 * Creates a new MultiMessage with an array.
	 * @param text Starting array. Converted to List to fill the text variable.
	 */
	public MultiMessage(String[] text){
		setText(text);
	}
	
	/**
	 * Creates a new MultiMessage from an existing MultiMessage. Essentially creates a copy.
	 * @param multiMessage The old multimessage which is to be copied.
	 */
	private MultiMessage(MultiMessage oldMessage) {
		setText(oldMessage.text);
	}
	
	/**
	 * Sets the list of the MultiMessage. 
	 * @param text The text of the MultiMessage will be set to this.
	 * @return Returns the MultiMessage instance (for in-line use).
	 */
	public MultiMessage setText(List<String> text) {
		this.text = text;
		return this;
	}
	
	/**
	 * Sets the list of the MultiMessage using an array.
	 * @param text The array which will be converted to a List.
	 * @return Returns the MultiMessage instance (for in-line use).
	 */
	public MultiMessage setText(String[] text) {
		this.text = Arrays.asList(text);
		return this;
	}
	
	/**
	 * Translates color codes in the text.
	 * @return Returns the MultiMessage instance (for in-line use).
	 */
	public MultiMessage color() {
		for(String t : text) {
			text.set(text.indexOf(t), ChatColor.translateAlternateColorCodes('&', t));
		}
		return this;
	}
	
	/**
	 * Translates color codes in the text using specified char.
	 * @param alternateColorCodeChar Char to be translated.
	 * @return Returns the MultiMessage instance (for in-line use).
	 */
	public MultiMessage color(char alternateColorCodeChar) {
		for(String t : text) {
			text.set(text.indexOf(t), ChatColor.translateAlternateColorCodes(alternateColorCodeChar, t));
		}
		return this;
	}
	
	/**
	 * Replaces placeholders using a Map<placeholder, replacement>.
	 * @param replacementMap Map which holds the placeholders and corresponding replacements.
	 * @return Returns the MultiMessage instance (for in-line use).
	 */
	public MultiMessage replace(Map<String, String> replacementMap) {
		for(String t : text) {
			int index = text.indexOf(t);
			for(Entry<String, String> entry : replacementMap.entrySet()) {
				t = t.replace(entry.getKey(), entry.getValue());
			}
			text.set(index, t);
		}
		return this;
	}
	
	/**
	 * Sends the multimessage to a Sender.
	 * @param sender The Sender instance to have the message sent to.
	 * @return Returns the MultiMessage instance (for in-line use).
	 */
	public MultiMessage send(CommandSender sender) {
		for(String t : text) {
			sender.sendMessage(t);
		}
		return this;
	}
	
	/**
	 * Sends the multimessage to a Player.
	 * @param player The Player instance to have the message sent to.
	 * @return
	 */
	public MultiMessage send(Player player) {
		for(String t : text) {
			player.sendMessage(t);
		}
		return this;
	}
	
	/**
	 * Appends a String to the text.
	 * @param suffix The String to be appended.
	 * @return Returns the MultiMessage instance (for in-line use).
	 */
	public MultiMessage append(String suffix) {
		for(String t : text) {
			text.set(text.indexOf(t), t + suffix);
		}
		return this;
	}
	
	/**
	 * Appends a MultiMessage to the text.
	 * @param suffix The MultiMessage to be appended.
	 * @return Returns the Message instance (for in-line use).
	 */
	public MultiMessage append(Message suffix) {
		for(String t : text) {
			text.set(text.indexOf(t), t + suffix.toString());
		}
		return this;
	}
	
	/**
	 * Prepends a String to the text.
	 * @param prefix The String to be prepended.
	 * @return Returns the Message instance (for in-line use).
	 */
	public MultiMessage prepend(String prefix) {
		for(String t : text) {
			text.set(text.indexOf(t), prefix + t);
		}
		return this;
	}
	
	/**
	 * Prepends a MultiMessage to the Text.
	 * @param prefix The MultiMessage to be prepended.
	 * @return Returns the Message instance (for in-line use).
	 */ 
	public MultiMessage prepend(Message prefix) {
		for(String t : text) {
			text.set(text.indexOf(t), prefix.toString() + t);
		}
		return this;
	}
	
	/**
	 * Returns a copy of the MultiMessage instance. This is a shortcut for new MultiMessage(oldMultiMessage).
	 * @return Returns the new MultiMessage instance.
	 */
	public MultiMessage copy() {
		return new MultiMessage(this);
	}
	
	/**
	 * Strips all the color codes from the text.
	 * @return Returns the Message instance (for in-line use).
	 */
	public MultiMessage stripColor() {
		for(String t : text) {
			text.set(text.indexOf(t), ChatColor.stripColor(t));
		}
		return this;
	}
	
	/**
	 * Replaces a char with another.
	 * @param oldChar The char to be replaced.
	 * @param newChar The replacement.
	 * @return Returns the Message instance (for in-line use).
	 */
	public MultiMessage replace(char oldChar, char newChar) {
		for(String t : text) {
			text.set(text.indexOf(t), t.replace(oldChar, newChar));
		}
		return this;
	}
	
	/**
	 * Replaces a CharSequence with another.
	 * @param target The CharSequence to be replaced.
	 * @param replacement The replacement.
	 * @return Returns the Message instance (for in-line use).
	 */
	public MultiMessage replace(CharSequence target, CharSequence replacement) {
		for(String t : text) {
			text.set(text.indexOf(t), t.replace(target, replacement));
		}
		return this;
	}
	
	/**
	 * Replaces using a regex String.
	 * @param regex The regex String.
	 * @param replacement The replacement.
	 * @return Returns the Message instance (for in-line use).
	 */
	public MultiMessage replaceAll(String regex, String replacement) {
		for(String t : text) {
			text.set(text.indexOf(t), t.replaceAll(regex, replacement));
		}
		return this;
	}
	
	/**
	 * Returns the List<String> form of the MultiMessage.
	 * @return Returns the text.
	 */
	public List<String> toStringList() {
		return text;
	}
	
	/**
	 * Returns the array form of the MultiMessage.
	 * @return Returns the text.
	 */
	public String[] toStringArray() {
		return (String[]) text.toArray();
	}
	
	/**
	 * Trims the text. Added for thoroughness.
	 * @return Returns the Message instance (for in-line use).
	 */
	public MultiMessage trim() {
		for(String t : text) {
			text.set(text.indexOf(t), t.trim());
		}
		return this;
	}
}
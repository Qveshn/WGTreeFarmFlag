package net.src_dev.srclibrary.messageapi;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {
	private String text;
	public Message(){
		setText("");
	}
	public Message(String text){
		setText(text);
	}
	public Message(Message message){
		setText(message.toString());
	}
		
	
	
	public Message setText(String text){
		this.text = text;
		return this;
	}
	public String getText(){
		return text;
	}
	public Message color(){
		text = ChatColor.translateAlternateColorCodes('&', text);
		return this;
	}
	public Message color(char alternateColorCodeChar){
		text = ChatColor.translateAlternateColorCodes(alternateColorCodeChar, text);
		return this;
	}
	public Message replace(Map<String, String> replacementMap){
		for(Entry<String, String> entry:replacementMap.entrySet()){
			text = text.replace(entry.getKey(), entry.getValue());
		}
		return this;
	}
	public void send(CommandSender sender){
		sender.sendMessage(text);
	}
	public void send(Player player){
		player.sendMessage(text);
	}
	
		
	
	public Message replace(char oldChar, char newChar){
		text = text.replace(oldChar, newChar);
		return this;
	}
	public Message replace(CharSequence target, CharSequence replacement){
		text = text.replace(target, replacement);
		return this;
	}
	public Message replaceAll(String regex, String replacement){
		text = text.replaceAll(regex, replacement);
		return this;
	}	
	public String toString(){
		return text;
	}
	public Message trim(){
		text = text.trim();
		return this;
	}
}

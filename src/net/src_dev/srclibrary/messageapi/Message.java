package net.src_dev.srclibrary.messageapi;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Message {
	private String text;
	private JavaPlugin plugin;
	public Message(String text){
		setText(text);
	}
	public Message(Message message){
		setPlugin(message.plugin);
		setText(message.text);
	}
	
	public Message setPlugin(JavaPlugin plugin){
		this.plugin = plugin;
		return this;
	}
	
	public Message setText(String text){
		this.text = text;
		return this;
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
	
	
	
	public Message send(CommandSender sender){
		sender.sendMessage(text);
		return this;
	}
	public Message send(Player player){
		player.sendMessage(text);
		return this;
	}
	public Message sendToConsole(){
		plugin.getServer().getConsoleSender().sendMessage(text);
		return this;
	}
	public Message logAsInfo(){
		plugin.getLogger().info(text);
		return this;
	}
	public Message logAsWarning(){
		plugin.getLogger().warning(text);
		return this;
	}
	
	
	
	public Message append(String suffix){
		text = text + suffix;
		return this;
	}
	public Message append(Message suffix){
		text = text + suffix.toString();
		return this;
	}
	public Message prepend(String prefix){
		text = prefix + text;
		return this;
	}
	public Message prepend(Message prefix){
		text = prefix.toString() + text;
		return this;
	}
	public Message copy(){
		return new Message(this);
	}
	public Message stripColor(){
		text = ChatColor.stripColor(text);
		return this;
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

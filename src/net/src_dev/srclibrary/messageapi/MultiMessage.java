package net.src_dev.srclibrary.messageapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MultiMessage {
	private List<String> text;
	public MultiMessage(){
		List<String> text = new ArrayList<String>();
		text.add("");
		setText(text);
	}
	public MultiMessage(List<String> text){
		setText(text);
	}
	public MultiMessage(MultiMessage multiMessage){
		setText(multiMessage.toStringList());
	}
	
	
	
	public MultiMessage setText(List<String> text){
		this.text = text;
		return this;
	}
	public List<String> getText(){
		return text;
	}
	public MultiMessage color(){
		for(String t:text){
			text.set(text.indexOf(t), ChatColor.translateAlternateColorCodes('&', t));
		}
		return this;
	}
	public MultiMessage color(char alternateColorCodeChar){
		for(String t:text){
			text.set(text.indexOf(t), ChatColor.translateAlternateColorCodes(alternateColorCodeChar, t));
		}
		return this;
	}
	public MultiMessage replace(Map<String, String> replacementMap){
		for(String t:text){
			int index = text.indexOf(t);
			for(Entry<String, String> entry:replacementMap.entrySet()){
				t = t.replace(entry.getKey(), entry.getValue());
			}
			text.set(index, t);
		}
		return this;
	}
	public void send(CommandSender sender){
		for(String t:text){
			sender.sendMessage(t);
		}
	}
	public void send(Player player){
		for(String t:text){
			player.sendMessage(t);
		}
	}
	
	
	
	public MultiMessage replace(char oldChar, char newChar){
		for(String t:text){
			text.set(text.indexOf(t), t.replace(oldChar, newChar));
		}
		return this;
	}
	public MultiMessage replace(CharSequence target, CharSequence replacement){
		for(String t:text){
			text.set(text.indexOf(t), t.replace(target, replacement));
		}
		return this;
	}
	public MultiMessage replaceAll(String regex, String replacement){
		for(String t:text){
			text.set(text.indexOf(t), t.replaceAll(regex, replacement));
		}
		return this;
	}
	public List<String> toStringList(){
		return text;
	}
	public MultiMessage trim(){
		for(String t:text){
			text.set(text.indexOf(t), t.trim());
		}
		return this;
	}
}

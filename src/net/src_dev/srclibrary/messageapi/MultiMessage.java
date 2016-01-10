package net.src_dev.srclibrary.messageapi;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MultiMessage {
	private List<String> text;
	private JavaPlugin plugin;
	public MultiMessage(List<String> text){
		setText(text);
	}
	public MultiMessage(MultiMessage multiMessage){
		setPlugin(multiMessage.plugin);
		setText(multiMessage.text);
	}
	
	public MultiMessage setPlugin(JavaPlugin plugin){
		this.plugin = plugin;
		return this;
	}
	
	public MultiMessage setText(List<String> text){
		this.text = text;
		return this;
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
	
	
	
	public MultiMessage send(CommandSender sender){
		for(String t:text){
			sender.sendMessage(t);
		}
		return this;
	}
	public MultiMessage send(Player player){
		for(String t:text){
			player.sendMessage(t);
		}
		return this;
	}
	public MultiMessage sendToConsole(){
		CommandSender console = plugin.getServer().getConsoleSender();
		for(String t:text){
			console.sendMessage(t);
		}
		return this;
	}
	public MultiMessage logAsInfo(){
		for(String t:text){
			plugin.getLogger().info(t);
		}
		return this;
	}
	public MultiMessage logAsWarning(){
		for(String t:text){
			plugin.getLogger().warning(t);
		}
		return this;
	}
	
	
	
	public MultiMessage append(String suffix){
		for(String t:text){
			text.set(text.indexOf(t), t + suffix);
		}
		return this;
	}
	public MultiMessage append(Message suffix){
		for(String t:text){
			text.set(text.indexOf(t), t + suffix.toString());
		}
		return this;
	}
	public MultiMessage prepend(String prefix){
		for(String t:text){
			text.set(text.indexOf(t), prefix + t);
		}
		return this;
	}
	public MultiMessage prepend(Message prefix){
		for(String t:text){
			text.set(text.indexOf(t), prefix.toString() + t);
		}
		return this;
	}
	public MultiMessage copy(){
		return new MultiMessage(this);
	}
	public MultiMessage stripColor(){
		for(String t:text){
			text.set(text.indexOf(t), ChatColor.stripColor(t));
		}
		return this;
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
	public String[] toStringArray(){
		return (String[]) text.toArray();
	}
	public MultiMessage trim(){
		for(String t:text){
			text.set(text.indexOf(t), t.trim());
		}
		return this;
	}
}

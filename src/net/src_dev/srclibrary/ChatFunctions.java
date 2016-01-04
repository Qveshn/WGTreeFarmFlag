package net.src_dev.srclibrary;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class ChatFunctions {
	private static char alternateColorCodeChar = '&';
	public static void setAlternateColorCodeChar(char alternateColorCodeChar){
		ChatFunctions.alternateColorCodeChar = alternateColorCodeChar;
	}
	public static char getAlternateColorCodeChar(){
		return alternateColorCodeChar;
	}
	public static void sendMessage(CommandSender sender, String message){
		sender.sendMessage(message);
	}
	public static void sendMessage(Player player, String message){
		player.sendMessage(message);
	}
	public static void sendColoredMessage(CommandSender sender, String message){
		sender.sendMessage(ChatColor.translateAlternateColorCodes(alternateColorCodeChar, message));
	}
	public static void sendColoredMessage(Player player, String message){
		player.sendMessage(ChatColor.translateAlternateColorCodes(alternateColorCodeChar, message));
	}
	public static void sendMessages(CommandSender sender, String[] messages){
		for(String m:messages){
			sender.sendMessage(m);
		}
	}
	public static void sendMessages(Player player, String[] messages){
		for(String m:messages){
			player.sendMessage(m);
		}
	}
	public static void sendColoredMessages(CommandSender sender, String[] messages){
		for(String m:messages){
			sender.sendMessage(ChatColor.translateAlternateColorCodes(alternateColorCodeChar, m));
		}
	}
	public static void sendColoredMessages(Player player, String[] messages){
		for(String m:messages){
			player.sendMessage(ChatColor.translateAlternateColorCodes(alternateColorCodeChar, m));
		}
	}
}

package net.src_dev.wgtreefarmflag.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldguard.protection.ApplicableRegionSet;

import net.src_dev.wgtreefarmflag.Messages;
import net.src_dev.wgtreefarmflag.WGTreeFarmFlag;

public class BlockListener implements Listener{
	private WGTreeFarmFlag plugin;
	public BlockListener(WGTreeFarmFlag plugin){
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreakEvent(BlockBreakEvent e){
		Block bl = e.getBlock();
		Location loc = bl.getLocation();
		World w = bl.getWorld();
		Material mat = bl.getType();
		ItemStack itemInHand;
		Player p = e.getPlayer();
		short dur;
		byte data;
		Block blUnder;
		ApplicableRegionSet regionSet = plugin.getWorldGuard().getRegionManager(w).getApplicableRegions(loc);
		if(regionSet.testState(null, WGTreeFarmFlag.TREE_FARM)){
			itemInHand = p.getItemInHand();
			if(mat == Material.LOG || mat == Material.LOG_2){
				data = bl.getData();
				if(mat == Material.LOG_2) data += 4;
				blUnder = bl.getRelative(BlockFace.DOWN);
				if((!(p instanceof NPC)) && (p.getGameMode() != GameMode.CREATIVE)){					
					if(itemInHand == null) bl.breakNaturally();
					else{
						bl.breakNaturally(itemInHand);
						dur = itemInHand.getDurability();
						if(dur > 0){
							dur++;
							itemInHand.setDurability(dur);
						}
					}
				}
				else{
					bl.setType(Material.AIR);
				}
				if(blUnder.getType() == Material.DIRT || blUnder.getType() == Material.GRASS){
					bl.setType(Material.SAPLING);
					bl.setData(data);
				}
			}
			else if(mat == Material.LEAVES || mat == Material.LEAVES_2){
				if((!(p instanceof NPC)) && (p.getGameMode() != GameMode.CREATIVE) && itemInHand.getType() == Material.SHEARS){
					bl.breakNaturally(itemInHand);
					dur = itemInHand.getDurability();
					dur++;
					itemInHand.setDurability(dur);
				}
				else{
					bl.setType(Material.AIR);
				}
			}
			else if(mat == Material.SAPLING){
				if(p.hasPermission("wgtreefarmflag.saplingbreak") && plugin.getConfig().getBoolean("allow-saplingbreak-with-perm")) return;
				Messages.cannotBreakSapling.send(p);
			}
			else return;
			e.setCancelled(true);
			return;
		}
		if(regionSet.testState(null, WGTreeFarmFlag.MUSHROOM_FARM)){
			itemInHand = p.getItemInHand();
			boolean isMushroom = false;
			Material mushroomType = null;
			if(mat == Material.HUGE_MUSHROOM_2){
				isMushroom = true;
				mushroomType = Material.HUGE_MUSHROOM_2;
			}
			else if(mat == Material.HUGE_MUSHROOM_1){
				isMushroom = true;
				mushroomType = Material.HUGE_MUSHROOM_1;
			}
			if(isMushroom){
				blUnder = bl.getRelative(BlockFace.DOWN);
				if((!(p instanceof NPC)) && (p.getGameMode() != GameMode.CREATIVE)){
					if(itemInHand == null) bl.breakNaturally();
					else{
						bl.breakNaturally(itemInHand);
						dur = itemInHand.getDurability();
						if(dur > 0){
							dur++;
							itemInHand.setDurability(dur);
						}
					}
				}
				else{
					bl.setType(Material.AIR);
				}
				if(blUnder.getType() == Material.DIRT){
					if(mushroomType == Material.HUGE_MUSHROOM_1){
						bl.setType(Material.BROWN_MUSHROOM);
					}
					else if(mushroomType == Material.HUGE_MUSHROOM_2){
						bl.setType(Material.RED_MUSHROOM);
					}
				}
			}
			else if(mat == Material.RED_MUSHROOM || mat == Material.BROWN_MUSHROOM){
				if(p.hasPermission("wgtreefarmflag.mushroombreak") && plugin.getConfig().getBoolean("allow-mushroombreak-with-perm")) return;
				Messages.cannotBreakMushroom.send(p);
			}
			else return;
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onLeavesDecayEvent(LeavesDecayEvent e){
		Block bl = e.getBlock();
		Location loc = bl.getLocation();
		World w = bl.getWorld();
		
		if(plugin.getWorldGuard().getRegionManager(w).getApplicableRegions(loc).testState(null, WGTreeFarmFlag.TREE_FARM)){
			bl.setType(Material.AIR);
			e.setCancelled(true);
		}
	}
}

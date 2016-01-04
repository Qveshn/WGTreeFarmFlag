package net.src_dev.wgtreefarmflag;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class MushroomIntervalGrower implements Runnable{
	@SuppressWarnings("unused")
	private WGTreeFarmFlag plugin;
	private int growthChance;
	private HashMap<ProtectedRegion, List<Block>> farmMushrooms;
	public MushroomIntervalGrower(WGTreeFarmFlag plugin, int growthChance, HashMap<ProtectedRegion, List<Block>> farmMushrooms){
		this.plugin = plugin;
		this.growthChance = growthChance;
		this.farmMushrooms = farmMushrooms;
	}
	
	@Override
	public void run(){
		Random random = new Random();
		for(Entry<ProtectedRegion, List<Block>> entry:farmMushrooms.entrySet()){
			for(Block b:entry.getValue()){
				Location loc = b.getLocation();
				World w = loc.getWorld();
				if(w.getBlockAt(loc).getType() == Material.RED_MUSHROOM || w.getBlockAt(loc).getType() == Material.BROWN_MUSHROOM){
					Material type = b.getType();
					TreeType treeType = null;
					if(type == Material.BROWN_MUSHROOM) treeType = TreeType.BROWN_MUSHROOM;
					else if(type == Material.RED_MUSHROOM) treeType = TreeType.RED_MUSHROOM;
					int r = random.nextInt(99) + 1;
					boolean grew;
					if(r <= growthChance){
						b.setType(Material.AIR);
						grew = w.generateTree(loc, treeType);	
						if(!grew){
							b.setType(type);
						}
						
					}
				}
			}
		}
	}
}

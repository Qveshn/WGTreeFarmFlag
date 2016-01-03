package net.src_dev.wgtreefarmflag;

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
	private WGTreeFarmFlag plugin;
	private int growthChance;
	public MushroomIntervalGrower(WGTreeFarmFlag plugin, int growthChance){
		this.plugin = plugin;
		this.growthChance = growthChance;
	}
	
	@Override
	public void run(){
		plugin.logDebug("Running mushroom grower task.", 1);
		Random random = new Random();
		plugin.logDebug("Starting iteration of Entry<ProtectedRegion, List<Block>>.", 4);
		for(Entry<ProtectedRegion, List<Block>> entry:plugin.farmMushrooms.entrySet()){
			plugin.logDebug("Starting iteration of List<Block>.", 4);
			for(Block b:entry.getValue()){
				Location loc = b.getLocation();
				Material type = b.getType();
				World w = loc.getWorld();
				TreeType treeType = null;
				if(type == Material.BROWN_MUSHROOM) treeType = TreeType.BROWN_MUSHROOM;
				else if(type == Material.RED_MUSHROOM) treeType = TreeType.RED_MUSHROOM;
				int r = random.nextInt(99) + 1;
				plugin.logDebug("Determining whether to grow tree. The chance is " + growthChance + "%, and the random is " + r + ".", 4);
				boolean grew;
				if(r <= growthChance){
					plugin.logDebug("Trying to grow tree..", 4);
					grew = w.generateTree(loc, treeType);	
					if(!grew) plugin.logDebug("Tree growth failed. Unknown cause.", 4);
				}
			}
		}
		plugin.logDebug("Mushroom grower task completed.", 2);
	}
}

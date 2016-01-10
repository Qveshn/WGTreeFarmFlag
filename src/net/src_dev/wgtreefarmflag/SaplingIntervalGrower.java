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

import net.src_dev.srclibrary.functions.TreeFunctions;

public class SaplingIntervalGrower implements Runnable{
	private WGTreeFarmFlag plugin;
	private int growthChance;
	public SaplingIntervalGrower(WGTreeFarmFlag plugin, int growthChance){
		this.plugin = plugin;
		this.growthChance = growthChance;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run(){
		Random random = new Random();
		for(Entry<ProtectedRegion, List<Block>> entry:plugin.getFarmSaplings().entrySet()){
			for(Block b:entry.getValue()){
				Location loc = b.getLocation();
				World w = loc.getWorld();
				Material type = b.getType();
				byte data = b.getData();
				TreeType treeType = TreeFunctions.getTreeTypeFromSapling(b);
				int r = random.nextInt(99) + 1;
				boolean grew;
				if(r <= growthChance){
					b.setType(Material.AIR);
					grew = w.generateTree(loc, treeType);
					if(!grew){
						b.setType(type);
						b.setData(data);
					}
				}
			}
		}
	}
}

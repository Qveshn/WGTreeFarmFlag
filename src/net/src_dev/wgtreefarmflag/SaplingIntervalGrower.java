package net.src_dev.wgtreefarmflag;

import java.util.HashMap;
import java.util.List;

import org.bukkit.block.Block;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class SaplingIntervalGrower implements Runnable{
	@SuppressWarnings("unused")
	private WGTreeFarmFlag plugin;
	@SuppressWarnings("unused")
	private int growthChance;
	@SuppressWarnings("unused")
	private HashMap<ProtectedRegion, List<Block>> farmSaplings;
	public SaplingIntervalGrower(WGTreeFarmFlag plugin, int growthChance, HashMap<ProtectedRegion, List<Block>> farmSaplings){
		this.plugin = plugin;
		this.growthChance = growthChance;
		this.farmSaplings = farmSaplings;
	}
	
	@Override
	public void run(){
		
	}
}

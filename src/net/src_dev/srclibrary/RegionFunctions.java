package net.src_dev.srclibrary;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.block.Block;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class RegionFunctions {
	public static List<Block> getBlocksInRegion(World world, ProtectedRegion region){
		List<Integer> coords;
		List<Block> blocks;
		Block block;
		coords = new ArrayList<Integer>();
		coords.add(region.getMinimumPoint().getBlockX());
		coords.add(region.getMinimumPoint().getBlockY());
		coords.add(region.getMinimumPoint().getBlockZ());
		coords.add(region.getMaximumPoint().getBlockX());
		coords.add(region.getMaximumPoint().getBlockY());
		coords.add(region.getMaximumPoint().getBlockZ());
		blocks = new ArrayList<Block>();
		for (int x = Math.min(coords.get(0), coords.get(3)); x <= Math.max(coords.get(0), coords.get(3)); x++) {
            for (int y = Math.min(coords.get(1), coords.get(4)); y <= Math.max(coords.get(1), coords.get(4)); y++) {
                for (int z = Math.min(coords.get(2), coords.get(5)); z <= Math.max(coords.get(2), coords.get(5)); z++) {
                    block = world.getBlockAt(x, y, z);
                    blocks.add(block);
                }
            }
        }
		return blocks;
	}
}

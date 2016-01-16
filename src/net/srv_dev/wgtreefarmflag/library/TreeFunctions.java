package net.srv_dev.wgtreefarmflag.library;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;

public class TreeFunctions {
	@SuppressWarnings("deprecation")
	public static TreeType getTreeTypeFromSapling(Block sapling){
		if(sapling.getType() != Material.SAPLING){
			return null;
		}
		byte data = sapling.getData();
		TreeType treeType;
		switch(data){
			case 0: treeType = TreeType.TREE;
					break;
			case 1: treeType = TreeType.REDWOOD;
					break;
			case 2: treeType = TreeType.BIRCH;
					break;
			case 3: treeType = TreeType.SMALL_JUNGLE;
					break;
			case 4: treeType = TreeType.ACACIA;
					break;
			case 5: treeType = TreeType.DARK_OAK;	
					break;
			default: treeType = TreeType.TREE;
					break;
		}
		return treeType;
	}
}

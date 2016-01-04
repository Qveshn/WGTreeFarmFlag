package net.src_dev.wgtreefarmflag;

public class SaplingIntervalGrower implements Runnable{
	@SuppressWarnings("unused")
	private WGTreeFarmFlag plugin;
	@SuppressWarnings("unused")
	private int growthChance;
	public SaplingIntervalGrower(WGTreeFarmFlag plugin, int growthChance){
		this.plugin = plugin;
		this.growthChance = growthChance;
	}
	
	@Override
	public void run(){
		
	}
}

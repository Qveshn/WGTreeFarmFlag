package net.src_dev.srclibrary.functions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFunctions {
	public static List<File> getYmlFiles(File folder){
	    List<File> ymlFiles = new ArrayList<File>();
	    for(File f:folder.listFiles()){
	        String[] splitName = f.getName().split(".");
	        if(splitName.length != 2) continue;
	        if(!(splitName[1].equalsIgnoreCase("yml") || splitName[1].equalsIgnoreCase("yaml"))) continue;
	        ymlFiles.add(f);
	    }
	    return ymlFiles;
	}
}

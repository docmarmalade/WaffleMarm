package com.marmalade.wafflemarm;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.marmalade.wafflemarm.WaffleSpyCommand;

public class WaffleMarm extends JavaPlugin {

	static WaffleMarm plugin;
	public final Logger log = Logger.getLogger("Minecraft");
	PlayerListener playerListener = new PlayerListener(this);  
	BlockListener blockListener = new BlockListener(this);
    WaffleSpyCommand waffleSpyCommand = new WaffleSpyCommand(this);

	public WaffleMarm() {
		plugin = this;
	}
	
	File waffleInfo;
	FileConfiguration fileConfig = new YamlConfiguration();

	 public void loadYamls() {
	        try {
	            fileConfig.load(waffleInfo); 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void saveYamls() {
	        try {
	            fileConfig.save(waffleInfo); //saves the FileConfiguration to its File
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	@Override
	public void onEnable(){
		log.info("It's Alive!!");
		PluginManager pm = getServer().getPluginManager();
		
		if (Config.loadSettings()) {
			getCommand("hi").setExecutor(new Commands(this));    //registers command executors
			getCommand("jump").setExecutor(new Commands(this));
		    System.out.print("WaffleMarm Plugin Enabled!");
		} 
		loadYamls();
		saveYamls();
		saveConfig();

		pm.registerEvents(playerListener, this);       //registers eventlisteners
		pm.registerEvents(blockListener, this);
		pm.registerEvents(waffleSpyCommand,this);
	}

	public static Set<String> oldChunks = new HashSet<String>();   //2 hashsets for storing coord sets of chunks
	public static Set<String> newChunks = new HashSet<String>();
}






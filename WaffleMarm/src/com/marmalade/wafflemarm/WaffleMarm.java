package com.marmalade.wafflemarm;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WaffleMarm extends JavaPlugin {

	static WaffleMarm plugin;
	public final Logger log = Logger.getLogger("Minecraft");
	PlayerListener playerListener = new PlayerListener(this);  
	BlockListener blockListener = new BlockListener(this);

	public WaffleMarm() {
		plugin = this;
	}

   
	@Override
	public void onEnable(){
		getLogger().info("It's Alive!!");
		PluginManager pm = getServer().getPluginManager();
		
		this.getConfig();
		saveConfig();
		
        System.out.print("WaffleMarm Plugin Enabled!");
        
		pm.registerEvents(playerListener, this);       //registers eventlisteners
		pm.registerEvents(blockListener, this);
       
        
		getCommand("hi").setExecutor(new Commands(this));    //registers command executors
		getCommand("jump").setExecutor(new Commands(this));

	}

	public static Set<String> oldChunks = new HashSet<String>();   //2 hashsets for storing coord sets of chunks
	public static Set<String> newChunks = new HashSet<String>();
}






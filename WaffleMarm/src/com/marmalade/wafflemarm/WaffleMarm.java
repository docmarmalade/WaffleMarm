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
    WaffleSpyEvent waffleSpyEvent = new WaffleSpyEvent(this);

	public WaffleMarm() {
		plugin = this;
	}
 
	@Override
	public void onEnable(){
		log.info("It's Alive!!");
		PluginManager pm = getServer().getPluginManager();

		if (Config.loadSettings()) {
			getCommand("hi").setExecutor(new Commands(this));    //registers command executors
			getCommand("jump").setExecutor(new Commands(this));
			getCommand("seen").setExecutor(new WaffleSpyCommand(this));
		    System.out.print("WaffleMarm Plugin Enabled!");
		} 
		saveConfig();

		pm.registerEvents(playerListener, this);       //registers eventlisteners
		pm.registerEvents(blockListener, this);
		pm.registerEvents(waffleSpyEvent,this);
	}

	public static Set<String> oldChunks = new HashSet<String>();   //2 hashsets for storing coord sets of chunks
	public static Set<String> newChunks = new HashSet<String>();
}






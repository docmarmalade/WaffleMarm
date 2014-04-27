package com.marmalade.wafflemarm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
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

    private static YamlConfiguration myConfig;
    private static File configFile;
    private static boolean loaded = false;
 
    public YamlConfiguration getConfig() {
        if (!loaded) {
            loadConfig();
        }
        return myConfig;
    }
 
    public static File getConfigFile() {
        return configFile;
    }
 
    public static void loadConfig() {
        configFile = new File(Bukkit.getServer().getPluginManager().getPlugin("WaffleMarm").getDataFolder(), "config.yml");
        if (configFile.exists()) {
            myConfig = new YamlConfiguration();
            try {
                myConfig.load(configFile);
            } catch (FileNotFoundException e) {
            	e.printStackTrace();
            } catch (IOException e) {
            	e.printStackTrace();
            } catch (InvalidConfigurationException e) {
            	e.printStackTrace();
            }
            loaded = true;
        }
    }

	@Override
	public void onEnable(){
		getLogger().info("It's Alive!!");
		PluginManager pm = getServer().getPluginManager();
		
        loadConfig();
        System.out.print("WaffleMarm Plugin Enabled!");
        this.reloadConfig();
        
		pm.registerEvents(playerListener, this);       //registers eventlisteners
		pm.registerEvents(blockListener, this);

		getCommand("hi").setExecutor(new Commands(this));    //registers command executors
		getCommand("jump").setExecutor(new Commands(this));

	}

	public static Set<String> oldChunks = new HashSet<String>();   //2 hashsets for storing coord sets of chunks
	public static Set<String> newChunks = new HashSet<String>();
}






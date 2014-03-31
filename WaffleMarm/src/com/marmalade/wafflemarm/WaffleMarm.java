package com.marmalade.wafflemarm;

import java.util.logging.Logger;
import com.marmalade.wafflemarm.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class WaffleMarm extends JavaPlugin {

	public static WaffleMarm plugin;
	public final Logger log = Logger.getLogger("Minecraft");
	private final PlayerListener playerListener = new PlayerListener(this);
	private final BlockListener blockListener = new BlockListener(this);
	private final ChunkListener chunkListener = new ChunkListener(this);
	
	public WaffleMarm() {
		plugin = this;
	}

	@Override
	public void onEnable(){
		getLogger().info("It's Alive!!");
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(playerListener, this);
		pm.registerEvents(blockListener, this);
		pm.registerEvents(chunkListener, this);
		
		getCommand("hi").setExecutor(new Commands(this));
		getCommand("jump").setExecutor(new Commands(this));
		getCommand("desert").setExecutor(new Commands(this));
		
	}
}




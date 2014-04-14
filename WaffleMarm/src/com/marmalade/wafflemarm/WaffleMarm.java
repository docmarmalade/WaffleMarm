package com.marmalade.wafflemarm;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import com.marmalade.wafflemarm.PlayerListener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class WaffleMarm extends JavaPlugin {

	public static WaffleMarm plugin;
	public final Logger log = Logger.getLogger("Minecraft");
	private final PlayerListener playerListener = new PlayerListener(this);  
	private final BlockListener blockListener = new BlockListener(this);

	public WaffleMarm() {
		plugin = this;
	}

	@Override
	public void onEnable(){
		getLogger().info("It's Alive!!");
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(playerListener, this);       //registers eventlisteners
		pm.registerEvents(blockListener, this);

		getCommand("hi").setExecutor(new Commands(this));    //registers command executors
		getCommand("jump").setExecutor(new Commands(this));

	}

	private static final String worldName = new String("world");
	public static final World world = plugin.getServer().getWorld(worldName);
	private static final int count = 0;
	public static final Chunk chunkCoords = world.getChunkAt(block);  //get chunk from block from loaded chunks
	public static final int chunkx = chunkCoords.getX();
	public static final int chunkz = chunkCoords.getZ();
	public static String coordSet = (chunkx + "," + chunkz);    //store set of coords as string
	public static final Set<String> oldChunks = new HashSet<String>();   //2 hashsets for storing coord sets of chunks
	public static final Set<String> newChunks = new HashSet<String>();
	newChunks.add(coordSet);
	count++;
	oldChunks.add(coordSet);
	
	
}
}




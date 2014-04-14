package com.marmalade.wafflemarm;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.marmalade.wafflemarm.WaffleMarm;

public final class PlayerListener implements Listener {

	final WaffleMarm plugin;

	public PlayerListener(WaffleMarm instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

		Player player = event.getPlayer();

		if (event.getMessage().startsWith("/bye")){
			player.sendMessage("Goodbye");
			event.setCancelled(true);
		}
		
		String worldName = new String("world");
		World world = plugin.getServer().getWorld(worldName);
		int count = 0;
		for (Chunk chunk : player.getWorld().getLoadedChunks()) {
			for (int x = 0; x < 16; x = x + 1) {
				for (int z = 0; z < 16; z = z + 1) {
					Block block = chunk.getBlock(x, 0, z);
					block.setBiome(Biome.DESERT);
					Chunk chunkCoords = world.getChunkAt(block);
					int chunkx = chunkCoords.getX();
					int chunkz = chunkCoords.getZ();
					String coordSet = (chunkx + "," + chunkz);
					Set<String> oldChunks = new HashSet<String>();
					Set<String> newChunks = new HashSet<String>();
					if(player.isOp()){
						if (event.getMessage().startsWith("/desert changed")) {
							newChunks.add(coordSet);
							count++;
							newChunks.removeAll(oldChunks);
							player.sendMessage("New Chunks Processed: " + (count/65536));
							event.setCancelled(true);
						}

						if (event.getMessage().startsWith("/desert start")) {
							player.sendMessage("This biome is now a desert. Good Luck.");
							oldChunks.add(coordSet);
							oldChunks.removeAll(newChunks);
							player.sendMessage("Chunk Coordinates Enhanced: " + oldChunks);
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}
}
















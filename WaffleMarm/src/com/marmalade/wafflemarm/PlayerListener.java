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
			for (int x = 0; x < 16; x = x + 1) {                 //iterating through chunks
				for (int z = 0; z < 16; z = z + 1) {             //and setting its biome via blocks
					Block block = chunk.getBlock(x, 0, z);   
					block.setBiome(Biome.DESERT);       
					Chunk chunkCoords = world.getChunkAt(block);  //get chunk from block from loaded chunks
					int chunkx = chunkCoords.getX();
					int chunkz = chunkCoords.getZ();
					String coordSet = (chunkx + "," + chunkz);    //store set of coords as string
					Set<String> oldChunks = new HashSet<String>();   //2 hashsets for storing coord sets of chunks
					Set<String> newChunks = new HashSet<String>();
					if(player.isOp()){
						if (event.getMessage().startsWith("/desert changed")) {
							newChunks.add(coordSet);
							count++;
							newChunks.removeAll(oldChunks);       //making sure there are no duplicates
							player.sendMessage("New Chunks Processed: " + (count/65536));   //(16*16*256)blocks in a chunk =65536 blocks,
							event.setCancelled(true);                                       //count uses for-loop that iterates through blocks
						}                                                                   //so this translates block count into chunk count

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
















package com.marmalade.wafflemarm;

import org.bukkit.Chunk;
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
	public static void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

		Player player = event.getPlayer();

		if (event.getMessage().startsWith("/bye")){
			player.sendMessage("Goodbye");
			event.setCancelled(true);
		}

		for (Chunk chunk : player.getWorld().getLoadedChunks()) {
			for (int x = 0; x < 16; x = x + 1) {                 //iterating through chunks
				for (int z = 0; z < 16; z = z + 1) {             //and setting its biome via blocks
					Block block = chunk.getBlock(x, 0, z);   
					block.setBiome(Biome.DESERT);  
				}
			}
		}
		
		if(player.isOp()){
			if (event.getMessage().startsWith("/desert changed")) {

				if(!(newChunks.contains(oldChunks))){    //making sure there are no duplicates
					player.sendMessage("New Chunks Processed: " + (count/65536));   //(16*16*256)blocks in a chunk =65536 blocks, count uses for-loop that iterates through block so this translates block count into chunk count 
				}
				event.setCancelled(true);
			}                                                                  
			if (event.getMessage().startsWith("/desert start")) {
				player.sendMessage("This biome is now a desert. Good Luck.");

				if(!(coordSet.contains(coordSet))){
					player.sendMessage("Chunk Coordinates Enhanced: " + oldChunks);
				}
				event.setCancelled(true);
			}
		}
	}
}













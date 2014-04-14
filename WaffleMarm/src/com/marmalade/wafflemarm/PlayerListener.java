package com.marmalade.wafflemarm;

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

public class PlayerListener implements Listener {

	WaffleMarm plugin;

	PlayerListener(WaffleMarm instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

		String worldName = "world";
		World world = plugin.getServer().getWorld(worldName);
		Block block = null; 
		Player player = event.getPlayer();
		int count = 0;

		if (event.getMessage().startsWith("/bye")){
			player.sendMessage("Goodbye");
			event.setCancelled(true);
		}
		for(Chunk chunk : player.getWorld().getLoadedChunks()){
			for(int x = 0; x < 16; x++) {                 //iterating through chunks
				for(int z = 0; z < 16; z++) {  
					block = chunk.getBlock(x,0,z);        //setting its biome via blocks
					block.setBiome(Biome.DESERT);  
				}
			}
		}
		
		Chunk chunkCoords = world.getChunkAt(block);  //get chunk from block from loaded chunks
		int chunkx = chunkCoords.getX();
		int chunkz = chunkCoords.getZ();
		String coordSet = (chunkx + "," + chunkz);    //store set of coords as string
		WaffleMarm.newChunks.add(coordSet);
		count++;
		WaffleMarm.oldChunks.add(coordSet);
		
		if(player.isOp()){
			if (event.getMessage().startsWith("/desert changed")) {

				if(!(WaffleMarm.newChunks.contains(WaffleMarm.oldChunks))){    //making sure there are no duplicates
					player.sendMessage("New Chunks Processed: " + (count/65536));   //(16*16*256)blocks in a chunk =65536 blocks, count uses for-loop that iterates through block so this translates block count into chunk count 
				}
				event.setCancelled(true);
			}                                                                  
			if (event.getMessage().startsWith("/desert start")) {
				player.sendMessage("This biome is now a desert. Good Luck.");
				if(!(coordSet.contains(coordSet))){
					player.sendMessage("Chunk Coordinates Enhanced: " + WaffleMarm.oldChunks);
				}
				event.setCancelled(true);
			}
		}
	}
}














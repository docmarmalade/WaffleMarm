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

public class PlayerListener implements Listener {

	WaffleMarm plugin;

	PlayerListener(WaffleMarm instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

		String worldName = "world";
		World world = plugin.getServer().getWorld(worldName);
		Player player = event.getPlayer();
		int count = 0;

		if (event.getMessage().startsWith("/bye")){
			player.sendMessage("Goodbye");
			event.setCancelled(true);
			return;
		}

		for(Chunk chunk : player.getWorld().getLoadedChunks()){
			for(int x = 0; x < 16; x++) {                 //iterating through chunks
				for(int z = 0; z < 16; z++) {  
					Block block = chunk.getBlock(x,0,z);        //setting its biome via blocks
					block.setBiome(Biome.DESERT);  
					Chunk chunkCoords = world.getChunkAt(block);  //get chunk from processed block
					int chunkx = chunkCoords.getX();              //get chunk location of said chunk
					int chunkz = chunkCoords.getZ();
					String cx = new Integer(chunkx).toString();
					String cz = new Integer(chunkz).toString();
					String coordSet = (cx + "," + cz);    //store set of coords as string
					if(WaffleMarm.oldChunks.isEmpty()){
						if(coordSet.equals(coordSet)){
							String cxz = coordSet.replaceAll((cx + "," + cz), coordSet);
							WaffleMarm.oldChunks.add(cxz);	
							WaffleMarm.newChunks.add(cxz);
							count++;
						}
					}
				}
			}

			if(player.isOp()){
				if (event.getMessage().startsWith("/desert start")) {
					event.setCancelled(true);
					player.sendMessage("Chunk Coordinates Enhanced: " + WaffleMarm.oldChunks);	
					WaffleMarm.oldChunks.clear();
				}
			}

			if (event.getMessage().startsWith("/desert change")) {
				event.setCancelled(true);
				if(!(WaffleMarm.newChunks.contains(WaffleMarm.oldChunks))){
					player.sendMessage("New Chunks Processed: " + (count));   //(16*16*256)blocks in a chunk =65536 blocks, count uses for-loop that iterates through block so this translates block count into chunk count
				}
			}
		}
	}
}
















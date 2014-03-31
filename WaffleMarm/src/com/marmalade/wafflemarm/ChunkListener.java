package com.marmalade.wafflemarm;

import org.bukkit.Chunk;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChunkListener implements Listener{

	final WaffleMarm plugin;

	public ChunkListener(WaffleMarm instance) {
		plugin = instance;
	}
	public void replaceChunkBiome(Chunk chunk, Biome oldBiome, Biome newBiome) {
		if(chunk.isLoaded()){
			for(int x = 0 ; x < 16; x++) {
				for(int z = 0 ; z < 16; z++) {
					if(chunk.getBlock(x, 0, z).getBiome() == oldBiome) {
						final Block block = chunk.getBlock(x, 0, z);
						block.setBiome(newBiome);
					}
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("biome.desert")){
			if (event.getMessage().startsWith("/desert")){
                 
			}
		}
	}
}





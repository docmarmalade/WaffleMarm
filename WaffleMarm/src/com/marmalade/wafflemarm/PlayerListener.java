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
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

		Player player = event.getPlayer();

		if (event.getMessage().startsWith("/bye")){
			player.sendMessage("Goodbye");
			event.setCancelled(true);
		}

		if(player.isOp()){
			if (event.getMessage().startsWith("/desert")) {
				player.sendMessage("I shall bring you the desert..");
				int count = 0;
				for (Chunk chunk : player.getWorld().getLoadedChunks()) {
					for (int x = 0; x < 16; x = x + 1) {
						for (int z = 0; z < 16; z = z + 1) {
							Block block = chunk.getBlock(x, 0, z);
							block.setBiome(Biome.DESERT);
						}
					}
					count++;
				} 
				player.sendMessage("Chunks Enhanced: " + count);
				event.setCancelled(true);
			}
		}
	}
}









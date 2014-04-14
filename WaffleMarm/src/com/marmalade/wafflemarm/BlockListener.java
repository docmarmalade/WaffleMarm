package com.marmalade.wafflemarm;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

	public final class BlockListener implements Listener {

		final WaffleMarm plugin;
		
		public BlockListener(WaffleMarm instance) {
			plugin = instance;
		}

		@EventHandler(priority = EventPriority.NORMAL)
		public void onBlockPlace(BlockPlaceEvent event) {
			
			Player player = event.getPlayer();
			
			event.setCancelled(true);
				player.setHealth(0.0);   //kills player when block is placed
				player.sendMessage("Hahaha you fool."); 
			}

		}
	




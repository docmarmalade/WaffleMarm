package com.marmalade.wafflemarm;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

	WaffleMarm plugin;

	BlockListener(WaffleMarm instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPlace(BlockPlaceEvent event) {

		Player player = event.getPlayer();

		event.setCancelled(true);
		player.setHealth(0.0);   //kills player when block is placed
		player.sendMessage("Hahaha you fool."); 
	}
}





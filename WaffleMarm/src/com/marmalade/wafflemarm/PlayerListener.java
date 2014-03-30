package com.marmalade.wafflemarm;

import java.util.logging.Logger;

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
		final Logger log = Logger.getLogger("Minecraft");
		Player player = event.getPlayer();
		
		if (event.getMessage().startsWith("/bye")){
			player.sendMessage("Goodbye");
			event.setCancelled(true);
		}

	}
}









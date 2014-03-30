package com.marmalade.wafflemarm;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.marmalade.wafflemarm.WaffleMarm;

public final class PlayerListener implements Listener {

	final WaffleMarm plugin;
	
	public PlayerListener(WaffleMarm instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerChat(AsyncPlayerChatEvent event) {

		Player player = event.getPlayer();
		
		if (event.getMessage().startsWith("/bye")){
			player.sendMessage("Goodbye");
		}

	}
}









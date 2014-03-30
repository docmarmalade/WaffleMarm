package com.marmalade.wafflemarm;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class ChatListener implements Listener {

	final WaffleMarm plugin;
	
	public ChatListener(WaffleMarm plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerChat(AsyncPlayerChatEvent event) {

		Player player = event.getPlayer();
		
		if (event.getMessage().startsWith("/bye")){
			if(event.isCancelled()) return;
			player.sendMessage("Goodbye");
		}

	}
}









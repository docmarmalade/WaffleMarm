package com.marmalade.wafflemarm;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener{
	
	public WaffleMarm plugin;
	
	public JoinListener(WaffleMarm plugin){
		this.plugin = plugin;
	}
@EventHandler
public void onPlayerJoin(PlayerJoinEvent event){
	Player player = event.getPlayer();
	
	plugin.chat.put(player.getName(), ("default"));
}

}

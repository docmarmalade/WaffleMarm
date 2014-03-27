package com.marmalade.wafflemarm;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
	
	public WaffleMarm plugin;
	
	public ChatListener(WaffleMarm plugin){
		this.plugin = plugin;
	}
		
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		Player player = event.getPlayer();
		
		if(player.getName().equalsIgnoreCase("dr_muffinbutter")){
		
			event.setFormat(ChatColor.GOLD + "[" + ChatColor.GREEN + "Developer" + ChatColor.GOLD + "]" 
			         + ChatColor.RED + player.getName() + " " + ChatColor.YELLOW+ event.getMessage());
		}
		else if (player.isOp()){
			event.setFormat(ChatColor.GOLD + "[" + ChatColor.BLUE + "Op" + ChatColor.GOLD + "]"
					 + ChatColor.WHITE + player.getName() + " " + ChatColor.AQUA + event.getMessage());
			
		}
		else {
			event.setFormat(ChatColor.GRAY + "[Player]" + ChatColor.RESET + player.getName() + " " + ChatColor.AQUA + event.getMessage());
					
					}
		
		String chat = plugin.chat.get(player.getName());
		
	    for (Player p : Bukkit.getServer().getOnlinePlayers()){
	    	
			if (plugin.chat.get(p.getName()).equals(chat)){
	        	 
	        	 p.sendMessage(chat + event.getFormat());
	    		
	    		
	    	}
	    }
    }
}

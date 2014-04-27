package com.marmalade.wafflemarm;

import java.net.InetAddress;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class WaffleSpy extends Event{

	WaffleMarm plugin;

	WaffleSpy(WaffleMarm instance) {
		plugin = instance;
	}

	@EventHandler
	public void AsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent e){
		InetAddress ip = e.getAddress();
		String iP = ip.getHostName();
		plugin.getConfig().set("players.info.ipAddress", iP);
		String playerName = e.getName();	
		plugin.getConfig().set("players.info.playerName", playerName);
		
	}

	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		String commandName = command.getName().toLowerCase();
		String[] trimmedArgs = args;
		if (commandName.equalsIgnoreCase("seen " + trimmedArgs[0])) {
			if (trimmedArgs.length == 1) {
				sender.sendMessage(ChatColor.GOLD + "Player information for " + ChatColor.GREEN + trimmedArgs[0]);
				
                OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(trimmedArgs[0]);
                long fp = offlinePlayer.getFirstPlayed();
                plugin.getConfig().set("players.info.firstLogin", fp);
                long lp = offlinePlayer.getLastPlayed();
                plugin.getConfig().set("players.info.lastLogout", lp);
            
			}
		}
		return true;
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}
}

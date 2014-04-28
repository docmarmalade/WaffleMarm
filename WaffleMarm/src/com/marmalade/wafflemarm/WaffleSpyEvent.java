package com.marmalade.wafflemarm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class WaffleSpyEvent implements Listener{
	
	WaffleMarm plugin;

	WaffleSpyEvent(WaffleMarm instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void AsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent e){
		InetAddress ip = e.getAddress();
		String iP = ip.getHostName();
		String playerName = e.getName();
		OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(playerName);
		long fp = offlinePlayer.getFirstPlayed();
		long lp = offlinePlayer.getLastPlayed();
		File waffleInfo = new File("players.yml");
		try {
			waffleInfo.createNewFile();
			System.out.println("checkpoint a");
		} catch (IOException err) {
			err.printStackTrace();
		}
		if(waffleInfo.exists()){
			System.out.println("checkpoint b");
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(waffleInfo));
				writer.write("Username: " + playerName + ", " + "IP: " + iP);
				writer.newLine();
				writer.write("First Login: " + fp + ", " + "Last Login: " + lp);
				writer.newLine();
				System.out.println("checkpoint c");
				writer.flush();
				writer.close();
				System.out.println("checkpoint d");
			} catch (FileNotFoundException err) {
				System.out.println("File Not Found. Good Luck!");
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
	}
}

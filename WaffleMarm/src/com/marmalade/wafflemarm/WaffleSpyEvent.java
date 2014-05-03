package com.marmalade.wafflemarm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class WaffleSpyEvent implements Listener{

	WaffleMarm plugin;

	WaffleSpyEvent(WaffleMarm instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGHEST)             
	public void onPlayerLogin(PlayerLoginEvent event){
		OfflinePlayer offlinePlayer = event.getPlayer();
		String ip = event.getAddress().toString();
		long fp = offlinePlayer.getFirstPlayed();
		long lp = offlinePlayer.getLastPlayed();
		String player = event.getPlayer().getDisplayName();
		SimpleDateFormat dateStamp = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
		System.out.print("1");
		String dateFirst = dateStamp.format(fp);
		System.out.print("2");
		String dateLast = dateStamp.format(lp);
		UUID uuid = ((Player) offlinePlayer).getUniqueId();
		File waffleInfo = new File("players.yml");
	
			try {
				waffleInfo.createNewFile();
				System.out.println("3");
			} catch (IOException err) {
				err.printStackTrace();
			}
			
			if(waffleInfo.exists()){
				System.out.println("4");
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(waffleInfo));
					writer.write("Username: " + player + ", " + "IP: " + ip);
					writer.newLine();
					writer.write("UUID: " + uuid);
					writer.newLine();
					writer.write("First Login: " + dateFirst + ", " + "Last Login: " + dateLast);
					writer.newLine();
					System.out.println("5");
					writer.flush();
					writer.close();
					System.out.println("6");
				} catch (FileNotFoundException err) {
					System.out.println("File Not Found. Good Luck!");
				} catch (IOException err) {
					err.printStackTrace();
				}
			}
		}
	}
		
	
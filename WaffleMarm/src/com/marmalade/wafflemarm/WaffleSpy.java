package com.marmalade.wafflemarm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class WaffleSpy implements CommandExecutor {

	WaffleMarm plugin;

	WaffleSpy(WaffleMarm instance) {
		plugin = instance;
	}
	static File df = WaffleMarm.plugin.getDataFolder();

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
		} catch (IOException err) {
			err.printStackTrace();
		}
		if(waffleInfo.exists()){
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(waffleInfo));
				writer.write("Username: " + playerName + ", " + "IP: " + iP);
				writer.newLine();
				writer.write("First Login: " + fp + ", " + "Last Login: " + lp);
				writer.flush();
				writer.close();
			} catch (FileNotFoundException err) {
				System.out.println("File Not Found. Good Luck!");
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
	}

	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;
		String commandName = command.getName().toLowerCase();
		String[] trimmedArgs = args;
		if (commandName.equalsIgnoreCase("seen " + trimmedArgs[0])) {
			if(player.isOp()){
				if (trimmedArgs.length == 1) {
					sender.sendMessage(ChatColor.GOLD + "Player information for " + ChatColor.GREEN + trimmedArgs[0]);
					File waffleInfo = new File("players.yml");
					OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(trimmedArgs[0]);
					long fp = offlinePlayer.getFirstPlayed();
					try {
						if(waffleInfo.exists()){
							BufferedReader reader = new BufferedReader(new FileReader(waffleInfo));
							String str = reader.readLine();
							while(str != null){
								sender.sendMessage(ChatColor.GOLD + str);
							}
							reader.close();
						}	
					} catch (FileNotFoundException e) {
						if(fp == 0){
							System.out.println("Player Does Not Exist!");
						}
						else{
							System.out.println("File Not Found!");
							e.printStackTrace();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return true;
	}
}

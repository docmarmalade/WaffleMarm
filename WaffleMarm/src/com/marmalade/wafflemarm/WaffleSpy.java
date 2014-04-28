package com.marmalade.wafflemarm;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
		OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(null);
		long fp = offlinePlayer.getFirstPlayed();
		long lp = offlinePlayer.getLastPlayed();
		File waffleInfo = new File("C:\\WaffleMarm\'players.yml");
		try {
			waffleInfo.createNewFile();
		} catch (IOException err) {
			err.printStackTrace();
		}
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

	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;
		String commandName = command.getName().toLowerCase();
		String[] trimmedArgs = args;
		if (commandName.equalsIgnoreCase("seen " + trimmedArgs[0])) {
			if(player.isOp()){
				if (trimmedArgs.length == 1) {
					sender.sendMessage(ChatColor.GOLD + "Player information for " + ChatColor.GREEN + trimmedArgs[0]);		
				}
			}
		}
		return true;
	}
}

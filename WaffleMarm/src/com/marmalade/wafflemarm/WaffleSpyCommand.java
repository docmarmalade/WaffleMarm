package com.marmalade.wafflemarm;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WaffleSpyCommand implements CommandExecutor {

	WaffleMarm plugin;

	WaffleSpyCommand(WaffleMarm instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;
		String commandName = command.getName().toLowerCase();
		String[] trimmedArgs = args;
		if (commandName.equalsIgnoreCase("seen")) {
			sender.sendMessage("checkpoint 1");
			if(player.isOp()){
				sender.sendMessage("checkpoint 2");
				if (trimmedArgs.length == 1) {
					sender.sendMessage("checkpoint 3");
					sender.sendMessage(ChatColor.GOLD + "Player information for " + ChatColor.GREEN + trimmedArgs[0]);
					File waffleInfo = new File("players.yml");
					sender.sendMessage("checkpoint 4");
					OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(trimmedArgs[0]);
					long fp = offlinePlayer.getFirstPlayed();
					try {
						if(waffleInfo.exists()){
							sender.sendMessage("checkpoint 5");
							BufferedReader reader = new BufferedReader(new FileReader(waffleInfo));
							String str = reader.readLine();
							sender.sendMessage("" + ChatColor.GOLD + str);
							sender.sendMessage("checkpoint 6");
							reader.close();
							sender.sendMessage("checkpoint 7");
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
			return true;
		}
		return false;
	}
}

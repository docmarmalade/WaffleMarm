package com.marmalade.wafflemarm;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class WaffleSpyCommand implements CommandExecutor {

	WaffleMarm plugin;

	public WaffleSpyCommand(WaffleMarm plugin){
		this.plugin = plugin;
	} 

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;
		String commandName = command.getName();
		String[] trimmedArgs = args;
		if (commandName.equalsIgnoreCase("seen")){
			if(player.isOp()){
				if(trimmedArgs.length == 1){
					OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(trimmedArgs[0]);
					String UUID = offlinePlayer.getUniqueId().toString();
					sender.sendMessage(ChatColor.GOLD + "Player information for " + ChatColor.GREEN + trimmedArgs[0]);
					File waffleInfo = new File ("plugins"+File.separator+"WaffleMarm"+File.separator+"players.yml");
					if(waffleInfo.exists()){
						String info = this.getPlayerCard(UUID);
						sender.sendMessage(info);
					}
					if(sender instanceof Player){
						try
						{
							long time = System.currentTimeMillis()/1000L;	
							boolean fileFound = false;
							File seenAttempts = new File ("plugins"+File.separator+"WaffleMarm"+File.separator+"seen_attempts.log");
							if(!seenAttempts.exists()){
								seenAttempts.createNewFile();
							}

							if(seenAttempts.exists()){
								fileFound = true;
							}
							seenAttempts.canWrite();
							BufferedWriter writer = new BufferedWriter(new FileWriter(seenAttempts, true));
							writer.append("CmdSender: " + sender );
							writer.newLine();
							writer.append("CmdTime: " + time );
							writer.newLine();
							writer.append("FileFound: " + fileFound);
							writer.newLine();
							writer.flush();
							writer.close();
						}
						catch(FileNotFoundException e)
						{
							System.out.println("File Not Found");
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
					}
					return true;
				}

			}
			else{
				player.sendMessage("You do not have permission to do this.");
			}	
		}
		return false;
	}

	public String getPlayerCard(String UUID) {
		FileConfiguration playersFile = WaffleSpyEvent.openPlayersFile();
		String pName = playersFile.getString(UUID + ".name");
		String pIp = playersFile.getString(UUID + ".ip");
		String pFirstDate = playersFile.getString(UUID + ".firstLogin");
		String pLastDate = playersFile.getString(UUID + ".lastLogin");
		String playerInfo = ("UUID: " + UUID + "\nUsername: " + pName + "\nIP: " + pIp + "\nFirstLogin: " + pFirstDate + "\nLastLogin: " + pLastDate);
		return playerInfo;
	}
}







package com.marmalade.wafflemarm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class WaffleSpyCommand implements CommandExecutor {

	WaffleMarm plugin;

	public WaffleSpyCommand(WaffleMarm plugin){
		this.plugin = plugin;
	} 

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;
		String commandName = command.getName();
		String[] trimmedArgs = args;
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(trimmedArgs[0]);
		System.out.println("1");
		if (commandName.equalsIgnoreCase("seen" + trimmedArgs[0])){
			System.out.println("2");
			if(player.isOp()){
				System.out.println("3");
				if(trimmedArgs.length == 1){
					System.out.println("4");
					sender.sendMessage(ChatColor.GOLD + "Player information for " + ChatColor.GREEN + trimmedArgs[0]);
					File waffleInfo = new File ("plugins"+File.separator+"WaffleMarm"+File.separator+"players.yml");
					if(waffleInfo.exists()){
						System.out.println("5");
						String UUID = ((Player) offlinePlayer).getUniqueId().toString(); 
						String info = this.getPlayerCard(UUID);
						sender.sendMessage(info);	
						return true;
					}
				}
			}
			else{
				player.sendMessage("You do not have permission to do this.");
			}
		}
		return false;
	}
	
	private static FileConfiguration fileConfig = new YamlConfiguration();
	private static File seenAttempts = new File ("plugins"+File.separator+"WaffleMarm"+File.separator+"seen_attempts.log");

	public String getPlayerCard(String UUID) {
		FileConfiguration playersFile = WaffleSpyEvent.openPlayersFile();
		String pName = playersFile.getString(UUID + ".name");
		String pIp = playersFile.getString(UUID + ".ip");
		String pUUID = playersFile.getString(UUID + ".uuid");
		String pFirstDate = playersFile.getString(UUID + ".firstLogin");
		String pLastDate = playersFile.getString(UUID + ".lastLogin");
		String playerInfo = ("UUID: " + pUUID + "\nUsername: " + pName + "\nIP: " + pIp + "\nFirstLogin: " + pFirstDate + "\nLastLogin: " + pLastDate);
		return playerInfo;
	}

	public String getAttempts(CommandSender sender, long time, boolean fileFound){
		FileConfiguration seenFile = openSeenFile();
		String cmdSender = seenFile.getString("seen." + sender + ".sender");
		String cmdTime = seenFile.getString("seen." + time + ".time");
		String cmdFound = seenFile.getString("seen." + fileFound + ".fileFound");
		String cmdResult = ("CmdSender: " + cmdSender + "\nCmdTime: " + cmdTime + "\nFileResult: " + cmdFound);
		return cmdResult;	
	}
	
	static FileConfiguration openSeenFile() {
		fileConfig = WaffleMarm.plugin.getConfig();

		if (!WaffleMarm.plugin.getDataFolder().exists()) {
			WaffleMarm.plugin.getDataFolder().mkdirs();
		}

		try {
			fileConfig.load(seenAttempts);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}

		return fileConfig;
	}
}






package com.marmalade.wafflemarm;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;



public class WaffleSpyCommand implements CommandExecutor, Listener {

	WaffleMarm plugin;

	WaffleSpyCommand(WaffleMarm instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)             
	public void onPlayerLogin(PlayerLoginEvent event){
		String name = event.getPlayer().getName(); 
		File waffleInfo = new File ("plugins"+File.separator+"WaffleMarm"+File.separator+"players"+File.separator+name);
		FileConfiguration fileConfig = new YamlConfiguration();
		String ip = event.getAddress().toString();
		OfflinePlayer offlinePlayer = event.getPlayer();
		String UUID = ((Player) offlinePlayer).getUniqueId().toString();;
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
		String firstDate = sdf.format(offlinePlayer.getFirstPlayed());
		String lastDate = null;
		if(offlinePlayer.hasPlayedBefore()){
			lastDate = sdf.format(offlinePlayer.getLastPlayed());
		}

		if(!waffleInfo.exists()){
			try {
				waffleInfo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fileConfig = YamlConfiguration.loadConfiguration(waffleInfo);
				fileConfig.set("Username:", name);
				fileConfig.set("IP:", ip);
				fileConfig.set("UUID:", UUID);
				fileConfig.set("FirstLogin:", firstDate);
				fileConfig.set("LastLogin:", lastDate);
				fileConfig.save(waffleInfo);
				Bukkit.getLogger().info(waffleInfo.getPath());
			} catch (FileNotFoundException err) {
				System.out.println("File Not Found. Good Luck!");
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
		Bukkit.getLogger().info("Created a new instance of players.yml: " + name);	
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;
		String commandName = command.getName().toLowerCase();
		String[] trimmedArgs = args;
		if (commandName.equalsIgnoreCase("seen")) {
			if(player.isOp()){
				if (trimmedArgs.length == 1) {
					sender.sendMessage(ChatColor.GOLD + "Player information for " + ChatColor.GREEN + trimmedArgs[0]);
					File waffleInfo = new File ("plugins"+File.separator+"WaffleMarm"+File.separator+"players"+File.separator+trimmedArgs[0]);
					OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(trimmedArgs[0]);
					long fp = offlinePlayer.getFirstPlayed();
					try {
						if(waffleInfo.exists()){
							BufferedReader reader = new BufferedReader(new FileReader(waffleInfo));
							String str = reader.readLine();
							sender.sendMessage("" + ChatColor.GOLD + str);
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
			else{
				player.sendMessage("You do not have permission to do this.");
			}
			return true;
		}
		return false;
	}		
}






package com.marmalade.wafflemarm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class WaffleSpyEvent implements Listener {

	WaffleMarm plugin;

	WaffleSpyEvent(WaffleMarm instance) {
		plugin = instance;
	}

	private static FileConfiguration fileConfig = new YamlConfiguration();
	private static File waffleInfo = new File ("plugins"+File.separator+"WaffleMarm"+File.separator+"players.yml");

	@EventHandler(priority = EventPriority.HIGHEST)             
	public void onPlayerLogin(PlayerLoginEvent event){
		String name = event.getPlayer().getName(); 	
		String ip = event.getAddress().toString();
		OfflinePlayer offlinePlayer = event.getPlayer();
		String UUID = ((Player) offlinePlayer).getUniqueId().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
		String firstDate = sdf.format(offlinePlayer.getFirstPlayed());
		String lastDate = sdf.format(offlinePlayer.getLastPlayed());
		addPlayer(name, ip, UUID, firstDate, lastDate);
		Bukkit.getLogger().info("Created a new instance of players.yml: " + name);	
	}

	public static void addPlayer (String name, String ip, String UUID, String firstDate, String lastDate) {
		FileConfiguration playersFile = openPlayersFile();
		playersFile.set(UUID + ".name", name);
		playersFile.set(UUID + ".ip", ip);
		playersFile.set(UUID + ".firstLogin", firstDate);
		playersFile.set(UUID + ".lastLogin", lastDate);

		try {
			playersFile.save(waffleInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static FileConfiguration openPlayersFile() {
		fileConfig = WaffleMarm.plugin.getConfig();

		if (!WaffleMarm.plugin.getDataFolder().exists()) {
			WaffleMarm.plugin.getDataFolder().mkdirs();
		}

		try {
			fileConfig.load(waffleInfo);
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

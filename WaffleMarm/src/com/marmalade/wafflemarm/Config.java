package com.marmalade.wafflemarm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config extends JavaPlugin {

	static YamlConfiguration myConfig;
	public static File configFile;
	private FileConfiguration config;
	File df = WaffleMarm.plugin.getDataFolder();

	public boolean loadSettings() {
		configFile = new File(df, "WaffleMarm/players.yml");
		config = WaffleMarm.plugin.getConfig();

		if (!df.exists()) {
			df.mkdirs();
		}

		try {
			config.load(configFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return true;
	}
}



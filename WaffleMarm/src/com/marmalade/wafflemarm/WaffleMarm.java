package com.marmalade.wafflemarm;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class WaffleMarm extends JavaPlugin {

	public final Logger log = Logger.getLogger("Minecraft");
	private final ChatListener ChatListener = new ChatListener(this);

	@Override
	public void onEnable(){
		getLogger().info("It's Alive!!");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(ChatListener, this);
	}
}




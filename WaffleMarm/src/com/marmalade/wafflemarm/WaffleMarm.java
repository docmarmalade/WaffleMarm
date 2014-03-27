package com.marmalade.wafflemarm;

import java.util.HashMap;
import java.util.logging.Logger;
import com.marmalade.wafflemarm.ChatListener;
import com.marmalade.wafflemarm.JoinListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
 
public final class WaffleMarm extends JavaPlugin {
	
	public HashMap<String, String> chat = new HashMap<String, String>();
	
	public final Logger log = Logger.getLogger("Minecraft");
	
	@Override
    public void onEnable(){
		getLogger().info("It's Alive!!");

	PluginManager pm = this.getServer().getPluginManager();	
		
	pm.registerEvents(new JoinListener (this), this);
	pm.registerEvents(new ChatListener (this), this);	
	
	getCommand("chat").setExecutor(new Commands(this));	
		
    }
	
	@Override 
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	 
		if(cmd.getName().equalsIgnoreCase("hi")){	
			if(sender instanceof Player){
				String displayName = sender.getName();
				sender.sendMessage("Hi " + displayName + "!");
				return true;
			}
 			else{
 				sender.sendMessage("You must be a player to use this command.");
 			}
     }
		return false;
    } 
}

	


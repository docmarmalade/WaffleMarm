package com.marmalade.wafflemarm;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
 
public final class WaffleMarm extends JavaPlugin {
	
	public final Logger log = Logger.getLogger("Minecraft");
	
	@Override
    public void onEnable(){
		getLogger().info("It's Alive!!");
    }
    @Override 
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
         
    	if(cmd.getName().equalsIgnoreCase("hi")){
    		if(sender instanceof Player){
    			String displayName = sender.getName();
    			sender.sendMessage("Hi " + displayName + "!");
    		return true;
    		}
    			else{
    				sender.sendMessage("You must be a player to use this command.");
    		return true;
    		}
    	}
    		return false;
    } //end boolean
} //end class

	


package com.marmalade.wafflemarm;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{

	public WaffleMarm plugin; 

	public Commands(WaffleMarm plugin){
		this.plugin = plugin;
	} //end Commands

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














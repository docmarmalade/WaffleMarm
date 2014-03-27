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
        
		Player player = (Player) sender;
		
		if (label.equalsIgnoreCase("chat")) {
        	if(args.length == 1){
        		plugin.chat.put(player.getName(), args[0].toLowerCase());
              } //end if
            else {
        	plugin.chat.put(player.getName(), "default");
              } //end else
		} //end if label
		return true;
	}
}
        
        
       
        
	

        


	
    	
    		


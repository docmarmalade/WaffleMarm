package com.marmalade.wafflemarm;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

public class Commands implements CommandExecutor{

	public WaffleMarm plugin; 

	public Commands(WaffleMarm plugin){
		this.plugin = plugin;
	} //end Commands

	@Override 
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		String displayName = sender.getName();
		Player player = (Player) sender;

		if(cmd.getName().equalsIgnoreCase("hi")){	
			if(sender instanceof Player){
				sender.sendMessage("Hi " + displayName + "!");
				return true;
			}
			else{
				sender.sendMessage("You must be a player to use this command.");
			} //end else
		} //end if

		if(cmd.getName().equalsIgnoreCase("jump")){

			double x = player.getLocation().getX(); 
			double y = player.getLocation().getY();
			double z = player.getLocation().getZ();
			String worldName = new String("world");
			World world = plugin.getServer().getWorld(worldName);
			Location location = new Location(world,x,(y + 20),z);
			player.teleport(location);
			player.sendMessage(ChatColor.YELLOW + "What goes up...Must come down!");
			return true;
		}

		if(player.isOp()){
			if(cmd.getName().equalsIgnoreCase("desert")){
				Chunk[] c = player.getWorld().getLoadedChunks();
				int chunkCount = 1; // init. chunk index
				for(int i = 0; i < c.length; i++){	
					if(i > 0){
						chunkCount++;


		
					} else{player.sendMessage("Player must be op to use this command");
					}
				}
			}
		}
		return false;
	}
}


























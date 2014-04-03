package com.marmalade.wafflemarm;

import java.util.Random;

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
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

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
		return false;
	}
	
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String command = event.getMessage();
		if(player.isOp()){
			if (command.startsWith("/desert")) {
				player.sendMessage("I shall bring you the desert..");
				int count = 0;
				for (Chunk chunk : player.getWorld().getLoadedChunks()) {
					for (int x = 0; x < 16; x = x + 1) {
						for (int z = 0; z < 16; z = z + 1) {
							Block block = chunk.getBlock(x, 0, z);
							int pick = new Random().nextInt(Biome.values().length);
							Biome biome = Biome.values()[pick];
							block.setBiome(biome);
						}
					}
					count++;
				} 
				player.sendMessage("Chunks Enhanced: " + count);
			}
		}
	}
}



























package de.worldcrafter.sressentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.worldcrafter.sressentials.utils.SrEssentialsPermissions;

/**
* @Author ClientConnection
* @Project sressentials
* @Package de.clientconnection.sressentials.commands
* @Date 14.09.2019
* @Time 15:55:47
*/
public class Gamemode_CMD implements CommandExecutor, SrEssentialsPermissions {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			
			return false;
		}
		
		final Player player = (Player) sender;
		if(!player.hasPermission(GAMEMODE_OTHER) && !player.hasPermission(GAMEMODE_SELF) && !player.hasPermission(SUPER_PERM)) {
			
			return false;
		}
		if(args.length == 1) {
			
		}
		
		return false;
	}
}

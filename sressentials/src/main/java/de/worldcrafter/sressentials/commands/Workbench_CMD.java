package de.worldcrafter.sressentials.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.worldcrafter.sressentials.SrEssentials;
import de.worldcrafter.sressentials.language.LanguageManager;
import de.worldcrafter.sressentials.utils.SrEssentialsPermissions;

/**
* @Author ClientConnection
* @Project sressentials
* @Package de.worldcrafter.sressentials.commands
* @Date 15.09.2019
* @Time 16:52:31
*/
public class Workbench_CMD implements CommandExecutor, SrEssentialsPermissions {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		final LanguageManager lm = SrEssentials.getLanguageManager();
		if(!(sender instanceof Player)) {
			sender.sendMessage(lm.prefix + "§cThis is not a command for console.");
			return false;
		}
		final Player player = (Player) sender;
		if(!player.hasPermission(SUPER_PERM) && !player.hasPermission(WORKBENCH_SELF)) {
			sender.sendMessage(lm.noPermissions.toString());
			return false;
		}
		player.openWorkbench(player.getLocation(), true);
		player.playSound(player.getLocation(), Sound.NOTE_BASS, 10F, 10F);
		player.sendMessage(lm.openedWorkbench.toString());
		return false;
	}
}

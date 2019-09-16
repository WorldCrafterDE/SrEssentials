package de.worldcrafter.sressentials.commands.teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.worldcrafter.sressentials.SrEssentials;
import de.worldcrafter.sressentials.language.LanguageManager;
import de.worldcrafter.sressentials.utils.SrEssentialsPermissions;

public class TeleportHere_CMD implements CommandExecutor, SrEssentialsPermissions {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		final LanguageManager lm = SrEssentials.getLanguageManager();
		if(!(sender instanceof Player)) {
			sender.sendMessage(lm.prefix + "§cThis is not a command for console.");
			return false;
		}
		final Player player = (Player) sender;
		if(!player.hasPermission(TELEPORT_OTHER_TO_OTHER) && !player.hasPermission(SUPER_PERM)) {
			sender.sendMessage(lm.noPermissions.toString());
			return false;
		}
		if(args.length == 1) {
			final Player target = Bukkit.getPlayer(args[0]);
			if(target == null) {
				sender.sendMessage(lm.notOnline.setPlayer(args[0]));
				return false;
			}
			target.teleport(player);
			sender.sendMessage(lm.teleportAPlayerToPlayer.setPlayerValue(target, player.getName()));
			target.sendMessage(lm.teleportToPlayer.setPlayer(player));
			return false;
		} 
		sender.sendMessage(lm.syntaxError + "\n"
				+ "/" + command.getName() + " <Player>");
		return false;
	}
}

package de.worldcrafter.sressentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.worldcrafter.sressentials.SrEssentials;
import de.worldcrafter.sressentials.language.LanguageManager;
import de.worldcrafter.sressentials.language.LanguageMessage;
import de.worldcrafter.sressentials.utils.SrEssentialsPermissions;

/**
* @Author ClientConnection
* @Project sressentials
* @Package de.clientconnection.sressentials.commands
* @Date 14.09.2019
* @Time 15:55:47
*/
public class Gamemode_CMD implements CommandExecutor, SrEssentialsPermissions {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		final LanguageManager lm = SrEssentials.getLanguageManager();
		if(!(sender instanceof Player)) {
			if(args.length == 2) {
				final Player target = Bukkit.getPlayer(args[1]);
				if(target == null) {
					sender.sendMessage(lm.notOnline.setPlayer(args[1]));
					return false;
				}
				try {
					final int i = Integer.parseInt(args[0]);
					if(!(i >= 0 && i <= 3)) {
						sendSyntax(sender, command, lm.syntaxError);
						return false;
					}
					final GameMode gm = GameMode.getByValue(i);
					target.setGameMode(gm);
					target.sendMessage(lm.setGamemodeByOther.setPlayerValue("CONSOLE", i));
					sender.sendMessage(lm.setGamemodeOther.setPlayerValue(target, i));
					return false;
				} catch(final NumberFormatException exc) {
					sendSyntax(sender, command, lm.syntaxError);
					return false;
				}
			} 
			sendSyntax(sender, command, lm.syntaxError);
			return false;
		}
		final Player player = (Player) sender;
		if(!player.hasPermission(GAMEMODE_OTHER) && !player.hasPermission(GAMEMODE_SELF) && !player.hasPermission(SUPER_PERM)) {
			sender.sendMessage(lm.noPermissions.toString());
			return false;
		}
		if(args.length == 1) {
			try {
				final int i = Integer.parseInt(args[0]);
				if(!(i >= 0 && i <= 3)) {
					sendSyntax(sender, command, lm.syntaxError);
					return false;
				}
				final GameMode gm = GameMode.getByValue(i);
				player.setGameMode(gm);
				sender.sendMessage(lm.setGamemodeSelf.setValue(i));
				return false;
			} catch(final NumberFormatException exc) {
				sendSyntax(sender, command, lm.syntaxError);
				return false;
			}
		} else if(args.length == 2) {
			if(!player.hasPermission(GAMEMODE_OTHER) && !player.hasPermission(SUPER_PERM)) {
				sender.sendMessage(lm.noPermissions.toString());
				return false;
			}
			final Player target = Bukkit.getPlayer(args[1]);
			if(target == null) {
				sender.sendMessage(lm.notOnline.setPlayer(args[1]));
				return false;
			}
			try {
				final int i = Integer.parseInt(args[0]);
				if(!(i >= 0 && i <= 3)) {
					sendSyntax(sender, command, lm.syntaxError);
					return false;
				}
				final GameMode gm = GameMode.getByValue(i);
				target.setGameMode(gm);
				target.sendMessage(lm.setGamemodeByOther.setPlayerValue(player, i));
				sender.sendMessage(lm.setGamemodeOther.setPlayerValue(target, i));
				return false;
			} catch(final NumberFormatException exc) {
				sendSyntax(sender, command, lm.syntaxError);
				return false;
			}
		} 
		sendSyntax(sender, command, lm.syntaxError);
		return false;
	}
	
	private void sendSyntax(final CommandSender sender, final Command command, final LanguageMessage syntax) {
		if(sender instanceof Player) {
			final Player player = (Player) sender;
			if(!player.hasPermission(SUPER_PERM) && !player.hasPermission(GAMEMODE_OTHER)) {
				sender.sendMessage(syntax + "\n"
						+ "/" + command.getName() + " <0|1|2|3>");
				return;
			}
			return;
		}
		sender.sendMessage(syntax + "\n"
				+ "/" + command.getName() + " <0|1|2|3> [Player]");
	}
}

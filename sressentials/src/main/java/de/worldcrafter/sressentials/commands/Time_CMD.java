package de.worldcrafter.sressentials.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
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
* @Package de.worldcrafter.sressentials.commands
* @Date 15.09.2019
* @Time 17:15:18
*/
public class Time_CMD implements CommandExecutor, SrEssentialsPermissions {

	private final Map<Player, Long> noSpam = new HashMap<Player, Long>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		final LanguageManager lm = SrEssentials.getLanguageManager();
		if(!(sender instanceof Player)) {
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("day") || args[0].equalsIgnoreCase("tag")) {
					for(World world : Bukkit.getWorlds()) {
						world.setTime(24000L);
					}
					for(Player all : Bukkit.getOnlinePlayers()) {
						all.sendMessage(lm.timeSetToDay.toString());
					}
					sender.sendMessage(lm.timeDay.toString());
					return false;
				} else if(args[0].equalsIgnoreCase("night") || args[0].equalsIgnoreCase("nacht")) {
					for(World world : Bukkit.getWorlds()) {
						world.setTime(14000L);
					}
					for(Player all : Bukkit.getOnlinePlayers()) {
						all.sendMessage(lm.timeSetToNight.toString());
					}
					sender.sendMessage(lm.timeNight.toString());
					return false;
				} else {
					try {
						final long time = Long.parseLong(args[0]);
						for(World world : Bukkit.getWorlds()) {
							world.setTime(time);
						}
						for(Player all : Bukkit.getOnlinePlayers()) {
							all.sendMessage(lm.timeSetToX.setValue(time));
						}
						sender.sendMessage(lm.timeX.setValue(time));
						return false;
					} catch(final NumberFormatException exc) {
						sendSyntax(sender, command, lm.syntaxError);
						return false;
					}
	 			}
			}
			return false;
		}
		final Player player = (Player) sender;
		if(!player.hasPermission(TIME_DAY) && !player.hasPermission(TIME_NIGHT) && !player.hasPermission(SUPER_PERM)) {
			player.sendMessage(lm.noPermissions.toString());
			return false;
		}
		
		if(!player.hasPermission(SUPER_PERM) && !player.hasPermission(TIME_SPAM_BYPASS)) {
			if(noSpam.containsKey(player)) {
				final Long time = noSpam.get(player);
				if(time > System.currentTimeMillis()) {
					sender.sendMessage(lm.timeNoSpam.setValue((time-System.currentTimeMillis())/1000));
					return false;
				} 
				noSpam.remove(player);
			}
		}
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("day") || args[0].equalsIgnoreCase("tag")) {
				player.getWorld().setTime(24000L);
				noSpam.put(player, SrEssentials.getConfigHandler().getTimeAntiSpamTime());
				for(Player all : player.getWorld().getPlayers()) {
					all.sendMessage(lm.timeSetToDay.toString());
				}
				sender.sendMessage(lm.timeDay.toString());
				return false;
			} else if(args[0].equalsIgnoreCase("night") || args[0].equalsIgnoreCase("nacht")) {
				player.getWorld().setTime(14000L);
				noSpam.put(player, SrEssentials.getConfigHandler().getTimeAntiSpamTime());
				for(Player all : player.getWorld().getPlayers()) {
					all.sendMessage(lm.timeSetToNight.toString());
				}
				sender.sendMessage(lm.timeNight.toString());
				return false;
			} else {
				try {
					final long time = Long.parseLong(args[0]);
					player.getWorld().setTime(time);
					noSpam.put(player, SrEssentials.getConfigHandler().getTimeAntiSpamTime());
					for(Player all : player.getWorld().getPlayers()) {
						all.sendMessage(lm.timeSetToX.setValue(time));
					}
					player.sendMessage(lm.timeX.setValue(time));
					return false;
				} catch(final NumberFormatException exc) {
					sendSyntax(sender, command, lm.syntaxError);
					return false;
				}
 			}
		}
		sendSyntax(sender, command, lm.syntaxError);
		return false;
	}
	
	private void sendSyntax(final CommandSender sender, final Command command, final LanguageMessage syntaxError) {
		sender.sendMessage(syntaxError + "\n"
				+ "/" + command.getName() + " <Day/Night/Time>");
	}
}

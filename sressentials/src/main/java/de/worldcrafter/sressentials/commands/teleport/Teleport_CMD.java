package de.worldcrafter.sressentials.commands.teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.worldcrafter.sressentials.SrEssentials;
import de.worldcrafter.sressentials.language.LanguageManager;
import de.worldcrafter.sressentials.language.LanguageMessage;
import de.worldcrafter.sressentials.utils.SrEssentialsPermissions;

public class Teleport_CMD implements CommandExecutor, SrEssentialsPermissions {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		final LanguageManager lm = SrEssentials.getLanguageManager();
		if(!(sender instanceof Player)) {
			if(args.length == 2) {
				final Player fromTarget = Bukkit.getPlayer(args[0]);
				if(fromTarget == null) {
					sender.sendMessage(lm.notOnline.setPlayer(args[0]));
					return false;
				}
				final Player toTarget = Bukkit.getPlayer(args[1]);
				if(toTarget == null) {
					sender.sendMessage(lm.notOnline.setPlayer(args[1]));
					return false;
				}
				if(fromTarget.equals(toTarget)) {
					sender.sendMessage(lm.samePlayer.toString());
					return false;
				}
				fromTarget.teleport(toTarget);
				sender.sendMessage(lm.teleportAPlayerToPlayer.setPlayerValue(fromTarget, toTarget));
				fromTarget.sendMessage(lm.teleportToPlayer.setPlayer(toTarget));
				return false;
			} else if(args.length == 4) {
				final Player target = Bukkit.getPlayer(args[0]);
				if(target == null) {
					sender.sendMessage(lm.notOnline.toString());
					return false;
				}
				try {
					String x = args[1], y = args[2], z = args[3];
					double xx, yy, zz;
					if(x.startsWith("~")) x = ((int) target.getLocation().getX() + Integer.parseInt(x.substring(1))+"");
					else if(x.endsWith("~")) x = ((int) target.getLocation().getX() + Integer.parseInt(x.substring(0, x.length()-1))+"");
					if(y.startsWith("~")) y = ((int) target.getLocation().getY() + Integer.parseInt(y.substring(1))+"");
					else if(y.endsWith("~")) y = ((int) target.getLocation().getY() + Integer.parseInt(y.substring(0, y.length()-1))+"");
					if(z.startsWith("~")) z = ((int) target.getLocation().getZ() + Integer.parseInt(z.substring(1))+"");
					else if(z.endsWith("~")) z = ((int) target.getLocation().getZ() + Integer.parseInt(z.substring(0, z.length()-1))+"");
					xx = Double.parseDouble(x);
					yy = Double.parseDouble(y);
					zz = Double.parseDouble(z);
					final Location loc = new Location(target.getWorld(), xx, yy, zz);
					target.teleport(loc);
					sender.sendMessage(lm.teleportAPlayerToLocation.setValue("\"§eWorld: §c" + target.getWorld().getName() + " §eX: §c" + xx + " §eY: §c" + yy + " §eZ: §c" + zz + "\""));
					target.sendMessage(lm.teleportToLocation.setValue("\"§eWorld: §c" + target.getWorld().getName() + " §eX: §c" + xx + " §eY: §c" + yy + " §eZ: §c" + zz + "\""));
					return false;
				} catch(final NumberFormatException exc) {
					sendSyntax(sender, command, true, lm.syntaxError);
					return false;
				}
			}
			sendSyntax(sender, command, true, lm.syntaxError);
			return false;
		}
		final Player player = (Player) sender;
		if(!player.hasPermission(TELEPORT_OTHER_TO_OTHER) && !player.hasPermission(TELEPORT_SELF_TO_OTHER) && !player.hasPermission(SUPER_PERM)) {
			sender.sendMessage(lm.noPermissions.toString());
			return false;
		}
		if(args.length == 1) {
			final Player target = Bukkit.getPlayer(args[0]);
			if(target == null) {
				sender.sendMessage(lm.notOnline.setPlayer(args[0]));
				return false;
			}
			player.teleport(target);
			sender.sendMessage(lm.teleportToPlayer.setPlayer(target));
			return false;
		} else if(args.length == 2) {
			final Player fromTarget = Bukkit.getPlayer(args[0]);
			if(fromTarget == null) {
				sender.sendMessage(lm.notOnline.setPlayer(args[0]));
				return false;
			}
			final Player toTarget = Bukkit.getPlayer(args[1]);
			if(toTarget == null) {
				sender.sendMessage(lm.notOnline.setPlayer(args[1]));
				return false;
			}
			if(fromTarget.equals(toTarget)) {
				sender.sendMessage(lm.samePlayer.toString());
				return false;
			}
			fromTarget.teleport(toTarget);
			sender.sendMessage(lm.teleportAPlayerToPlayer.setPlayerValue(fromTarget, toTarget));
			fromTarget.sendMessage(lm.teleportToPlayer.setPlayer(toTarget));
			return false;
		} else if(args.length == 3) {
			try {
				String x = args[0], y = args[1], z = args[2];
				double xx, yy, zz;
				if(x.startsWith("~")) x = ((int) player.getLocation().getX() + Integer.parseInt(x.substring(1))+"");
				else if(x.endsWith("~")) x = ((int) player.getLocation().getX() + Integer.parseInt(x.substring(0, x.length()-1))+"");
				if(y.startsWith("~")) y = ((int) player.getLocation().getY() + Integer.parseInt(y.substring(1))+"");
				else if(y.endsWith("~")) y = ((int) player.getLocation().getY() + Integer.parseInt(y.substring(0, y.length()-1))+"");
				if(z.startsWith("~")) z = ((int) player.getLocation().getZ() + Integer.parseInt(z.substring(1))+"");
				else if(z.endsWith("~")) z = ((int) player.getLocation().getZ() + Integer.parseInt(z.substring(0, z.length()-1))+"");
				xx = Double.parseDouble(x);
				yy = Double.parseDouble(y);
				zz = Double.parseDouble(z);
				final Location loc = new Location(player.getWorld(), xx, yy, zz);
				player.teleport(loc);
				sender.sendMessage(lm.teleportToLocation.setValue("\"§eWorld: §c" + player.getWorld().getName() + " §eX: §c" + xx + " §eY: §c" + yy + " §eZ: §c" + zz + "\""));
			} catch(final NumberFormatException exc) {
				sendSyntax(sender, command, player.hasPermission(TELEPORT_OTHER_TO_OTHER) || player.hasPermission(TELEPORT_SELF_TO_OTHER), lm.syntaxError);
				return false;
			}
		} else if(args.length == 4) {
			final Player target = Bukkit.getPlayer(args[0]);
			if(target == null) {
				sender.sendMessage(lm.notOnline.toString());
				return false;
			}
			try {
				String x = args[1], y = args[2], z = args[3];
				double xx, yy, zz;
				if(x.startsWith("~")) x = ((int) player.getLocation().getX() + Integer.parseInt(x.substring(1))+"");
				else if(x.endsWith("~")) x = ((int) player.getLocation().getX() + Integer.parseInt(x.substring(0, x.length()-1))+"");
				if(y.startsWith("~")) y = ((int) player.getLocation().getY() + Integer.parseInt(y.substring(1))+"");
				else if(y.endsWith("~")) y = ((int) player.getLocation().getY() + Integer.parseInt(y.substring(0, y.length()-1))+"");
				if(z.startsWith("~")) z = ((int) player.getLocation().getZ() + Integer.parseInt(z.substring(1))+"");
				else if(z.endsWith("~")) z = ((int) player.getLocation().getZ() + Integer.parseInt(z.substring(0, z.length()-1))+"");
				xx = Double.parseDouble(x);
				yy = Double.parseDouble(y);
				zz = Double.parseDouble(z);
				final Location loc = new Location(player.getWorld(), xx, yy, zz);
				target.teleport(loc);
				sender.sendMessage(lm.teleportAPlayerToLocation.setValue("\"§eWorld: §c" + player.getWorld().getName() + " §eX: §c" + xx + " §eY: §c" + yy + " §eZ: §c" + zz + "\""));
				target.sendMessage(lm.teleportToLocation.setValue("\"§eWorld: §c" + player.getWorld().getName() + " §eX: §c" + xx + " §eY: §c" + yy + " §eZ: §c" + zz + "\""));
				return false;
			} catch(final NumberFormatException exc) {
				sendSyntax(sender, command, player.hasPermission(TELEPORT_OTHER_TO_OTHER) || player.hasPermission(TELEPORT_SELF_TO_OTHER), lm.syntaxError);
				return false;
			}
		}
		sendSyntax(sender, command, player.hasPermission(TELEPORT_OTHER_TO_OTHER) || player.hasPermission(TELEPORT_SELF_TO_OTHER), lm.syntaxError);
		return false;
	}
	
	private void sendSyntax(final CommandSender sender, final Command command, final boolean canTpLocation, final LanguageMessage syntax) {
		sender.sendMessage(syntax + "\n"
				+ "/" + command.getName() + " <ToPlayer>"
				+ String.valueOf(!canTpLocation ? "" : "\n"
				+ "/" + command.getName() + " <FromPlayer> <ToPlayer>\n"
				+ "/" + command.getName() + " <FromPlayer> <X> <Y> <Z>"));
	}
}

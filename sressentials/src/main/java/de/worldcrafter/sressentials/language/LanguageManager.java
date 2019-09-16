package de.worldcrafter.sressentials.language;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import de.worldcrafter.sressentials.SrEssentials;
import net.md_5.bungee.api.ChatColor;

/**
* @Author ClientConnection
* @Project sressentials
* @Package de.worldcrafter.sressentials.language
* @Date 15.09.2019
* @Time 15:10:43
*/
public class LanguageManager {

	public LanguageMessage prefix, noPermissions, notOnline, syntaxError;				 //Utils
	public LanguageMessage setGamemodeSelf, setGamemodeOther, setGamemodeByOther;//Gamemode
	public LanguageMessage openedWorkbench, openedEnderchest;
	public LanguageMessage timeDay, timeNight, timeX, timeNoSpam, timeSetToDay, timeSetToNight, timeSetToX;
	
	private final File f;
	private final YamlConfiguration cfg;
	
	public LanguageManager() {
		this.f = new LanguageFileBuilder(SrEssentials.getConfigManager()).build();
		this.cfg = YamlConfiguration.loadConfiguration(f);
		this.prefix = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefix")));
		this.noPermissions = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("No-Permissions").replaceAll("%prefix%", this.prefix.toString())));
		this.notOnline = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Not-Online").replaceAll("%prefix%", this.prefix.toString())));
		this.syntaxError = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Syntax-Error").replaceAll("%prefix%", this.prefix.toString())));
		this.setGamemodeSelf = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Set-Gamemode-Self").replaceAll("%prefix%", this.prefix.toString())));
		this.setGamemodeOther = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Set-Gamemode-Other").replaceAll("%prefix%", this.prefix.toString())));
		this.setGamemodeByOther = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Set-Gamemode-ByOther").replaceAll("%prefix%", this.prefix.toString())));
		this.openedWorkbench = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Opened-Workbench").replaceAll("%prefix%", this.prefix.toString())));
		this.openedEnderchest = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Opened-Enderchest").replaceAll("%prefix%", this.prefix.toString())));
		this.timeDay = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Time-Day").replaceAll("%prefix%", this.prefix.toString())));
		this.timeNight = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Time-Night").replaceAll("%prefix%", this.prefix.toString())));
		this.timeX = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Time-X").replaceAll("%prefix%", this.prefix.toString())));
		this.timeNoSpam = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Time-NoSpam").replaceAll("%prefix%", this.prefix.toString())));
		this.timeSetToDay = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Time-Set-To-Day").replaceAll("%prefix%", this.prefix.toString())));
		this.timeSetToNight = new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Time-Set-To-Night").replaceAll("%prefix%", this.prefix.toString())));
		this.timeSetToX= new LanguageMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("Time-Set-To-X").replaceAll("%prefix%", this.prefix.toString())));
	}
}

package de.worldcrafter.sressentials.language;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import de.worldcrafter.sressentials.SrEssentials;

/**
* @Author ClientConnection
* @Project sressentials
* @Package de.worldcrafter.sressentials.language
* @Date 15.09.2019
* @Time 15:10:43
*/
public class LanguageManager {

	public LanguageMessage prefix, noPermissions, notOnline, syntaxError, notYou, samePlayer;
	public LanguageMessage setGamemodeSelf, setGamemodeOther, setGamemodeByOther;
	public LanguageMessage openedWorkbench, openedEnderchest;
	public LanguageMessage timeDay, timeNight, timeX, timeNoSpam, timeSetToDay, timeSetToNight, timeSetToX;
	public LanguageMessage teleportToPlayer, teleportAPlayerToPlayer, teleportToLocation, teleportAPlayerToLocation;
	
	private final File f;
	private final YamlConfiguration cfg;
	
	public LanguageManager() {
		this.f = new LanguageFileBuilder(SrEssentials.getConfigManager()).build();
		this.cfg = YamlConfiguration.loadConfiguration(f);
		this.prefix = new LanguageMessage("Prefix");
		SrEssentials.getInstance().prefixSet = true;
		this.noPermissions = new LanguageMessage("No-Permissions");
		this.notOnline = new LanguageMessage("Not-Online");
		this.syntaxError = new LanguageMessage("Syntax-Error");
		this.notYou = new LanguageMessage("Not-You");
		this.samePlayer = new LanguageMessage("Same-Player");
		this.setGamemodeSelf = new LanguageMessage("Set-Gamemode-Self");
		this.setGamemodeOther = new LanguageMessage("Set-Gamemode-Other");
		this.setGamemodeByOther = new LanguageMessage("Set-Gamemode-ByOther");
		this.openedWorkbench = new LanguageMessage("Opened-Workbench");
		this.openedEnderchest = new LanguageMessage("Opened-Enderchest");
		this.timeDay = new LanguageMessage("Time-Day");
		this.timeNight = new LanguageMessage("Time-Night");
		this.timeX = new LanguageMessage("Time-X");
		this.timeNoSpam = new LanguageMessage("Time-NoSpam");
		this.timeSetToDay = new LanguageMessage("Time-Set-To-Day");
		this.timeSetToNight = new LanguageMessage("Time-Set-To-Night");
		this.timeSetToX = new LanguageMessage("Time-Set-To-X");
		this.teleportToPlayer = new LanguageMessage("Teleport-To-Player");
		this.teleportAPlayerToPlayer = new LanguageMessage("Teleport-A-Player-To-Player");
		this.teleportToLocation = new LanguageMessage("Teleport-To-Location");
		this.teleportAPlayerToLocation = new LanguageMessage("Teleport-A-Player-To-Location");
	}
	
	public YamlConfiguration getConfig() {
		return this.cfg;
	}
}

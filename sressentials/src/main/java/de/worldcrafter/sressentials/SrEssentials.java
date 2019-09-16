package de.worldcrafter.sressentials;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.worldcrafter.sressentials.commands.Gamemode_CMD;
import de.worldcrafter.sressentials.commands.Time_CMD;
import de.worldcrafter.sressentials.commands.Workbench_CMD;
import de.worldcrafter.sressentials.commands.teleport.TeleportHere_CMD;
import de.worldcrafter.sressentials.commands.teleport.Teleport_CMD;
import de.worldcrafter.sressentials.config.ConfigManager;
import de.worldcrafter.sressentials.language.LanguageManager;
import de.worldcrafter.sressentials.utils.ConfigHandler;

/**
* @Author ClientConnection
* @Project sressentials
* @Package de.clientconnection.sressentials
* @Date 14.09.2019
* @Time 15:29:42
*/
public class SrEssentials extends JavaPlugin {

	private static SrEssentials instance;
	private static Logger logger;
	private static ConfigManager configManager;
	private static LanguageManager languageManager;
	private static ConfigHandler configHandler;
	public boolean prefixSet = false;
	
	public void onEnable() {
		logger = Bukkit.getLogger();
		try {
			init();
			register();
			logger.log(Level.FINE, "[SrEssentials] succesfully enabled!");
		} catch(final Exception exc) {
			exc.printStackTrace();
			logger.log(Level.SEVERE, "[SrEssentials] could not loaded succesfully enabled. Please report this error!");
		}
	}

	private void init() {
		instance = this;
		configManager = new ConfigManager();
		languageManager = new LanguageManager();
		configHandler = new ConfigHandler(configManager);
	}
	
	private void register() {
		this.getCommand("gamemode").setExecutor(new Gamemode_CMD());
		this.getCommand("gm").setExecutor(new Gamemode_CMD());
		this.getCommand("workbench").setExecutor(new Workbench_CMD());
		this.getCommand("wb").setExecutor(new Workbench_CMD());
		this.getCommand("time").setExecutor(new Time_CMD());
		this.getCommand("zeit").setExecutor(new Time_CMD());
		this.getCommand("tp").setExecutor(new Teleport_CMD());
		this.getCommand("teleport").setExecutor(new Teleport_CMD());
		this.getCommand("tph").setExecutor(new TeleportHere_CMD());
		this.getCommand("tphere").setExecutor(new TeleportHere_CMD());
	}
	
	public static ConfigManager getConfigManager() {
		return configManager;
	}
	
	public static LanguageManager getLanguageManager() {
		return languageManager;
	}
	
	public static ConfigHandler getConfigHandler() {
		return configHandler;
	}
	
	/**
	 * Is returning the instance of the Main Class
	 * @return SrEssentials
	 */
	public static SrEssentials getInstance() {
		return instance;
	}
}

package de.worldcrafter.sressentials;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.worldcrafter.sressentials.config.ConfigManager;

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
	
	public void onEnable() {
		logger = Bukkit.getLogger();
		logger.log(Level.INFO, "Das Plugin wird gestartet....");
		try {
			init();
			logger.log(Level.FINE, "Das Plugin wurde erfolgreich geladen!");
		} catch(final Exception exc) {
			exc.printStackTrace();
			logger.log(Level.SEVERE, "Das Plugin konnte nicht erfolgreich geladen werden. Bitte melde diesen Fehler umgehend.");
		}
	}

	private void init() {
		instance = this;
		configManager = new ConfigManager();
	}
	
	public static ConfigManager getConfigManager() {
		return configManager;
	}
	
	/**
	 * Is returning the instance of the Main Class
	 * @return SrEssentials
	 */
	public static SrEssentials getInstance() {
		return instance;
	}
}

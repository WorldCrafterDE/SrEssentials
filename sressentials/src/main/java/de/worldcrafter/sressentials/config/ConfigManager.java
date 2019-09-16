package de.worldcrafter.sressentials.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.bukkit.configuration.file.YamlConfiguration;

import de.worldcrafter.sressentials.SrEssentials;

/**
* @Author ClientConnection
* @Project sressentials
* @Package de.clientconnection.sressentials.config
* @Date 14.09.2019
* @Time 16:22:54
*/
public class ConfigManager {

	private final File f = new File("plugins//SrEssentials//config.yml");
	private final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	private final File[] languages;
	
	/**
	 * Initial config.yml and all languages.yml files
	 */
	public ConfigManager() {
		if(!f.exists()) {
			final File o = new File("plugins//SrEssentials//");
			if(!o.exists()) o.mkdir();
			final InputStream link = SrEssentials.getInstance().getResource("resources/config.yml");
			try {
				Files.copy(link, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		final String[] languages = new String[] {"german.yml", "english.yml"};
		this.languages = new File[languages.length];
		final File o = new File("plugins//SrEssentials//languages//");
		if(!o.exists()) o.mkdir();
		int counter = 0;
		for(String path : languages) {
			final InputStream link = SrEssentials.getInstance().getResource("resources/languages/" + path);
			try {
				final File languageFile = new File("plugins//SrEssentials//languages//" + path);
				this.languages[counter] = languageFile;
				counter++;
				if(!languageFile.exists())
				Files.copy(link, languageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns the anti-spam protection time
	 * in seconds
	 * @return
	 */
	public int getTimeAntiSpamTime() {
		return this.cfg.getInt("Time-no-Spam");
	}
	
	/**
	 * Returns the selected language file
	 * @return
	 */
	public String getLanguageFile() {
		return this.cfg.getString("language");
	}
	
	/**
	 * Returns all language files
	 * @return
	 */
	public File[] getLanguages() {
		return this.languages;
	}
}

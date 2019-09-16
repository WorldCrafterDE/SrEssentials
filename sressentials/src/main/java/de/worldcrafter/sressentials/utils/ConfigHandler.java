package de.worldcrafter.sressentials.utils;

import de.worldcrafter.sressentials.config.ConfigManager;

/**
* @Author ClientConnection
* @Project sressentials
* @Package de.worldcrafter.sressentials.utils
* @Date 15.09.2019
* @Time 17:48:56
*/
public class ConfigHandler {

	private final String language;
	private final Long timeAntiSpamTime;
	
	public ConfigHandler(final ConfigManager cfg) {
		String language = cfg.getLanguageFile();
		if(language.endsWith(".yml")) language = language.substring(0, language.length()-4);
		this.language = language;
		this.timeAntiSpamTime = cfg.getTimeAntiSpamTime() * 1000L;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public Long getTimeAntiSpamTime() {
		return this.timeAntiSpamTime + System.currentTimeMillis();
	}
}

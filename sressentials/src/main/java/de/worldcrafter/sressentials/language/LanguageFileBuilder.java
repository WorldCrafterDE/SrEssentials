package de.worldcrafter.sressentials.language;

import java.io.File;

import de.worldcrafter.sressentials.config.ConfigManager;

/**
* @Author ClientConnection
* @Project sressentials
* @Package de.worldcrafter.sressentials.utils
* @Date 15.09.2019
* @Time 15:01:50
*/
public class LanguageFileBuilder {

	private File f;
	
	public LanguageFileBuilder() {}
	
	/**
	 * Setting language file up, by the Config
	 * @param configManager
	 */
	public LanguageFileBuilder(final ConfigManager configManager) {
		String fileName = configManager.getLanguageFile().toLowerCase();
		if(!fileName.endsWith(".yml")) fileName = fileName + ".yml";
		for(final File files : configManager.getLanguages()) {
			if(files.getName().equalsIgnoreCase(fileName)) {
				this.f = files;
				break;
			}
		}
	}

	/**
	 * Setting file language by a name
	 * @param name
	 * @return LanguageFileBuilder
	 */
	public LanguageFileBuilder setLanguage(String name) {
		if(!name.endsWith(".yml")) name = name + ".yml";
		final File f = new File("plugins//SrEssentials//languages//" + name);
		if(f.exists()) this.f = f;
		return this;
	}
	
	/**
	 * Reutrns the file built by the name
	 * @return If no language is selected, english file will be return
	 */
	public File build() {
		if(this.f == null) this.f = new File("plugins//SrEssentials//languages//english.yml");
		return this.f;
	}
}

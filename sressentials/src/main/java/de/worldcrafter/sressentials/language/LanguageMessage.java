package de.worldcrafter.sressentials.language;

import org.bukkit.entity.Player;

/**
* @Author ClientConnection
* @Project sressentials
* @Package de.worldcrafter.sressentials.language
* @Date 15.09.2019
* @Time 15:32:23
*/
public class LanguageMessage {

	private String name;
	
	public LanguageMessage(final String name) {
		this.name = name;
	}
	
	public String setPlayerValue(final Player player, final Object obj) {
		return this.name.replaceAll("%player%", player.getName()).replaceAll("%value%", obj.toString());
	}
	
	public String setPlayerValue(final String name, final Object obj) {
		return this.name.replaceAll("%player%", name).replaceAll("%value%", obj.toString());
	}

	public String setPlayer(final String name) {
		return this.name.replaceAll("%player%", name);
	}
	
	public String setPlayer(final Player player) {
		return this.name.replaceAll("%player%", player.getName());
	}
	
	public String setValue(final Object obj) {
		return this.name.replaceAll("%value%", obj.toString());
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}

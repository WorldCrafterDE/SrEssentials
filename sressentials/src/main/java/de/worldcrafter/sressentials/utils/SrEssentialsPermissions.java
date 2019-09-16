package de.worldcrafter.sressentials.utils;

/**
* @Author ClientConnection
* @Project sressentials
* @Package de.clientconnection.sressentials.utils
* @Date 14.09.2019
* @Time 15:58:26
*/
public interface SrEssentialsPermissions {

	final String PERMPREFIX = "essentials.";
	final String GAMEMODE_SELF = PERMPREFIX + "gamemode.self";
	final String GAMEMODE_OTHER = PERMPREFIX + "gamemode.other";
	
	final String WORKBENCH_SELF = PERMPREFIX + "workbench.use";
	
	final String TIME_DAY = PERMPREFIX + "time.day";
	final String TIME_NIGHT = PERMPREFIX + "time.night";
	final String TIME_SPAM_BYPASS = PERMPREFIX + "time.spam.bypass";
	
	final String TELEPORT_SELF_TO_OTHER = PERMPREFIX + "teleport.self.other";
	final String TELEPORT_OTHER_TO_OTHER = PERMPREFIX + "teleport.other.other";
	
	final String SUPER_PERM = PERMPREFIX + "*";
}

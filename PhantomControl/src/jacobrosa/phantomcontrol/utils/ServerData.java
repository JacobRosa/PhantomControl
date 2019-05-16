package jacobrosa.phantomcontrol.utils;

import jacobrosa.phantomcontrol.config.ConfigManager;

public class ServerData {
	
	private static boolean allowPhantomNaturalSpawn, allowPhantomForcedSpawn;
	
	public static void setup() {
		boolean shouldSave = false;
		
		if(ConfigManager.getConfig().contains("config.phantoms.natural"))
			allowPhantomNaturalSpawn = ConfigManager.getConfig().getBoolean("config.phantoms.natural");
		else {
			allowPhantomNaturalSpawn = true;
			shouldSave = true;
		}
		
		if(ConfigManager.getConfig().contains("config.phantoms.forced"))
			allowPhantomForcedSpawn = ConfigManager.getConfig().getBoolean("config.phantoms.forced");
		else {
			allowPhantomForcedSpawn = true;
			shouldSave = true;
		}
		
		if(shouldSave)
			save();
	}
	
	public static void save() {
		ConfigManager.getConfig().set("config.phantoms.natural", allowPhantomNaturalSpawn);
		ConfigManager.getConfig().set("config.phantoms.forced", allowPhantomForcedSpawn);
	}
	
	public static boolean allowPhantomNaturalSpawning() {
		return allowPhantomNaturalSpawn;
	}
	
	public static void setAllowPhantomNaturalSpawning(boolean bln) {
		allowPhantomNaturalSpawn = bln;
		ConfigManager.getConfig().set("config.phantoms.natural", bln);
	}
	
	public static boolean allowPhantomForcedSpawning() {
		return allowPhantomForcedSpawn;
	}
	
	public static void setAllowPhantomForcedSpawning(boolean bln) {
		allowPhantomForcedSpawn = bln;
		ConfigManager.getConfig().set("config.phantoms.forced", bln);
	}

}

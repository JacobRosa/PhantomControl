package jacobrosa.phantomcontrol;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import jacobrosa.phantomcontrol.commands.CommandPhantom;
import jacobrosa.phantomcontrol.listeners.PhantomListener;
import jacobrosa.phantomcontrol.utils.ServerData;

public class PhantomControl extends JavaPlugin{
	
	private static PhantomControl plugin = null;
	
	private static String name, version;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		name = getDescription().getName();
		version = getDescription().getVersion();
		
		getCommand("phantom").setExecutor(new CommandPhantom());
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PhantomListener(), this);
		
		ServerData.setup();
	}
	
	@Override
	public void onDisable() {
		ServerData.save();
		
		plugin = null;
	}
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public static String getPluginName() {
		return name;
	}
	
	public static String getPluginVersion() {
		return version;
	}

}

package jacobrosa.phantomcontrol.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import jacobrosa.phantomcontrol.utils.ServerData;

public class PhantomListener implements Listener{
	
	@EventHandler
	public void onPhantomSpawn(CreatureSpawnEvent event) {
		SpawnReason reason = event.getSpawnReason();
		LivingEntity livingEntity = event.getEntity();
		if(!(livingEntity instanceof Phantom))
			return;
		
		Bukkit.getServer().broadcastMessage(reason.toString());
		if(reason == SpawnReason.NATURAL) {
			if(!ServerData.allowPhantomNaturalSpawning()) {
				Phantom phantom = (Phantom) livingEntity;
				phantom.remove();
				
				//Debug
				Bukkit.getServer().broadcastMessage("Phantom Removed");
			}
			return;
		}
		
		if(!ServerData.allowPhantomForcedSpawning()) {
			Phantom phantom = (Phantom) livingEntity;
			phantom.remove();
				
			//Debug
			Bukkit.getServer().broadcastMessage("Phantom Removed");
		}
	}

}

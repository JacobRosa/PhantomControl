package jacobrosa.phantomcontrol.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jacobrosa.phantomcontrol.PhantomControl;
import jacobrosa.phantomcontrol.utils.PhantomSpawnReason;
import jacobrosa.phantomcontrol.utils.ServerData;
import jacobrosa.phantomcontrol.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class CommandPhantom extends CustomCommand{

	@Override
	public void run(Player player, Command cmd, String[] args) {
		if(player.hasPermission("phantomcontrol.command.phantom")) {
			if(args.length == 0) {
				player.sendMessage(ChatColor.GRAY + "/phantom <natural|forced> <true|false>");
				player.sendMessage(ChatColor.GRAY + "/phantom info");
				return;
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("info")) {
					boolean naturalSpawning = ServerData.allowPhantomNaturalSpawning(), forcedSpawning = ServerData.allowPhantomForcedSpawning();
					player.sendMessage(PhantomControl.getPluginName() + " v" + PhantomControl.getPluginVersion() + " by kingbluesapphire");
					player.sendMessage("Natural Phantom Spawning: " + (naturalSpawning ? ChatColor.GREEN : ChatColor.RED) + ServerData.allowPhantomNaturalSpawning());
					player.sendMessage("Forced Phantom Spawning: " + (forcedSpawning ? ChatColor.GREEN : ChatColor.RED) + ServerData.allowPhantomForcedSpawning());
					return;
				}
				if(args[0].equalsIgnoreCase("version")) {
					player.sendMessage(ChatColor.GRAY + PhantomControl.getPluginName() + " v" + PhantomControl.getPluginVersion() + " by kingbluesapphire");
					return;
				}
				player.sendMessage(ChatColor.GRAY + "/phantom <natural|forced> <true|false>");
				player.sendMessage(ChatColor.GRAY + "/phantom info");
				return;
			}
			processInput(player, args[0], args[1]);
			return;
		}else {
			player.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			return;
		}
	}

	@Override
	public void run(CommandSender commandSender, Command cmd, String[] args) {
		if(args.length == 0) {
			commandSender.sendMessage(ChatColor.GRAY + "/phantom <natural|forced> <true|false>");
			commandSender.sendMessage(ChatColor.GRAY + "/phantom info");
			return;
		}
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("info")) { 
				boolean naturalSpawning = ServerData.allowPhantomNaturalSpawning(), forcedSpawning = ServerData.allowPhantomForcedSpawning();
				commandSender.sendMessage(PhantomControl.getPluginName() + " v" + PhantomControl.getPluginVersion() + " by kingbluesapphire");
				commandSender.sendMessage("Natural Phantom Spawning: " + (naturalSpawning ? ChatColor.GREEN : ChatColor.RED) + ServerData.allowPhantomNaturalSpawning());
				commandSender.sendMessage("Forced Phantom Spawning: " + (forcedSpawning ? ChatColor.GREEN : ChatColor.RED) + ServerData.allowPhantomForcedSpawning());
				return;
			}
			commandSender.sendMessage(ChatColor.GRAY + "/phantom <natural|forced> <true|false>");
			commandSender.sendMessage(ChatColor.GRAY + "/phantom info");
			return;
		}
		processInput(commandSender, args[0], args[1]);
		return; 
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length > 2)
			return new ArrayList<>();
		if(args.length == 1) {
			List<String> arg1 = new ArrayList<>();
			arg1.add("natural");
			arg1.add("forced");
			arg1.add("info");
			return arg1;
		}
		if(args[0].equalsIgnoreCase("info"))
			return new ArrayList<>();
		List<String> arg2 = new ArrayList<String>();
		arg2.add("true");
		arg2.add("false");
		return arg2;
	}

	private void processInput(CommandSender sender, String reasonInput, String valueInput) {
		PhantomSpawnReason spawnReason = getPhantomSpawnReasonFromString(reasonInput);
		if(spawnReason == null) {
			sender.sendMessage(ChatColor.RED + "Invalid spawn reason!");
			return;
		}
		if(!Utils.isValidBoolean(valueInput)) {
			sender.sendMessage(ChatColor.RED + "Invalid value.");
			return;
		}
		boolean valueTo = Utils.booleanFromString(valueInput);
		if(spawnReason == PhantomSpawnReason.Natural)
			ServerData.setAllowPhantomNaturalSpawning(valueTo);
		if(spawnReason == PhantomSpawnReason.Forced)
			ServerData.setAllowPhantomForcedSpawning(valueTo);
		sender.sendMessage(ChatColor.GREEN + "Phantoms spawn reason " + spawnReason.toString().toLowerCase() + " set to " + valueTo);
	}

	private PhantomSpawnReason getPhantomSpawnReasonFromString(String input) {
		if(input.equalsIgnoreCase("natural")) {
			return PhantomSpawnReason.Natural;
		}else if(input.equalsIgnoreCase("forced")) {
			return PhantomSpawnReason.Forced;
		}else {
			return null;
		}
	}

}

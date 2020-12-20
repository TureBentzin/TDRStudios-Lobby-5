package net.juligames.lobbyplugin.commands;

import net.juligames.lobbyplugin.LobbyPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class setspawn implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (player.hasPermission("setspawn")) {
        if (args.length == 0) {
          FileConfiguration config = LobbyPlugin.getPlugin().getConfig();
          config.set("Spawn.World", player.getWorld().getName());
          config.set("Spawn.X", Double.valueOf(player.getLocation().getX()));
          config.set("Spawn.Y", Double.valueOf(player.getLocation().getY()));
          config.set("Spawn.Z", Double.valueOf(player.getLocation().getZ()));
          config.set("Spawn.Yaw", Float.valueOf(player.getLocation().getYaw()));
          config.set("Spawn.Pitch", Float.valueOf(player.getLocation().getPitch()));
          LobbyPlugin.getPlugin().saveConfig();
          player.sendMessage("§8[§eInfo§8]§a Du hast den Spawnpunkt gesetzt!");
        } else {
          player.sendMessage("§8[§e!§8]§a Bitte benutze /setspawn");
        } 
      } else {
        player.sendMessage("§8[§e!§8]§a Dafür reichen deine Recht nicht aus!");
      } 
    } 
    return false;
  }
}
